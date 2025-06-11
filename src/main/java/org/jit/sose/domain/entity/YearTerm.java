package org.jit.sose.domain.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@ApiModel(value = "YearTerm", description = "学年学期")
public class YearTerm {

    private Integer id;

    private String termName;

    private String remark;

    private Timestamp stateDate;

    private String stateDateString;

    private String state;
}