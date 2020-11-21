package com.luffy.mapper;

import com.luffy.model.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luffy
 */
public interface SysPermissionMapper {

    List<SysPermission> queryByUserNameAndLevel(@Param(value = "userName") String userId, @Param(value = "level") int level);

    List<SysPermission> queryByUserNameParentId(@Param(value = "userName") String userId, @Param(value = "parentId") String parentId);
}
