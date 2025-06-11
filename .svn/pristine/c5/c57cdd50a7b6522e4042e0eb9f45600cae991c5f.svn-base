package org.jit.sose.domain.vo;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author jinyu: 
* @Date 2020年10月20日 下午2:04:25 
*  
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ListConfigVo", description = "【根据条件】查询配置管理信息")
public class ListConfigVo {

	@ApiModelProperty("主键标识")
    private Integer id;
	
	@ApiModelProperty("配置项名称")
    private String name;
	
	@ApiModelProperty("描述")
    private String description;
	
	@ApiModelProperty("配置项值")
    private String value;
	
	@ApiModelProperty("是否禁用")
    private String isEnable;
	
	@ApiModelProperty("状态")
    private String state;

	@ApiModelProperty("创建日期")
	private String createTimeString;
    private Timestamp createTime;

	@ApiModelProperty("更新日期")
	private String stateTimeString;
    private Timestamp stateTime;
}
 