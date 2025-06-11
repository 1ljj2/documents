package org.jit.sose.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author jinyu: 
* @Date 2020年10月20日 下午2:07:07 
*  
*/
@ApiModel(value = "ListConfigParam", description = "分页查询参数封装消息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListConfigParam {
	
	@ApiModelProperty(value = "当前页索引", required = true)
    private Integer pageNum;

    @ApiModelProperty(value = "当前页页面大小", required = true)
    private Integer pageSize;
    
    @ApiModelProperty("描述")
    private String description;
    
    @ApiModelProperty("是否禁用")
    private String isEnable;

}
 