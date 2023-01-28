package com.kevin.epacms.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kevin.epacms.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface RecordMapper extends BaseMapper<Record> {
    /**
     * 根据用户和角色分页查询打卡记录
     *
     * @param wrapper 包装器
     * @param page    页面
     * @return {@link IPage}<{@link Record}>
     */
    IPage<Record> getRecordByCondition(Page<?> page, @Param(Constants.WRAPPER) QueryWrapper<Record> wrapper);
}
