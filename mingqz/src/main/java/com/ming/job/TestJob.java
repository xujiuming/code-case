package com.ming.job;

import com.ming.base.quartz.BaseProxyJob;
import org.springframework.stereotype.Component;

/**
 * @author ming
 * @date 2017-11-08 14:41
 */
@Component("testJob")
public class TestJob extends BaseProxyJob {

    @Override
    public void execute() {
        System.out.println("testJob");
    }
}
