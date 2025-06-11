package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LJH
 * @Date: 2020/10/13 14:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListArchiveFilePoolVo {
    @ApiModelProperty("档案模板与文档关联表的标识")
    private Integer archTemFileId;

    @ApiModelProperty("模板文件标识")
    private Integer temFileId;

    @ApiModelProperty("用户上传文件标识")
    private Integer userFileId;

    @ApiModelProperty("审核流程标识")
    private Integer processId;

    @ApiModelProperty("模板文件名称")
    private String temFileName;

    @ApiModelProperty("用户上传文件名称")
    private String userFileName;

    @ApiModelProperty("模板文档编号")
    private String temNumber;

    @ApiModelProperty("用户文档编号")
    private String userNumber;

    @ApiModelProperty("文档更新时间")
    private String temStateTimeString;

    @ApiModelProperty("用户更新时间")
    private String userStateTimeString;
}
