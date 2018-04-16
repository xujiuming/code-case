package com.ming;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 基于java 阻塞io的 服务端案例
 *
 * @author ming
 * @date 2018-04-11 14:53
 */
public class JavaOio {

    public static void main(String[] args) throws IOException {
        new JavaOio().StartJavaOio(20000);
    }

    /**
     * 启动java oio 方法
     *
     * @param port
     * @author ming
     * @date 2018-04-11 14:57
     */
    public void StartJavaOio(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        for (; ; ) {
            //接受链接
            final Socket clientSocket = socket.accept();
            System.out.println("accept client::" + clientSocket);
            //建立新线程执行 客户端的请求
            new Thread(() -> {
                OutputStream outputStream = null;
                try {
                    //获取输出流
                    outputStream = clientSocket.getOutputStream();
                    //输出数据
                    outputStream.write("hi ming ".getBytes(Charset.forName("UTF-8")));
                    //刷新数据
                    outputStream.flush();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
