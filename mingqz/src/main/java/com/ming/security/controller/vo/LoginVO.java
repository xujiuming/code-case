package com.ming.security.controller.vo;

import lombok.Data;

import java.io.Serializable;


/**
 * login view object
 *
 * @author ming
 * @date 2018-01-06 17:14
 */
@Data
public class LoginVO implements Serializable {

    private String username;
    private String password;
    private Boolean rememberMe;
    private String name;
    private String headImageUrl;
    private String token;


}
