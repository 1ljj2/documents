package org.jit.sose.domain.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@ApiModel(value = "FileRoleCon", description = "文件角色关联")
@NoArgsConstructor
public class FileRoleCon {
    private Integer id;

    private Integer roleId;

    private Integer fileId;

    private String state;

    private Date stateTime;

}