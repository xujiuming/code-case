package com.ming.utils;

import java.util.Collection;
import java.util.Map;

/**集合工具类
 * @author ming
 * @date 2018-09-25 15:57:22
 */
public class CollectionUtils {
    /**
     * @param tCollection
     * @return boolean
     * @author ming
     * @date 2017-08-15 11点
     */
    public static <T> boolean isEmpty(Collection<T> tCollection) {
        return org.springframework.util.CollectionUtils.isEmpty(tCollection);
    }


    /**
     * @param tdMap
     * @return boolean
     * @author ming
     * @date 2017-08-15 11点
     */
    public static <T, V> boolean isEmpty(Map<T, V> tdMap) {
        return org.springframework.util.CollectionUtils.isEmpty(tdMap);
    }


    /**
     * @param tCollection
     * @return boolean
     * @author ming
     * @date 2017-08-15 11点
     */
    public static <T> boolean notEmpty(Collection<T> tCollection) {
        return !isEmpty(tCollection);
    }


    /**
     * @param tdMap
     * @return boolean
     * @author ming
     * @date 2017-08-15 11点
     */
    public static <T, V> boolean notEmpty(Map<T, V> tdMap) {
        return !isEmpty(tdMap);
    }

}
