package com.kevin.epacms.service;

import com.kevin.epacms.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kevin.epacms.entity.vo.ConditionVo;
import com.kevin.epacms.util.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
public interface RecordService extends IService<Record> {

    /**
     * 打卡记录
     *
     * @param record 记录
     * @return {@link Result}
     */
    Result addRecord(Record record);

    /**
     * 删除记录
     *
     * @param id id
     * @return {@link Result}
     */
    Result delRecord(Long id);

    /**
     * 批量删除记录
     *
     * @param recordIdsStr 记录id str
     * @return {@link Result}
     */
    Result delBatchRecords(String recordIdsStr);

    /**
     * 判断学生今日是否打卡
     *
     * @param uid uid
     * @return {@link Result}
     */
    Result isRecord(Long uid);

    /**
     * 根据用户和角色分页查询打卡记录
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param conditions  条件
     * @return {@link Result}
     */
    Result getRecordsPageByCondition(Integer currentPage, Integer pageSize, List<ConditionVo> conditions);
}
