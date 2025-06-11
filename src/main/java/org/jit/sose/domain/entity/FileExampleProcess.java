package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.NOT_EMPTY;

@Data
@NoArgsConstructor
@TableName(value = "t_file_example_process")
public class FileExampleProcess {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private Integer fileId;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private Integer processId;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private String auditState;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private String auditOpinion;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private String state;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private Date createTime;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private Date stateTime;

}