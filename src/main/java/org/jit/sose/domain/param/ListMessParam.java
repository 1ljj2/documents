package org.jit.sose.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author jinyu: 
* @Date 2020年9月22日 上午11:01:03 
*  
*/
@ApiModel(value = "ListMessParam", description = "分页查询参数封装消息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListMessParam {
	
	@ApiModelProperty(value = "当前页索引", required = true)
    private Integer pageNum;

    @ApiModelProperty(value = "当前页页面大小", required = true)
    private Integer pageSize;
    
    @ApiModelProperty(value = "消息标识", required = true)
    private Integer Id;
    
    @ApiModelProperty(value = "标题", required = true)
    private String title;
    
    @ApiModelProperty(value = "发布部门标识", required = true)
    private Integer departmentId;

    @ApiModelProperty(value = "发布用户标识", required = true)
    private Integer userId;
    
    @ApiModelProperty(value = "面向用户标识", required = true)
    private Integer readUserId;
    
    @ApiModelProperty(value = "分类", required = true)
    private Integer categoryId;

}
 