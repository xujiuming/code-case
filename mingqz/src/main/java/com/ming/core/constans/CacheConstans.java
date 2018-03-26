package com.ming.core.constans;

import lombok.Data;

/**
 * 缓存实例名称
 * 方便使用 CacheManager 提取缓存实例
 *
 * @author ming
 * @date 2018-01-18 14:56
 */
@Data
public class CacheConstans {

    /**
     * user  token 缓存实例
     *
     * @author ming
     * @date 2018-01-18 14:58
     */
    public final static String USER_TOKEN = "userToken";


    /**
     * user resource 缓存实例
     *
     * @author ming
     * @date 2018-01-18 14:59
     */
    public final static String USER_RESOURCE = "userResource";
}
