package org.jit.sose.domain.param;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author jinyu: 
* @Date 2020年9月25日 下午4:58:35 
*  
*/
@ApiModel(value = "AddEditMessParam", description = "增加/修改个人消息的封装参数")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEditMessParam {
	
	@ApiModelProperty(value = "消息标识")
    private Integer id;
	private Integer messId;

    @NotNull
    @ApiModelProperty(value = "消息标题", required = true)
    private String title;

    @NotNull
    @ApiModelProperty(value = "消息内容", required = true)
    private String content;
    
    @NotNull
    @ApiModelProperty(value = "分类标识", required = true)
    private Integer categoryId;
    
    @ApiModelProperty(value = "发布部门标识", required = true)
    private Integer departmentId;

    @ApiModelProperty(value = "发布用户标识", required = true)
    private Integer userId;

    @ApiModelProperty(value = "面向对象", required = true)
	private List<Integer> roleId;
	
	@ApiModelProperty(value = "面向用户", required = true)
	private List<Integer> readUserId;
	
	@ApiModelProperty(value = "文件标识", required = true)
    private Integer fileId;
}
 