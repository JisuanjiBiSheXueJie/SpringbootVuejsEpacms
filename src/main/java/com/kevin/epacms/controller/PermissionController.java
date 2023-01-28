package com.kevin.epacms.controller;

import com.kevin.epacms.service.PermissionService;
import com.kevin.epacms.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
@Api(tags = "权限管理API")
@CrossOrigin
@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @ApiOperation("根据roleId查询权限列表")
    @GetMapping
    public Result getMenuByRid(@RequestParam("roleId") Integer roleId){
        return permissionService.getMenuByRid(roleId);
    }
}
