package com.luffy.model;

import lombok.Data;

import java.util.Date;

/**
 * @author luffy
 */
@Data
public class User {

    private String id;
    private String userName;
    private String account;
    private String password;
    private int state;
    private Date registerTime;
    private Date updateTime;
}
