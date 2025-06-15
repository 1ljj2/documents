package org.jit.sose.domain.vo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author jinyu: 
* @Date 2020年10月18日 上午11:48:51 
*  
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ListChargingArchiveVo", description = "【根据条件】查询档案绑定文件返回信息")
public class ListChargingArchiveVo {
	
	@ApiModelProperty(value = "主键")
    private Integer id;
	
	@ApiModelProperty(value = "用户档案编号")
    private String userArchiveNumber;

    @ApiModelProperty(value = "用户档案名称")
    private String userArchiveName;
    
    @ApiModelProperty(value = "负责人")
    private Integer leaderId;
    private String leaderName;

    @ApiModelProperty(value = "用户标识")
    private Integer userId;
    private String userName;

    @ApiModelProperty(value = "档案模板")
    private Integer archiveTemplateId;
    private String archiveTemplateName;

    @ApiModelProperty(value = "流程")
    private Integer processId;
    private String processName;

    @ApiModelProperty(value = "审核状态")
    private String auditState;

    @ApiModelProperty(value = "审核意见")
    private String auditOpinion;

    @ApiModelProperty(value = "是否禁用")
    private Boolean isEnable;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "状态日期")
    private String stateTimeString;
    private Timestamp stateTime;

    @ApiModelProperty(value = "文档")
    private List<Integer> fileIds;
//    private Integer fileId;
//    private String fileName;

}
 