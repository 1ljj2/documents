package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

import org.jit.sose.domain.entity.Role;
import org.jit.sose.domain.entity.User;

/** 
* @author jinyu: 
* @Date 2020年9月22日 上午11:04:10 
*  
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ListMessVo", description = "【根据条件】查询消息返回信息")
public class ListMessVo {
	
	@ApiModelProperty("主键标识")
    private Integer id;
	
	@ApiModelProperty("标题")
	private String title;

	@ApiModelProperty("内容")
    private String content;

	@ApiModelProperty("发布用户")
    private Integer userId;
	private String userName;

	@ApiModelProperty("面向对象")
	private List<Integer> roleId;
//	private String roleName;
	private List<ListRoleByMessVo> role;
	
	@ApiModelProperty("面向用户")
	private List<Integer> readUserId;
//	private String readUserName;
	private List<ListRoleByMessVo> readUser;
	
	@ApiModelProperty("分类")
    private Integer categoryId;
    private String categoryName;
	
	@ApiModelProperty("附件")
    private Integer fileId;
    private String accessUrl;
    private String fileName;

	@ApiModelProperty("状态")
    private String state;
	
	@ApiModelProperty("是否已读")
	private String isRead;

	@ApiModelProperty("创建日期")
	private String createTimeString;
    private Timestamp createTime;

	@ApiModelProperty("更新日期")
	private String stateTimeString;
    private Timestamp stateTime;

    private ListDepartmentVo department;

}
 