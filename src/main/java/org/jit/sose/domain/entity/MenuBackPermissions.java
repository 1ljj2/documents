package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("a_menu_back_permissions")
@EqualsAndHashCode(callSuper=true)
public class MenuBackPermissions extends Model<MenuBackPermissions> {

    private static final long serialVersionUID = -2304299819190299235L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("后台菜单Id")
    private Integer menuBackId;

    @ApiModelProperty("请求权限Id")
    private Integer permissionId;

    @ApiModelProperty("创建时间")
    @TableField(select = false)
    private Timestamp createDate;

    @ApiModelProperty("更新时间")
    @TableField(select = false)
    private Timestamp updateDate;

    @ApiModelProperty("状态")
    @TableField(select = false)
    private String state;

}