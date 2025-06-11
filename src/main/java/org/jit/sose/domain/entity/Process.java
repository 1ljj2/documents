package org.jit.sose.domain.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@ApiModel(value = "Process")
@AllArgsConstructor
@NoArgsConstructor
public class Process {

    private Integer id;

    private String processName;

    private Integer processStepId;

    private Integer roleId;

    private Boolean isEnable;

    private String state;

    private String remark;

    private Timestamp createTime;

    private Timestamp stateDate;
}