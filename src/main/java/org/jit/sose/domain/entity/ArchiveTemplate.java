package org.jit.sose.domain.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@ApiModel(value = "ArchiveTemplate")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ArchiveTemplate {
    private Integer id;

    private String number;

    private String name;

    private Integer termId;

    private Integer courseId;

    private String remark;

    private String state;

    private Date createTime;

    private Date stateTime;

    private Integer courseListId;

}