package com.kevin.epacms.controller;

import cn.hutool.core.bean.BeanUtil;
import com.kevin.epacms.entity.User;
import com.kevin.epacms.entity.dto.SearchDto;
import com.kevin.epacms.entity.dto.UserDto;
import com.kevin.epacms.entity.vo.ConditionVo;
import com.kevin.epacms.service.GradeService;
import com.kevin.epacms.service.UserService;
import com.kevin.epacms.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
@Api(tags = "用户管理API")
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation("通过用户名获取用户")
    @GetMapping("/name")
    public User getUserByUsername(@RequestParam("username") String username){
        return userService.getUserByUsername(username);
    }

    @ApiOperation("通过用户名获取当前用户信息")
    @GetMapping("/currentUser")
    public UserDto getCurrentUserByUsername(@RequestParam("username") String username){
        return userService.getCurrentUserByUsername(username);
    }

    @ApiOperation("用户名是否已经注册")
    @GetMapping("/exist")
    public Result isExistUser(@RequestParam("username") String username){
        return userService.isExistUser(username);
    }

    @ApiOperation("分页查询用户信息")
    @PostMapping("/page")
    public Result getUsersByPage(@RequestBody SearchDto searchConditions,
                                 @RequestParam(name = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize){
        return userService.getUsersByPage(currentPage, pageSize, searchConditions.getConditions());
    }

    @ApiOperation("更新用户信息")
    @PutMapping
    public Result updateUser(@RequestBody UserDto userDto){
        User user = BeanUtil.toBean(userDto, User.class);
        return userService.updateUser(user);
    }

    @ApiOperation("重置用户密码")
    @PutMapping("resetPwd")
    public Result resetPassword(@RequestParam("uid") Long uid){
        return userService.resetPassword(uid);
    }

    @ApiOperation("修改密码")
    @PutMapping("/password")
    public Result changePassword(@RequestParam("password") String password){
        return userService.changePassword(password);
    }

    @ApiOperation("根据id删除用户")
    @DeleteMapping
    public Result deleteUserById(@RequestParam("id") Long id){
        return userService.deleteUserById(id);
    }

    @ApiOperation("批量删除用户")
    @DeleteMapping("/batch")
    public Result deleteBatchUsers(@RequestParam("userIdsStr") String userIdsStr){
        return userService.deleteUsers(userIdsStr);
    }

    @ApiOperation("导出用户信息")
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        userService.export(response);
    }

}
