package com.luffy.mapper;

import com.luffy.model.SysRole;

import java.util.List;

/**
 * @author luffy
 */
public interface SysRoleMapper {

    List<SysRole> queryRoleByUserId(String userId);
}
