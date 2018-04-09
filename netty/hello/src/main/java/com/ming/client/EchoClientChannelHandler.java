package com.ming.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * echo客户端 channel handler
 *
 * @author ming
 * @date 2018-04-09 09:05
 */
public class EchoClientChannelHandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     * 建立链接时候调用
     *
     * @author ming
     * @date 2018-04-09 09:05
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当链接成功时候 发送一条消息
        ctx.writeAndFlush(Unpooled.copiedBuffer("ming 建立链接", CharsetUtil.UTF_8));
        super.channelActive(ctx);
    }

    /**
     * 读取到服务器传递来的消息 时候调用
     * 注意: 服务发送过来的消息 可能不是一块
     * 例如 服务器发送5kb的数据  client 是可能执行两次channelRead0 方法的 第一次读取了3kb  第二次读取2kb
     * 服务器保证发送的字节数组会按照顺序发送  但是client 接受可能是多阶段接受的
     *
     * @author ming
     * @date 2018-04-09 09:05
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("client info::" + msg.toString(CharsetUtil.UTF_8));
    }

    /**
     * 异常处理
     *
     * @author ming
     * @date 2018-04-09 09:06
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
