package org.jit.sose.domain.param;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author jinyu: 
* @Date 2020年9月24日 下午4:51:51 
*  
*/
@ApiModel(value = "AddNoticeParam", description = "增加/修改公告信息的封装参数")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNoticeParam {
	
	@ApiModelProperty(value = "公告标识")
    private Integer id;
	private Integer noticeId;

    @NotNull
    @ApiModelProperty(value = "公告标题", required = true)
    private String title;

    @NotNull
    @ApiModelProperty(value = "公告内容", required = true)
    private String content;
    
    @ApiModelProperty(value = "发布部门标识", required = true)
    private Integer departmentId;

    @ApiModelProperty(value = "发布用户标识", required = true)
    private Integer userId;
    
    @ApiModelProperty(value = "分类标识", required = true)
    private Integer categoryId;
    
    @ApiModelProperty(value = "文件标识", required = true)
    private Integer fileId;
}
 