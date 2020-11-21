package com.luffy.service;

import com.luffy.model.SysPermission;

import java.util.List;

/**
 * @author luffy
 */
public interface SysPermissionService {

    List<SysPermission> queryByUserNameAndLevel(String userName, int level);

    List<SysPermission> queryByUserNameParentId(String userName, String parentId);
}
