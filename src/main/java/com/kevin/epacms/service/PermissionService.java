package com.kevin.epacms.service;

import com.kevin.epacms.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kevin.epacms.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 根据roleId查询权限列表
     *
     * @param roleId 角色ID
     * @return {@link Result}
     */
    Result getMenuByRid(Integer roleId);
}
