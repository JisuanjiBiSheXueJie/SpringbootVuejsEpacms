package com.kevin.epacms.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kevin.epacms.entity.vo.ConditionVo;

import java.util.List;
import java.util.Objects;

/**
 * 查询工具类
 *
 * @author kevin
 * @since 2022/11/12 8:59
 */
public class SearchUtil {
    public static <T> QueryWrapper<T> parseWhereSql(List<ConditionVo> conditions){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(conditions) && !conditions.isEmpty()) {
            for (ConditionVo condition : conditions) {
                switch (condition.getType()){
                    case "eq":
                        queryWrapper.eq(condition.getColumn(), condition.getValue());
                        break;
                    case "ne":
                        queryWrapper.ne(condition.getColumn(), condition.getValue());
                        break;
                    case "like":
                        queryWrapper.like(condition.getColumn(), condition.getValue());
                        break;
                    case "likeLeft":
                        queryWrapper.likeLeft(condition.getColumn(), condition.getValue());
                        break;
                    case "likeRight":
                        queryWrapper.likeRight(condition.getColumn(), condition.getValue());
                        break;
                    case "notLike":
                        queryWrapper.notLike(condition.getColumn(), condition.getValue());
                        break;
                    case "gt":
                        queryWrapper.gt(condition.getColumn(), condition.getValue());
                        break;
                    case "lt":
                        queryWrapper.lt(condition.getColumn(), condition.getValue());
                        break;
                    case "ge":
                        queryWrapper.ge(condition.getColumn(), condition.getValue());
                        break;
                    case "le":
                        queryWrapper.le(condition.getColumn(), condition.getValue());
                        break;
                    default:
                        return queryWrapper;
                }
            }
        }
        return queryWrapper;
    }
}
