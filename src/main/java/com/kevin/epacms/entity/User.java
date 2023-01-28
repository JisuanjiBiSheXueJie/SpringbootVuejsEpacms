package com.kevin.epacms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kevin.epacms.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
@Data
@TableName("t_user")
@ApiModel(value = "User对象")
public class User implements Serializable , UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("学号")
    @TableField("sno")
    private String sno;

    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty("真实姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("性别")
    @TableField("sex")
    private String sex;

    @ApiModelProperty("手机号码")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("宿舍号")
    @TableField("dormitory_num")
    private String dormitoryNum;

    @ApiModelProperty("民族")
    @TableField("nation")
    private String nation;

    @ApiModelProperty("班级号")
    @TableField("class_num")
    private String classNum;

    @ApiModelProperty("院系")
    @TableField("academy")
    private String academy;

    @ApiModelProperty("图片url")
    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty("是否禁用")
    @TableField("enable")
    private Integer enable;

    @ApiModelProperty("注册时间")
    @TableField("register_time")
    @JsonFormat(pattern = CommonConstant.DATETIME_FORMAT)
    private LocalDateTime registerTime;

    @ApiModelProperty("角色id")
    @TableField("rid")
    private Integer rid;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
