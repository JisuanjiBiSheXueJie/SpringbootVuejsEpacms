package com.kevin.epacms.controller;

import cn.hutool.core.bean.BeanUtil;
import com.kevin.epacms.entity.Record;
import com.kevin.epacms.entity.dto.RecordDto;
import com.kevin.epacms.entity.dto.SearchDto;
import com.kevin.epacms.service.RecordService;
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
@Api(tags = "打卡管理API")
@CrossOrigin
@RestController
@RequestMapping("/api/record")
public class RecordController {
    @Resource
    private RecordService recordService;

    @ApiOperation("根据用户和角色分页查询打卡记录")
    @PostMapping("/page")
    public Result getRecordsPageByCondition(@RequestBody SearchDto searchConditions,
                                      @RequestParam(name = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize){
        return recordService.getRecordsPageByCondition(currentPage, pageSize, searchConditions.getConditions());
    }
    @ApiOperation("判断学生今日是否打卡")
    @GetMapping("/today")
    public Result isRecord(@RequestParam("uid") Long uid){
        return recordService.isRecord(uid);
    }

    @ApiOperation("打卡记录")
    @PostMapping
    public Result addRecord(@RequestBody RecordDto recordDto){
        Record record = BeanUtil.toBean(recordDto, Record.class);
        return recordService.addRecord(record);
    }

    @ApiOperation("删除记录")
    @DeleteMapping
    public Result delRecord(@RequestParam("id") Long id){
        return recordService.delRecord(id);
    }

    @ApiOperation("批量删除记录")
    @DeleteMapping("/batch")
    public Result delBatchRecords(@RequestParam("recordIdsStr") String recordIdsStr){
        return recordService.delBatchRecords(recordIdsStr);
    }
}
