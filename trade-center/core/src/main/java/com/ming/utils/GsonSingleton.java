package com.ming.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * gson 单例对象  双检锁 模式
 *
 * @author ming
 * @date 2018-08-23 09:56:33
 */
public class GsonSingleton {
    private volatile static Gson gson;

    private GsonSingleton() {
    }

    public static Gson getInstance() {
        if (null == gson) {
            synchronized (Gson.class) {
                if (null == gson) {
                    gson = new GsonBuilder()
                            .setDateFormat(Constant.DATE_PATTERN)
                            .create();
                }
            }
        }
        return gson;
    }
}
