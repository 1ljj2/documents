package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@ApiModel(value = "ArchiveExampleFile", description = "档案实例文件")
@TableName(value = "t_archive_example_file")
@EqualsAndHashCode(callSuper = false)
public class ArchiveExampleFile {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "档案实例流程标识")
    private Integer exampleId;

    @ApiModelProperty(value = "空文档标识")
    private Integer emptyFileId;

    @ApiModelProperty(value = "文档标识")
    private Integer fileId;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "状态时间")
    private Date stateTime;

}