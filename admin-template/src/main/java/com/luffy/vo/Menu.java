package com.luffy.vo;

import com.luffy.model.SysPermission;
import lombok.Data;

import java.util.List;

/**
 * @author luffy
 */
@Data
public class Menu {

    private String id;
    private String name;
    private String icon;
    private String uri;
    private List<Menu> childMenus;

    public Menu(SysPermission permission) {
        this.id = permission.getId();
        this.name = permission.getName();
        this.icon = permission.getIcon();
        this.uri = permission.getUri();
    }
}
