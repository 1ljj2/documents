package org.jit.sose.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wufang
 * @Date 2020-09-22 13:35:47
 */
@ApiModel(value = "listProcessParam", description = "分页查询参数封装流程")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListProcessParam {

    @ApiModelProperty(value = "当前页索引", required = true)
    private Integer pageNum;

    @ApiModelProperty(value = "当前页页面大小", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "所属分类标识")
    private Integer categoryId;

    @ApiModelProperty(value = "服务角色标识")
    private Integer roleId;

    @ApiModelProperty(value = "流程名称搜索值")
    private String processName;

    @ApiModelProperty(value = "用户所属角色的标识集合")
    private List<Integer> roleIdList;

}
