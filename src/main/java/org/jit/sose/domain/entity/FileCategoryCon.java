package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.NOT_EMPTY;

@Data
@ApiModel(value = "FileCategoryCon", description = "文件分类关联")
@TableName(value = "t_file_category_con")//指定表名
@NoArgsConstructor
public class FileCategoryCon {
    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
    private Integer id;

    @TableField(insertStrategy = NOT_EMPTY ,updateStrategy = NOT_EMPTY)
    private Integer categoryId;

    @TableField(insertStrategy = NOT_EMPTY ,updateStrategy = NOT_EMPTY)
    private Integer fileId;

    @TableField(select = false , insertStrategy = NOT_EMPTY ,updateStrategy = NOT_EMPTY)
    private String state;

    @TableField(select = false , insertStrategy = NOT_EMPTY ,updateStrategy = NOT_EMPTY)
    private Date stateTime;

}