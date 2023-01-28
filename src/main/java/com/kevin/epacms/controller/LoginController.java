package com.kevin.epacms.controller;

import com.kevin.epacms.entity.dto.LoginParam;
import com.kevin.epacms.entity.dto.RegisterParam;
import com.kevin.epacms.service.UserService;
import com.kevin.epacms.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author kevin
 * @since 2022/11/17 22:23
 */
@Api(tags = "登录管理API")
@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {
    @Resource
    private UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginParam loginParam, HttpServletRequest request){
        return userService.login(loginParam, request);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@Valid @RequestBody RegisterParam registerParam){
        return userService.register(registerParam);
    }
}
