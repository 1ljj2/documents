package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wufang
 * @Date 2020-10-08 18:28:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListFilePoolVo {

    @ApiModelProperty("档案模板与文档关联表的标识")
    private Integer archTemFileId;

    @ApiModelProperty("文件标识")
    private Integer fileId;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文档编号")
    private String number;

    @ApiModelProperty("文档更新时间")
    private String stateTimeString;

}
