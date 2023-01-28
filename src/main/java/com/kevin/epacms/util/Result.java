package com.kevin.epacms.util;

import cn.hutool.core.util.StrUtil;
import com.kevin.epacms.constant.CommonConstant;
import com.kevin.epacms.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 统一返回结果
 *
 * @author kevin
 * @since 2022/10/5 12:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编码 */
    private Integer code;
    /** 返回消息 */
    private String message;
    /** 返回数据 */
    private Object data;

    /**
     * 返回成功结果
     *
     * @return {@link Result}
     */
    public static Result ok(){
        return Result.ok(CommonConstant.SUCCESS, null);
    }

    /**
     * 返回成功结果
     *
     * @param message 消息
     * @return {@link Result}
     */
    public static Result ok(String message){
        return Result.ok(message, null);
    }
    public static Result ok(Object data) {
        return Result.ok(CommonConstant.SUCCESS, data);
    }
    /**
     * 返回成功结果，携带数据
     *
     * @param message 消息
     * @param data    数据
     * @return {@link Result}
     */
    public static Result ok(String message, Object data){
        return Result.builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .data(data)
                .build();
    }

    /**
     * 返回失败结果
     *
     * @param message 消息
     * @return {@link Result}
     */
    public static Result fail(String message){
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }

    /**
     * 返回失败结果
     *
     * @param message 消息
     * @param code    代码
     * @return {@link Result}
     */
    public static Result fail(Integer code, String message){
        return Result.fail(code, message, null);
    }

    /**
     * 返回失败结果，携带数据
     *
     * @param message 消息
     * @param data    数据
     * @param code    代码
     * @return {@link Result}
     */
    public static Result fail(Integer code, String message, Object data){
        return Result.builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }


}