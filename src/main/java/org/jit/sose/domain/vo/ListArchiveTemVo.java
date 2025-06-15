package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wufang
 * @Date 2020-10-07 15:15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListArchiveTemVo {
    @ApiModelProperty("主键标识")
    private Integer id;

    @ApiModelProperty("档案名称")
    private String name;

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

    @ApiModelProperty("流程")
    private Integer processId;
    private String  processName;

    @ApiModelProperty("流程")
    private Integer userId;
    private String  userName;

    @ApiModelProperty("更新时间")
    private String stateTimeString;

    @ApiModelProperty("审核状态")
    private String auditState;

    @ApiModelProperty("课程还是课程列表")
    private String parentId;


    private String archiveState;


    //课程idd 和 姓名
    private List<Integer> allCoursesList;
    private List<String> allCourseNames;

}
