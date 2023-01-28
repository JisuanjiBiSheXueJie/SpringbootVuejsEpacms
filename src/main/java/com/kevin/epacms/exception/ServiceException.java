package com.kevin.epacms.exception;

import lombok.Getter;

/** 自定义异常
 * @author: kevin
 * @since : 9:08 2022/5/7
 */
@Getter
public class ServiceException extends RuntimeException{
    private final Integer code;

    public ServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
