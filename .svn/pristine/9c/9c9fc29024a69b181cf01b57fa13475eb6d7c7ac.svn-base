package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.jit.sose.constant.RedisConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "MenuBack", description = "后台菜单")
@TableName("a_menu_back")
@EqualsAndHashCode(callSuper=true)
public class MenuBack extends Model<MenuBack> {

    private static final long serialVersionUID = 8107634885506167821L;

    public MenuBack(MenuBack param) {
        this.id = param.getId();
        this.enable = param.isEnable();
        this.url = param.getUrl();
        this.name = param.getName();
        this.parentId = param.getParentId();
        this.icon = param.getIcon();
        this.type = param.getType();
        switch (this.type) {
            case "dir":
                this.level = 1;
                this.parentId = 0;
                break;
            case "menu":
                this.level = 2;
                break;
            case "button":
                this.level = 3;
                this.icon = null;
                this.url = null;
                break;
        }
        this.seq = param.getSeq();
    }

    public interface Insert {
    }

    public interface Update {
    }

    @NotNull(groups = Update.class)
    @ApiModelProperty("主键")
    private Integer id;

    @Length(max = 100, message = "长度最大为100", groups = { Insert.class, Update.class })
    @ApiModelProperty("请求路径")
    private String url;

    @NotBlank
    @Length(max = 10, message = "长度最大为100", groups = { Insert.class, Update.class })
    @ApiModelProperty("菜单名称")
    private String name;

    @NotNull(groups = Insert.class)
    @ApiModelProperty("父级菜单标识")
    private Integer parentId;

    @NotBlank(groups = Insert.class)
    @ApiModelProperty(value = "菜单类型", notes = "dir:目录\r\n" + "menu:菜单\r\n" + "button:按钮", example = "menu")
    private String type;

    @ApiModelProperty(value = "级别", notes = "目录为1级\r\n" + "菜单为2级\r\n" + "菜单中的操作为3级", example = "2")
    private Integer level;

    @Range(min = 0, max = 99, groups = { Insert.class, Update.class })
    @ApiModelProperty("菜单排序")
    private Integer seq;

    @ApiModelProperty("是否启用")
    @TableField("is_enable")
    private boolean enable;

    @Length(max = 100, message = "长度最大为100", groups = { Insert.class, Update.class })
    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("创建时间")
    @TableField(select = false)
    private Timestamp createDate;

    @ApiModelProperty("更新时间")
    @TableField(select = false)
    private Timestamp updateDate;

    @ApiModelProperty("状态")
    @TableField(select = false)
    private String state;

    @ApiModelProperty("子菜单集合")
    @TableField(exist = false)
    private List<MenuBack> menuList;

    /**
     * 获取缓存在redis中的后台菜单权限key，BACK:roleKey:menuUrl
     *
     * @param roleKey 角色key
     * @param menuUrl 菜单url
     * @return 菜单对应的权限key
     */
    public static String getRedisPermissionsKey(String roleKey, String menuUrl) {
        String key = RedisConstant.MENU_BACK_KEY_PREFIX + ":" + roleKey + ":" + menuUrl;
        return key;
    }

}