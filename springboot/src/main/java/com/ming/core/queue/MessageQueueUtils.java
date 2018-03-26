package com.ming.core.queue;

import com.ming.core.queue.body.MessageQueueBody;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 操作消息队列的工具类
 * 当消息队列满了 可以手动选择丢弃部分不重要的topic的消息
 * 经过考虑 选择ConcurrentLinkedQueue  并发优化的linkedQueue  阻塞式  允许丢弃 部分不重要的topic
 *
 * @author ming
 * @date 2017-08-28 16点
 */
public class MessageQueueUtils {

    /**
     * 队列深度  暂定100   因为机器 预估只能买 1核 1g的 太深了 可能会gg
     */
    public static final int QUEUE_MAX_SIZE = 100;

    /*
    * 使用 阻塞式 队列
    * 先不使用 多线程优化后的队列
    * */
    // private static ConcurrentLinkedQueue<MessageQueueBody> messageQueue = new ConcurrentLinkedQueue<>();
    private static LinkedBlockingQueue<MessageQueueBody> messageQueue = new LinkedBlockingQueue(QUEUE_MAX_SIZE);


    /**
     * 获取消息队列
     *
     * @author ming
     * @date 2017-08-28 19点
     */
    public static LinkedBlockingQueue<MessageQueueBody> getMessageQueue() {
        return messageQueue;
    }


}
