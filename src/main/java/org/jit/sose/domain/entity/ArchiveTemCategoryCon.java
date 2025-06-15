package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@ApiModel(value = "ArchiveTemCategoryCon", description = "档案分类关联")
@TableName(value = "t_archive_tem_category_con")
@EqualsAndHashCode(callSuper = false)
public class ArchiveTemCategoryCon {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "类别标识")
    private Integer categoryId;

    @ApiModelProperty(value = "档案模板标识")
    private Integer archiveTemId;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "状态日期")
    private Date stateTime;

}