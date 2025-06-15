package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jit.sose.domain.entity.Role;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserVo", description = "用户信息Vo")
public class UserVo {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("创建时间")
    private String createDate;

    @ApiModelProperty("微信名称")
    private String weixinName;

    @ApiModelProperty(value = "角色集合")
    private List<Role> roleList;

}