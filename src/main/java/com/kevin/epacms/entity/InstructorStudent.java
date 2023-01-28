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
@TableName("t_instructor_student")
@ApiModel(value = "InstructorStudent对象", description = "")
public class InstructorStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("instructor_id")
    private Integer instructorId;

    @TableField("student_id")
    private Integer studentId;
}
