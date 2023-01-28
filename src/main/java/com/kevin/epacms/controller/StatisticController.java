package com.kevin.epacms.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kevin.epacms.constant.CommonConstant;
import com.kevin.epacms.entity.Leave;
import com.kevin.epacms.entity.Record;
import com.kevin.epacms.entity.dto.LeaveMap;
import com.kevin.epacms.entity.dto.RecordMap;
import com.kevin.epacms.entity.vo.StatisticsVo;
import com.kevin.epacms.service.*;
import com.kevin.epacms.util.RedisUtil;
import com.kevin.epacms.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author kevin
 * @date 2022/11/22 21:39
 */
@Api(tags = "统计管理API")
@CrossOrigin
@RestController
@RequestMapping("/api/statistics")
public class StatisticController {
    @Resource
    private UserService userService;
    @Resource
    private RecordService recordService;
    @Resource
    private LeaveService leaveService;
    @Resource
    private UploadService uploadService;
    @Resource
    private NewsService newsService;
    @Resource
    private RedisUtil redisUtil;

    @ApiOperation("统计数据")
    @GetMapping
    public Result statisticTotal(){
        StatisticsVo statisticsVo = new StatisticsVo();
        statisticsVo.setUserTotal(userService.count());
        statisticsVo.setRecordTotal(recordService.count());
        statisticsVo.setNewsTotal(newsService.count());
        statisticsVo.setFileTotal(uploadService.count());
        return Result.ok(statisticsVo);
    }

    @ApiOperation("查询每月的打卡数和离校数")
    @GetMapping("/month/recordAndLeave")
    public Result getRecordTotalByMonth(){
        String key = CommonConstant.STATISTICS_PREFIX + "recordAndLeave";
        String statisticsData = redisUtil.get(key);
        if (StrUtil.isNotBlank(statisticsData)) {
            return Result.ok(JSONUtil.parseObj(statisticsData));
        }
        List<Record> recordList = recordService.query()
                .select("count(*) total, DATE_FORMAT( time, '%Y-%m' ) date ")
                .groupBy("date").list();
        List<RecordMap> recordMaps = RecordMap.init();
        for (Record record : recordList) {
            String date = record.getDate();
            for (RecordMap resultMap : recordMaps) {
                if(Objects.equals(date, resultMap.getDate())){
                    resultMap.setTotal(record.getTotal());
                }
            }
        }
        List<Leave> leaveList = leaveService.query()
                .select("count(*) total, DATE_FORMAT( apply_time, '%Y-%m' ) date ")
                .groupBy("date").list();
        List<LeaveMap> leaveMaps = LeaveMap.init();
        for (Leave leave : leaveList) {
            String date = leave.getDate();
            for (LeaveMap resultMap : leaveMaps) {
                if(Objects.equals(date, resultMap.getDate())){
                    resultMap.setTotal(leave.getTotal());
                }
            }
        }
        JSONObject resultJson = new JSONObject(2);
        resultJson.set("records", recordMaps);
        resultJson.set("leaves", leaveMaps);
        redisUtil.set(key, resultJson, 5L, TimeUnit.MINUTES);
        return Result.ok(resultJson);
    }
}
