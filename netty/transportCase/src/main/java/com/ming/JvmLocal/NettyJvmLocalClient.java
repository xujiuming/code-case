package com.ming.JvmLocal;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.util.CharsetUtil;

/**
 * netty 基于jvm内部本地通信 client 实现
 *
 * @author ming
 * @date 2018-04-17 13:45
 */
public class NettyJvmLocalClient {


    /**
     * 启动方法
     *
     * @author ming
     * @date 2018-04-17 13:50
     */
    public void Start() throws InterruptedException {
        final ByteBuf byteBuf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("hi ming client", CharsetUtil.UTF_8));
        //注册 local 事件处理器
        EventLoopGroup eventLoopGroup = new LocalEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    //使用 localChannel渠道
                    .channel(LocalChannel.class)
                    //注册 local模式的地址
                    .remoteAddress(new LocalAddress(NettyJvmLocal.LOCAL_ADDRESS))
                    .handler(new ChannelInitializer<LocalChannel>() {
                        @Override
                        protected void initChannel(LocalChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("connect " + ctx);
                                    ctx.writeAndFlush(byteBuf).addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

}
