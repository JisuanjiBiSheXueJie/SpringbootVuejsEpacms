package com.kevin.epacms.controller;

import com.kevin.epacms.entity.Leave;
import com.kevin.epacms.entity.dto.LeaveDto;
import com.kevin.epacms.service.LeaveService;
import com.kevin.epacms.util.Result;
import com.kevin.epacms.util.SearchUtil;
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
@Api(tags = "离校管理API")
@CrossOrigin
@RestController
@RequestMapping("/api/leave")
public class LeaveController {
    @Resource
    private LeaveService leaveService;

    @ApiOperation("根据查询条件查询离校记录")
    @PostMapping("/page")
    public Result getLeaveBySearch(@RequestBody LeaveDto leaveDto,
                                   @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "15") Integer pageSize){
        return leaveService.getLeaveBySearch(currentPage, pageSize, leaveDto);
    }

    @ApiOperation("添加或更新离校申请")
    @PostMapping
    public Result addOrUpdateLeave(@RequestBody Leave leave){
        return leaveService.addOrUpdateLeave(leave);
    }

    @ApiOperation("删除离校申请")
    @DeleteMapping
    public Result deleteLeave(@RequestParam("id") Long id){
        return leaveService.deleteLeave(id);
    }

    @ApiOperation("批量删除离校申请")
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestParam("idsStr") String idsStr){
        return leaveService.deleteBatch(idsStr);
    }
}
