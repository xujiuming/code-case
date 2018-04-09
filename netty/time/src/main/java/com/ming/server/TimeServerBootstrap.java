package com.ming.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;


/**
 * time server 的引导器
 *
 * @author ming
 * @date 2018-04-09 10:05
 */
public class TimeServerBootstrap {


    public static void main(String[] args) throws InterruptedException {
        new TimeServerBootstrap().start();
    }



    /**
     * 启动echo server
     *
     * @author ming
     * @date 2018-04-08 17:21
     */
    public void start() throws InterruptedException {
        final TimeServerChannelHandler timeServerChannelHandler = new TimeServerChannelHandler();
        //创建 监听处理组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建引导器
            ServerBootstrap bootstrap = new ServerBootstrap();
            //配置引导器
            bootstrap.group(group)
                    //引导器使用nio
                    .channel(NioServerSocketChannel.class)
                    //指点端口设置 socket
                    .localAddress(new InetSocketAddress(10000))
                    //添加 echo channel 到引导器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(timeServerChannelHandler);
                        }
                    });
            //异步绑定服务器 调用sync 阻塞等到绑定完成
            ChannelFuture future = bootstrap.bind().sync();
            //获取channel的close future 阻塞等到完成
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }



}
