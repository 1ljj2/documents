package org.jit.sose.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author jinyu: 
* @Date 2020年9月23日 下午5:53:37 
*  
*/
@ApiModel(value = "ListNoticeParam", description = "分页查询参数封装消息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListNoticeParam {
	
	@ApiModelProperty(value = "当前页索引", required = true)
    private Integer pageNum;

    @ApiModelProperty(value = "当前页页面大小", required = true)
    private Integer pageSize;
    
    @ApiModelProperty(value = "公告标识", required = true)
    private Integer Id;
    
    @ApiModelProperty(value = "标题", required = true)
    private String title;
    
    @ApiModelProperty(value = "发布部门标识", required = true)
    private Integer departmentId;

    @ApiModelProperty(value = "发布用户标识", required = true)
    private Integer userId;
    
    @ApiModelProperty(value = "分类", required = true)
    private Integer categoryId;
    
    @ApiModelProperty("审核用户标识")
    private Integer auditId;
}
 