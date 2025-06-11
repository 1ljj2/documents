package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wufang
 * @Date 2020-10-08 14:10:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListMatchFileTemVo {

    @ApiModelProperty("主键标识")
    private Integer fileId;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("编号")
    private String number;
}
