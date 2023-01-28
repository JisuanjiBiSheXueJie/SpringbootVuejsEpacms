package com.kevin.epacms.service;

import com.kevin.epacms.entity.Leave;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kevin.epacms.entity.dto.LeaveDto;
import com.kevin.epacms.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
public interface LeaveService extends IService<Leave> {

    /**
     * 删除离校申请
     *
     * @param id id
     * @return {@link Result}
     */
    Result deleteLeave(Long id);

    /**
     * 批量删除离校申请
     *
     * @param idsStr ids
     * @return {@link Result}
     */
    Result deleteBatch(String idsStr);

    /**
     * 添加或更新离校申请
     *
     * @param leave 离校信息
     * @return {@link Result}
     */
    Result addOrUpdateLeave(Leave leave);

    /**
     * 根据查询条件查询离校记录
     *
     * @param currentPage   当前页面
     * @param pageSize      页面大小
     * @param leaveDto      条件json
     * @return {@link Result}
     */
    Result getLeaveBySearch(Integer currentPage, Integer pageSize, LeaveDto leaveDto);
}
