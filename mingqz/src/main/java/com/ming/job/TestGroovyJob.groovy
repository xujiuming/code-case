package com.ming.job

import com.ming.base.quartz.BaseProxyJob

class TestGroovyJob extends BaseProxyJob {
    @Override
    void execute() {
        println "testGroovyJob"
    }
}
