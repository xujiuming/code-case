package com.ming.JvmLocal;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.local.LocalServerChannel;
import io.netty.util.CharsetUtil;

/**
 * netty 基于jvm内部本地通信 server实现
 *
 * @author ming
 * @date 2018-04-17 13:45
 */
public class NettyJvmLocalServer {


    /**
     * 启动方法
     *
     * @author ming
     * @date 2018-04-17 13:50
     */
    public void Start() throws InterruptedException {
        final ByteBuf byteBuf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("hi ming ", CharsetUtil.UTF_8));
        //注册 local 事件处理器
        EventLoopGroup eventLoopGroup = new LocalEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup)
                    .channel(LocalServerChannel.class)
                    .localAddress(new LocalAddress(NettyJvmLocal.LOCAL_ADDRESS))
                    .childHandler(new ChannelInitializer<LocalChannel>() {
                        @Override
                        protected void initChannel(LocalChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("accept " + ctx);
                                    ctx.writeAndFlush(byteBuf).addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            ChannelFuture future = serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }


    }

}
