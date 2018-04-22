package com.ming.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

/**
 * 时间服务 处理渠道
 * 只需要处理client 链接上的时候 返回日期即可 不需要对其他信息进行处理
 *
 * @author ming
 * @date 2018-04-09 09:58
 */
@ChannelHandler.Sharable
public class TimeServerChannelHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当client 链接上来的时候
     *
     * @author ming
     * @date 2018-04-09 10:00
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final ByteBuf result = ctx.alloc().buffer();
        result.writeBytes("ming".getBytes(CharsetUtil.UTF_8));
        final ChannelFuture future = ctx.writeAndFlush(result);
        System.out.println("-------");
        future.addListener((ChannelFutureListener) future1 -> ctx.close());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }

    /**
     * 读取渠道
     *
     * @author ming
     * @date 2018-04-08 17:08
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("server::" + in.toString(CharsetUtil.UTF_8));
        //将收到的消息 发送给发送者 不冲刷出站消息
        ctx.write(in);
    }

    /**
     * 渠道读取 完整处理
     *
     * @author ming
     * @date 2018-04-08 17:09
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将未决消息 冲刷到远程节点 并且关闭channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }
}
