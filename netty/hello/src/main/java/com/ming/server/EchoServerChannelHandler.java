package com.ming.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * echo 服务器 渠道处理
 * @author ming
 * @date 2018-04-08 17:06
 */
//标识 channel handler 可以被多个channel 安全的共享
@ChannelHandler.Sharable
public class EchoServerChannelHandler extends ChannelInboundHandlerAdapter {

    /**读取渠道
     * @author ming
     * @date 2018-04-08 17:08
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("server::"+in.toString(CharsetUtil.UTF_8));
        //将收到的消息 发送给发送者 不冲刷出站消息
        ctx.write(in);
    }

    /**渠道读取 完整处理
     * @author ming
     * @date 2018-04-08 17:09
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将未决消息 冲刷到远程节点 并且关闭channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**异常处理
     * @author ming
     * @date 2018-04-08 17:12
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
