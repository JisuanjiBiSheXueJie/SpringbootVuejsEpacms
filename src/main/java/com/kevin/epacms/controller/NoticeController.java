package com.kevin.epacms.controller;

import com.kevin.epacms.entity.Notice;
import com.kevin.epacms.entity.dto.SearchDto;
import com.kevin.epacms.entity.vo.ConditionVo;
import com.kevin.epacms.service.NoticeService;
import com.kevin.epacms.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  通知前端控制器
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
@Api(tags = "通知管理API")
@CrossOrigin
@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @ApiOperation("条件分页查询")
    @PostMapping("/page")
    public Result getNoticesByCondition(@RequestBody SearchDto searchConditions,
                                  @RequestParam(name = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize){
        return noticeService.getNoticesByCondition(currentPage, pageSize, searchConditions.getConditions());
    }

    @ApiOperation("根据id和类型查询通知详情")
    @GetMapping
    public Result getNoticeById(@RequestParam("id") Integer id,
                                @RequestParam("type") String type){
        return noticeService.getNoticeById(id, type);
    }

    @ApiOperation("新增或更新通知")
    @PostMapping
    public Result addOrUpdateNotice(@RequestBody Notice notice){
        return noticeService.addOrUpdateNotice(notice);
    }

    @ApiOperation("根据id删除通知")
    @DeleteMapping
    public Result delNotice(@RequestParam("id") Long id){
        return noticeService.delNotice(id);
    }

    @ApiOperation("批量删除通知")
    @DeleteMapping("/batch")
    public Result delBatch(@RequestParam("idsStr") String idsStr){
        return noticeService.delBatch(idsStr);
    }

}
