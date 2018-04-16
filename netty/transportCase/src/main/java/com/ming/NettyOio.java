package com.ming;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * netty oio 实现
 *
 * @author ming
 * @date 2018-04-16 15:56
 */
public class NettyOio {
    public static void main(String[] args) throws InterruptedException {
        new NettyOio().Start(20000);
    }

    /**
     * netty oio bootstrap
     *
     * @author ming
     * @date 2018-04-16 15:56
     */
    public void Start(int port) throws InterruptedException {
        final ByteBuf byteBuf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("hi ming ", CharsetUtil.UTF_8));
        // 使用 oio 事件循环处理
        EventLoopGroup eventLoopGroup = new OioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup)
                    //使用oio 渠道
                    .channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("accept " + ctx);
                                    //输出一条信息 关闭 渠道
                                    ctx.writeAndFlush(byteBuf.duplicate())
                                            .addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            //绑定服务器
            ChannelFuture future = serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}
