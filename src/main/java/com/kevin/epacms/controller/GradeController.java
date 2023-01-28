package com.kevin.epacms.controller;

import com.kevin.epacms.service.GradeService;
import com.kevin.epacms.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
@Api(tags = "班级管理API")
@CrossOrigin
@RestController
@RequestMapping("/api/grade")
public class GradeController {
    @Resource
    private GradeService gradeService;

    @ApiOperation("获取所有班级信息")
    @GetMapping
    public Result getAllGrade(){
        return gradeService.getAllGrade();
    }

}
