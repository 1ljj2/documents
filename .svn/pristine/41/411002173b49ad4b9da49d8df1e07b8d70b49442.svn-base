package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.NOT_EMPTY;
import static com.baomidou.mybatisplus.annotation.FieldStrategy.NOT_NULL;

@Data
@ApiModel(value = "FileInfo", description = "文件信息")
@TableName(value = "a_file_info")
@NoArgsConstructor
public class FileInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private Integer classId;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private Integer parentId;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private String number;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private String fileName;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private String suffix;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private String prefix;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private String accessUrl;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private Integer userId;

    @TableField(exist = false)
    private Integer roleId;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private String hashCode;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private String type;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private Boolean isEnable;

    @TableField(value = "remark",insertStrategy = NOT_EMPTY,updateStrategy = NOT_NULL)
    private String remark;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private String state;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private Date createTime;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private Date stateTime;

    @TableField(insertStrategy = NOT_EMPTY,updateStrategy = NOT_EMPTY)
    private byte[] fileCode;

    @TableField(exist = false)
    private String fileCodeStr;

    /**
     * 非对称加密后的对称加密秘钥
     */
    @TableField(exist = false)
    private String aesKeyEncrypt;

    /**
     * 文件的base64编码
     */
    @TableField(exist = false)
    private String base64;


    @TableField(exist = false)
    private List<Object> parameters;

    @TableField(exist = false)
    private List<Integer> roleIdList;

    @ApiModelProperty(value = "所属分类标识")
    @TableField(exist = false)
    private Integer categoryId;

    @ApiModelProperty(value = "所属学年学期标识")
    @TableField(exist = false)
    private Integer termId;

    @ApiModelProperty(value = "关联课程标识")
    @TableField(exist = false)
    private Integer courseId;

    @ApiModelProperty(value = "模板文件id")
    @TableField(exist = false)
    private Integer archTemFileId;

}