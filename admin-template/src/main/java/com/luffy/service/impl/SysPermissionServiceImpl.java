package com.luffy.service.impl;

import com.luffy.mapper.SysPermissionMapper;
import com.luffy.model.SysPermission;
import com.luffy.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luffy
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> queryByUserNameAndLevel(String userName, int level) {
        return sysPermissionMapper.queryByUserNameAndLevel(userName, level);
    }

    @Override
    public List<SysPermission> queryByUserNameParentId(String userName, String parentId) {
        return sysPermissionMapper.queryByUserNameParentId(userName, parentId);
    }
}
