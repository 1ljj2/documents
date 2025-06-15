package org.jit.sose.domain.vo;

import org.jit.sose.domain.entity.Role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author jinyu: 
* @Date 2020年9月25日 下午7:27:18 
*  
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ListRoleByMessVo", description = "【根据消息标识】查询服务对象返回参数封装")
public class ListRoleByMessVo {
	
    @ApiModelProperty("主键标识")
    private Integer id;

    @ApiModelProperty("角色")
    private Integer roleId;
    private String roleName;
    private String description;
//    private Role role;
    
    @ApiModelProperty("消息")
    private Integer messId;

}
 