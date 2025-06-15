package org.jit.sose.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LJH
 * @Date: 2020/10/3 11:02
 */
@ApiModel(value = "ListChargingFileParam", description = "分页查询参数封装文档监管")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListChargingFileParam {
    @ApiModelProperty(value = "当前页索引", required = true)
    private Integer pageNum;

    @ApiModelProperty(value = "当前页页面大小", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "所属分类标识")
    private Integer categoryId;

    @ApiModelProperty(value = "学期标识")
    private Integer termId;

    @ApiModelProperty(value = "文档名称搜索值")
    private String fileTemName;

    @ApiModelProperty(value = "文件状态")
    private String state;

    @ApiModelProperty("创建者")
    private Integer userId;
    private String  userName;
}
