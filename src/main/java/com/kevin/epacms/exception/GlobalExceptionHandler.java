package com.kevin.epacms.exception;

import com.kevin.epacms.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringJoiner;

/**
 * @author: kevin
 * @since : 9:06 2022/5/7
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 如果抛出的是ServiceException，则调用该方法
     * @param se 业务异常
     * @return 异常信息
     */
    @ExceptionHandler(ServiceException.class)
    public Result handle(ServiceException se){
        return Result.fail(se.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public Result usernameNotFound(UsernameNotFoundException e){
        return Result.fail(HttpStatus.BAD_REQUEST.value(),e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValid(MethodArgumentNotValidException e){
        log.error("数据校验出现问题：{}，异常类型：{}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        StringJoiner stringJoiner = new StringJoiner(",");
        bindingResult.getFieldErrors().forEach(fieldError -> {
            // 获取错误信息
            String message = fieldError.getDefaultMessage();
            // 获取错误的属性名字
            //String field = fieldError.getField();
            stringJoiner.add(message);
        });
        return Result.fail(HttpStatus.BAD_REQUEST.value(), stringJoiner.toString());
    }
}
