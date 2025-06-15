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
@ApiModel(value = "FileTermCon", description = "文件学期关联")
@TableName(value = "t_file_term_con")//指定表名
@NoArgsConstructor
public class FileTermCon {
    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
    private Integer id;


    private Integer termId;

    private Integer fileId;

    @TableField(insertStrategy = NOT_EMPTY)
    private String state;

    @TableField(insertStrategy = NOT_EMPTY)
    private Date stateTime;

}