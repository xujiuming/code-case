package com.ming.base.quartz;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时器具体任务实现任务基类。所有子类需要继承它.
 * <pre>
 *     2. 使用{@link #setMemo(String)}可以设置任务结束后备注
 *     3. 当任务出现异常时，会被记录到日志里并标记任务失败。所以任务的异常需要抛出来，不要catch掉不抛出。
 *     4. 记得实现类加{@link org.springframework.stereotype.Service}注解，让它可以被spring扫描到
 * </pre>
 *
 * @author ming
 * @date 2017-11-09 16:32
 */
@Slf4j
public abstract class BaseProxyJob {

    /**
     * 任务执行完之后的备注
     */
    private String memo;

    /**
     * 实现
     */
    public abstract void execute();

    public String getMemo() {
        return memo;
    }

    /**
     * 设置执行后备注
     *
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
}
