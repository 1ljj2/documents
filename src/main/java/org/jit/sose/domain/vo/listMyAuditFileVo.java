package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LJH
 * @Date: 2020/10/19 9:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class listMyAuditFileVo {
    @ApiModelProperty("主键标识")
    private Integer fileId;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("编号")
    private String number;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("分类")
    private Integer categoryId;
    private String  categoryName;

    @ApiModelProperty("学期")
    private Integer termId;
    private String  termName;

    @ApiModelProperty("课程")
    private Integer courseId;
    private String  courseName;

    @ApiModelProperty("创建者id")
    private Integer userId;
    private String  userName;


    private String  auditState;


    @ApiModelProperty("更新时间")
    private String stateTimeString;
}
