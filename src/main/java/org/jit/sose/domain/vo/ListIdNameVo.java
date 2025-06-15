package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author wufang
 * @Date 2020-08-05 12:06:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ListIdNameVo")
public class ListIdNameVo {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "学年学期名称")
    private String name;
    
    @ApiModelProperty(value = "更新日期")
    private Timestamp stateDate;
    private String stateDateString;

    private String parentId;

    private List<ListIdNameVo> children;

}
