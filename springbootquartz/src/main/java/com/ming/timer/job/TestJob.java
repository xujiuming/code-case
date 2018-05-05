package com.ming.timer.job;

import com.ming.timer.base.BaseProxyJob;
import org.springframework.stereotype.Component;

/**
 * test job
 *
 * @author ming
 * @date 2018-04-23 17:17
 */
@Component
public class TestJob extends BaseProxyJob {
    @Override
    public void execute() {
        System.out.println("test job");
    }
}
