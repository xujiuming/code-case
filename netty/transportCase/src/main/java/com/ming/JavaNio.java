package com.ming;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * java 实现nio 模式的服务端
 *
 * @author ming
 * @date 2018-04-11 15:08
 */
public class JavaNio {


    public static void main(String[] args) throws IOException {
        new JavaNio().StartJavaNio(20000);
    }

    /**
     * 启动java nio 服务端
     *
     * @param port
     * @author ming
     * @date 2018-04-11 15:09
     */
    public void StartJavaNio(int port) throws IOException {
        //打开 server socket channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置 为非阻塞队列
        serverSocketChannel.configureBlocking(false);
        //通过socket channel 获取socket
        ServerSocket socket = serverSocketChannel.socket();
        //获取socket 的地址
        InetSocketAddress socketAddress = new InetSocketAddress(port);
        //绑定 socket地址到socket
        socket.bind(socketAddress);
        //打开selector
        Selector selector = Selector.open();
        //将socket channel 注册到selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //返回客户端的消息
        final ByteBuffer msg = ByteBuffer.wrap("hi ming ".getBytes("UTF-8"));

        for (; ; ) {
            try {
                //阻塞等待需要处理的新事件
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            //获取所有连接事件的 select key
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            readyKeys.forEach(key -> {
                        try {
                            //检查时间 是否是一个新的 可以被接受的链接
                            if (key.isAcceptable()) {
                                ServerSocketChannel server = (ServerSocketChannel) key.channel();
                                SocketChannel client = server.accept();
                                if (null == client){
                                    return;
                                }
                                client.configureBlocking(false);
                                client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                                System.out.println("accept from " + client);
                            }
                            //检查socket是否准备好写数据
                            if (key.isWritable()) {
                                SocketChannel client = (SocketChannel) key.channel();
                                ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                                while (byteBuffer.hasRemaining()) {
                                    if (client.write(byteBuffer) == 0) {
                                        break;
                                    }
                                }
                                client.close();
                            }

                        } catch (IOException e) {
                            key.cancel();
                            try {
                                key.channel().close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
            );
        }
    }
}
