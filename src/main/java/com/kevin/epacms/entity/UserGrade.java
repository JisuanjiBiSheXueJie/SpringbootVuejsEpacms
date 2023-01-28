package com.kevin.epacms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2022-11-10
 */
@Getter
@Setter
@TableName("t_user_grade")
@ApiModel(value = "UserGrade对象", description = "")
public class UserGrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("班级id")
    @TableField("class_id")
    private Integer classId;
}
