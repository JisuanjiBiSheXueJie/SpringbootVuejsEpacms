package com.kevin.epacms.mapper;

import com.kevin.epacms.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据roleId查询权限列表
     *
     * @param roleId 角色ID
     * @return {@link Permission}
     */
    List<Permission> getMenuByRid(Integer roleId);
}
