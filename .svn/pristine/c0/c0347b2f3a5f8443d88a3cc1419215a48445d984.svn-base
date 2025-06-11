package org.jit.sose.domain.vo;

import java.sql.Timestamp;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author jinyu: 
* @Date 2020年9月23日 下午6:00:01 
*  
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ListNoticeVo", description = "【根据条件】查询公告返回信息")
public class ListNoticeVo {

	@ApiModelProperty("主键标识")
    private Integer id;
	
	@ApiModelProperty("标题")
	private String title;

	@ApiModelProperty("内容")
    private String content;

	@ApiModelProperty("发布用户")
    private Integer userId;
	private String userName;

	@ApiModelProperty("发布部门")
    private ListDepartmentVo department;
    
	@ApiModelProperty("分类")
    private Integer categoryId;
    private String categoryName;
	
	@ApiModelProperty("附件")
    private Integer fileId;
    private String accessUrl;
    private String fileName;

	@ApiModelProperty("状态")
    private String state;
	
	@ApiModelProperty("排序")
	private Integer seq;

	@ApiModelProperty("审核状态")
    private String auditState;

    @ApiModelProperty("是否禁用")
    private String isEnable;

	@ApiModelProperty("创建日期")
	private String createTimeString;
    private Timestamp createTime;

	@ApiModelProperty("更新日期")
	private String stateTimeString;
    private Timestamp stateTime;
    
    @ApiModelProperty("更新日期")
    private Timestamp keepNewTime;
    
    @ApiModelProperty("审核用户标识")
    private Integer auditId;
    
    @ApiModelProperty("新公告个数")
    private Integer count;
    
    @ApiModelProperty("待审核公告个数")
    private Integer noticeAuditCount;
    
    @ApiModelProperty("是否启用公告审核")
    private String noticeAudit;
    
    @ApiModelProperty("是否启用短信审核")
    private String message4Audit;
    
}
 