package com.kevin.epacms.service;

import com.kevin.epacms.entity.Grade;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kevin.epacms.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
public interface GradeService extends IService<Grade> {

    /**
     * 获取所有班级信息
     * @return 所有班级信息
     */
    Result getAllGrade();
}
