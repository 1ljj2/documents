package org.jit.sose.domain.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@ApiModel(value = "ArchiveTemplateEmpFile")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ArchiveTemplateEmpFile {

    private Integer id;

    private Integer archiveTemplateId;

    private Integer fileId;

    private Integer seq;

    private String remark;

    private String state;

    private Date stateTime;

}