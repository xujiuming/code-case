package com.ming;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class Test {
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(Test.class);

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
        logger.log(Level.ALL, "ming all");
        logger.trace("ming tarce");
        logger.debug("ming debug");
        logger.info("ming  info ");
        logger.warn("ming warn ");
        logger.error("ming error");
        logger.fatal("ming fatal");
        logger.log(Level.OFF, "ming off");
    }
}
