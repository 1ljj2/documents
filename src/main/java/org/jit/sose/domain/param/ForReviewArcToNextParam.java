package org.jit.sose.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wufang
 * @Date 2020-10-20 10:54:58
 */
@ApiModel(value = "ForReviewArcToNextParam", description = "档案审核送审下一个人审核的封装参数")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForReviewArcToNextParam {


    @ApiModelProperty(value = "用户标识", required = true)
    private Integer arcAuditId;
    @ApiModelProperty(value = "用户标识", required = true)
    private Integer userId;
    @ApiModelProperty(value = "发布用户标识", required = true)
    private Integer stepId;
    @ApiModelProperty(value = "发布用户标识", required = true)
    private String  auditState;
    @ApiModelProperty(value = "发布用户标识", required = true)
    private String  opinion;
    @ApiModelProperty(value = "发布用户标识")
    private Integer  exampleId;

}
