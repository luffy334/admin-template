package com.luffy.service.impl;

import com.luffy.model.SysPermission;
import com.luffy.service.SysPermissionService;
import com.luffy.service.SysService;
import com.luffy.vo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luffy
 */
@Service
public class SysServiceImpl implements SysService {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public List<Menu> queryUserMenu(String userName) {
        List<Menu> menus = new ArrayList<>();
        List<SysPermission> sysPermissions = sysPermissionService.queryByUserNameAndLevel(userName, 1);
        for (SysPermission sysPermission : sysPermissions) {
            Menu menu = new Menu(sysPermission);
            // 子菜单
            List<Menu> childMenus = new ArrayList<>(10);
            List<SysPermission> childPermission = sysPermissionService.queryByUserNameParentId(userName, sysPermission.getId());
            for (SysPermission permission : childPermission) {
                childMenus.add(new Menu(permission));
            }
            menu.setChildMenus(childMenus);
            menus.add(menu);
        }
        return menus;
    }
}
