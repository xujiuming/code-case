package com.ming.base.system;

/**
 * 系统
 *
 * @author ming
 * @date 2017-09-11 18:04
 */
public class SystemContext {

    /**
     * 用户上下文 ThreadLocal
     *
     * @author ming
     * @date 2017-09-11 18:06
     */
    private static ThreadLocal<UserContext> userContextThreadLocal = new ThreadLocal<>();


    public static void put(UserContext userContext) {
        userContextThreadLocal.set(userContext);
    }

    public static UserContext get() {
        return userContextThreadLocal.get();
    }


}
