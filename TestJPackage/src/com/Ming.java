package com;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * 提供一个简易 的http服务
 *
 * @author ming
 * @date 2020-05-09 14:00
 */
public class Ming {
    public static void main(String[] args) throws IOException, InterruptedException {
        //创建http服务器，绑定本地8888端口
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        //创建上下文监听,拦截包含/
        httpServer.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                System.out.println("访问服务 [/]"+System.currentTimeMillis());
                exchange.sendResponseHeaders(200, 0);
                OutputStream os = exchange.getResponseBody();
                os.write("mmp!".getBytes("UTF-8"));
                os.close();
            }
        });
        System.out.println("开启httpServer服务:8080端口:提供[/]访问");
        httpServer.start();
    }
}
