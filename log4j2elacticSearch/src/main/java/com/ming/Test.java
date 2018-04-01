package com.ming;


import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

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
    public static void main(String[] args) throws IOException {
        Long begin = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            logger.log(Level.ALL, "ming all");
            logger.trace("ming tarce"+ i);
            logger.debug("ming debug"+ i);
            logger.info("ming  info "+ i);
            logger.warn("ming warn "+ i);
            logger.error("ming error"+ i);
            logger.fatal("ming fatal"+ i);
            logger.log(Level.OFF, "ming off" + i);
        }
        System.out.println("---------");
        System.out.println("耗时:" + (System.currentTimeMillis() - begin));
    }


}
