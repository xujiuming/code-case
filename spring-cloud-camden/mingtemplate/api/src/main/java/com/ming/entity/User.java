package com.ming.entity;


import lombok.Data;

@Data
public class User {
    private Integer userId;

    private String userName;

    private String password;

    private String phone;
}