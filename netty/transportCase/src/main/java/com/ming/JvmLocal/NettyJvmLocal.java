package com.ming.JvmLocal;

/**
 * 调用 netty jvm  local server 和client 的客户端
 *
 * @author ming
 * @date 2018-04-17 14:09
 */
public class NettyJvmLocal {
    /**
     * 本地地址
     *
     * @author ming
     * @date 2018-04-17 14:16
     */
    public static final String LOCAL_ADDRESS = "ming";

    public static void main(String[] args) throws InterruptedException {
        // 启动 server
        new Thread(() -> {
            try {
                new NettyJvmLocalServer().Start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //暂停 线程 1s 等待server 启动完毕
        Thread.sleep(1000L);

        //启动client
        new Thread(() -> {
            try {
                new NettyJvmLocalClient().Start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
