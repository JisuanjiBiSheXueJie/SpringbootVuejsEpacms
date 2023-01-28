package com.kevin.epacms.controller;

import com.kevin.epacms.service.MailService;
import com.kevin.epacms.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author kevin
 * @since 2022/11/11 21:24
 */
@Api(tags = "邮箱管理API")
@CrossOrigin
@RestController
@RequestMapping("/api/email")
public class MailController {
    @Resource
    private MailService mailService;

    @ApiOperation("判断邮箱是否存在")
    @GetMapping
    public Result isExistEmail(@RequestParam("email") String email){
        return mailService.isExistEmail(email);
    }

    @ApiOperation("获取验证码")
    @PostMapping
    public Result getCode(@RequestParam("email") String email){
        return mailService.getCode(email);
    }

    @ApiOperation("通知该同学打卡")
    @GetMapping("/notice")
    public Result noticeEmail(@RequestParam("email") String email){
        return mailService.noticeEmail(email);
    }
}
