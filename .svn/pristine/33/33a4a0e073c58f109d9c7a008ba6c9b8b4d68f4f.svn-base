package org.jit.sose.domain.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "Department", description = "部门")
@EqualsAndHashCode(callSuper = false)
public class Department {
    private Integer id;

    private String departmentName;
    private String parentName;

    private Integer parId;

    private Integer level;

    private String remark;

    private String state;

    private Date stateTime;

    List<Department> departmentList;

}