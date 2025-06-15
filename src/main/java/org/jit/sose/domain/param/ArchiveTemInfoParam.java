package org.jit.sose.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wufang
 * @Date 2020-10-07 18:48:43
 */
@ApiModel(value = "ArchiveTemInfoParam")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveTemInfoParam {

    @ApiModelProperty(value = "档案标识")
    private Integer id;

    @ApiModelProperty(value = "档案名称")
    private String name;

    @ApiModelProperty(value = "编号")
    private String number;

    @ApiModelProperty(value = "所属分类标识")
    private Integer categoryId;

    @ApiModelProperty(value = "所属学年学期标识")
    private Integer termId;

    @ApiModelProperty(value = "关联课程标识")
    private Integer courseId;

    @ApiModelProperty(value = "关联流程标识")
    private Integer processId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "档案的模板文件id集合")
    private List<Integer> fileIdList;

    @ApiModelProperty(value = "关联课程列表标识")
    private Integer courseListId;


    //添加参数
    private List<Integer> courseListS;


    //编辑参数
    private List<Integer> editcourseLists;
}
