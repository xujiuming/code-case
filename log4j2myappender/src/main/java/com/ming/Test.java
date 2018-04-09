package com.ming;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 测试 log4j2 日志转换器
 *
 * @author ming
 * @date 2018-03-29 13:32
 */
@Log4j2
public class Test {
    private static final Logger loggerByLogManager = LogManager.getLogger(Test.class);

    /**
     * 测试输出日志
     * ALL
     * TRACE
     * DEBUG
     * INFO
     * WARN
     * ERROR
     * FATAL
     * OFF
     *
     * @author ming
     * @date 2018-03-29 13:02
     */
    public static void main(String[] args) {
        //logManager 提供的logging
        loggerByLogManager.log(Level.ALL, "ming all");
        loggerByLogManager.trace("ming tarce");
        loggerByLogManager.debug("ming debug");
        loggerByLogManager.info("ming  info ");
        loggerByLogManager.warn("ming warn ");
        loggerByLogManager.error("ming error");
        loggerByLogManager.fatal("ming fatal");
        loggerByLogManager.log(Level.OFF, "ming off");

        // lombok 的@log4j2 提供的logger
        log.log(Level.ALL, "ming all");
        log.trace("ming tarce");
        log.debug("ming debug");
        log.info("ming  info ");
        log.warn("ming warn ");
        log.error("ming error");
        log.fatal("ming fatal");
        log.log(Level.OFF, "ming off");
    }
}
