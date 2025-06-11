package org.jit.sose.domain.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "ProcessSteps")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessSteps {
    private Integer id;

    private String stepName;

    private Integer pStepId;

    private Integer roleId;

    private Integer isSign;

    private String remark;

    private String state;

    private Date createTime;

    private Date stateDate;

}