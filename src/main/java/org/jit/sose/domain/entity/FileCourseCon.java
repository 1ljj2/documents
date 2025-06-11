package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.NOT_EMPTY;

@Data
@ApiModel(value = "FileCourseCon")
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_file_course_con")//指定表名
@EqualsAndHashCode(callSuper = false)
public class FileCourseCon {
    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
    private Integer id;


    private Integer fileInfoId;

    private Integer courseId;

    @TableField(insertStrategy = NOT_EMPTY)
    private String state;

    @TableField(insertStrategy = NOT_EMPTY)
    private Date stateTime;
}