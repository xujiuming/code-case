package com.ming.core.constants;

/**
 *系统类型
 *@author ming
 *@date 2017-10-30 13:05
 */
public enum  SystemType {
    USER_APP_PLATFORM(1,"用户app平台"),
    EMP_APP_PLATFORM(2,"员工app平台"),
    SYSTEM_PLATFORM(3,"系统平台"),
    H5_PLATFORM(4,"h5平台")

    ;

    private Integer type;
    private String name ;

    SystemType(Integer type, String name) {
        this.type = type;
        this.name = name;
    }
}
