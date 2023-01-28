package com.kevin.epacms.service;

import com.kevin.epacms.util.Result;

/**
 * @author kevin
 * @since 2022/11/11 21:26
 */
public interface MailService {
    /**
     * 邮箱是否已存在
     *
     * @param email 电子邮件
     * @return {@link Result}
     */
    Result isExistEmail(String email);

    /**
     * 获取验证码
     *
     * @param email 电子邮件
     * @return {@link Result}
     */
    Result getCode(String email);

    /**
     * 通知该同学打卡
     *
     * @param email 电子邮件
     * @return {@link Result}
     */
    Result noticeEmail(String email);
}
