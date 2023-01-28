package com.kevin.epacms.constant;

/**
 * @author: kevin
 * @since: 13:11 2022/5/7
 */
public interface JwtVerifyConst {
    String SUCCESS = "token验证成功";
    String EXPIRED = "token已过期";
    String SIGNATURE_VERIFICATION = "token签名失败";
    String DECODE_ERROR = "token解析失败，请重新登录获取token";
    String NOT_LOGIN = "未登录";
}
