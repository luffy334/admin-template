package com.luffy.model;

import lombok.Data;

import java.util.Date;

/**
 * @author luffy
 */
@Data
public class SysRole {

    private String id;
    private String name;
    private String creator;
    private Date creatTime;
    private Date updateTime;
}
