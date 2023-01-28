package com.kevin.epacms.service;

import com.kevin.epacms.entity.Notice;
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
public interface NoticeService extends IService<Notice> {

    /**
     * 根据id查询通知详情
     *
     * @param id   通知id
     * @param type 类型（md，html）
     * @return 通知详情
     */
    Result getNoticeById(Integer id, String type);

    /**
     * 添加或更新通知
     *
     * @param notice 通知信息
     * @return {@link Result}
     */
    Result addOrUpdateNotice(Notice notice);

    /**
     * 根据id删除通知
     *
     * @param id id
     * @return {@link Result}
     */
    Result delNotice(Long id);

    /**
     * 批量删除通知
     *
     * @param idsStr ids str
     * @return {@link Result}
     */
    Result delBatch(String idsStr);

    /**
     * 根据用户id分页查询
     *
     * @param currentPage   当前页面
     * @param pageSize      页面大小
     * @param conditions    查询条件
     * @return {@link Result}
     */
    Result getNoticesByCondition(Integer currentPage, Integer pageSize, List<ConditionVo> conditions);
}
