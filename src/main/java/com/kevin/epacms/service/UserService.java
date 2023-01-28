package com.kevin.epacms.service;

import com.kevin.epacms.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kevin.epacms.entity.dto.LoginParam;
import com.kevin.epacms.entity.dto.RegisterParam;
import com.kevin.epacms.entity.dto.UserDto;
import com.kevin.epacms.entity.vo.ConditionVo;
import com.kevin.epacms.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
public interface UserService extends IService<User> {

    /**
     * 通过用户名获取用户
     *
     * @param username 用户名
     * @return {@link User}
     */
    User getUserByUsername(String username);

    /**
     * 更新用户信息
     * @param user 用户信心
     * @return 结果
     */
    Result updateUser(User user);

    /**
     * 批量删除用户信息
     * @param userIdsStr 用户id字符串（用“,”拼接）
     * @return 是否删除成功
     */
    Result deleteUsers(String userIdsStr);

    /**
     * 分页查询用户信息
     *
     * @param currentPage   当前页
     * @param pageSize      页数
     * @param conditions    查询条件
     * @return 结果
     */
    Result getUsersByPage(Integer currentPage, Integer pageSize, List<ConditionVo> conditions);

    /**
     * 导出用户信息
     * @param response 响应
     */
    void export(HttpServletResponse response);

    /**
     * 是否存在电子邮件
     *
     * @param email 电子邮件
     * @return boolean
     */
    boolean isExistEmail(String email);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return {@link Result}
     */
    Result deleteUserById(Long id);

    /**
     * 登录
     *
     * @param loginParam 登录参数
     * @param request  请求
     * @return {@link Result}
     */
    Result login(LoginParam loginParam, HttpServletRequest request);

    /**
     * 用户注册
     *
     * @param registerParam 注册参数
     * @return {@link Result}
     */
    Result register(RegisterParam registerParam);

    /**
     * 用户名是否已经注册
     *
     * @param username 用户名
     * @return {@link Result}
     */
    Result isExistUser(String username);

    /**
     * 通过用户名获取当前用户信息
     *
     * @param username 用户名
     * @return {@link UserDto}
     */
    UserDto getCurrentUserByUsername(String username);

    /**
     * 更改密码
     *
     * @param password 密码
     * @return {@link Result}
     */
    Result changePassword(String password);

    /**
     * 重置密码
     *
     * @param uid uid
     * @return {@link Result}
     */
    Result resetPassword(Long uid);
}
