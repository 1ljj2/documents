package org.jit.sose.domain.vo;

import org.jit.sose.domain.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author jinyu: 
* @Date 2020年9月25日 下午7:29:46 
*  
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ListReadUserByMess", description = "【根据消息标识】查询面向用户返回参数封装")
public class ListReadUserByMess {
	
    @ApiModelProperty("主键标识")
    private Integer id;

    @ApiModelProperty("用户")
    private Integer userId;
    private String userName;
//    private User readUser;
    
    @ApiModelProperty("消息")
    private Integer messId;

	@ApiModelProperty(value = "新消息个数")
    private Integer count;

}
 