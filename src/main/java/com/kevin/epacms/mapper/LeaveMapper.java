package com.kevin.epacms.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kevin.epacms.entity.Leave;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevin.epacms.entity.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
public interface LeaveMapper extends BaseMapper<Leave> {
    /**
     * 由uid离校记录
     *
     * @param page    页面
     * @param wrapper 包装器
     * @return {@link IPage}<{@link Record}>
     */
    IPage<Leave> selectLeavePage(Page<?> page, @Param(Constants.WRAPPER) QueryWrapper<Leave> wrapper);

}
