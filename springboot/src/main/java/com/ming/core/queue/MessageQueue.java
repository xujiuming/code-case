package com.ming.core.queue;

import com.ming.core.queue.body.MessageQueueBody;
import lombok.Data;

/**
 * 消息队列 内容
 *
 * @author ming
 * @date 2017-08-28 16点
 */
@Data
public class MessageQueue {
    private String topic;
    private MessageQueueBody body;
    private Integer version;
}
