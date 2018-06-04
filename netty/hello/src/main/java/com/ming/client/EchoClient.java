package com.ming.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * echo client 引导
 *
 * @author ming
 * @date 2018-04-09 09:11
 */
public class EchoClient {
    private final String host = "localhost";
    private final int port = 10000;


    public static void main(String[] args) throws InterruptedException {
        new EchoClient().start();
    }

    /**
     * echo client 引导
     *
     * @author ming
     * @date 2018-04-09 09:12
     */
    public void start() throws InterruptedException {
        //创建 event 基于nio实现
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientChannelHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭 event
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}
