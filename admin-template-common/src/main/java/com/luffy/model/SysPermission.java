package com.luffy.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luffy
 */
@Data
public class SysPermission implements Serializable {

    private String id;
    private String parentId;
    private String name;
    private String icon;
    private String uri;
    private int level;
    private int sort;
    private Date creatTime;
    private Date updateTime;
}
