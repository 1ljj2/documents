package org.jit.sose.domain.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author wufang
 * @Date 2020-09-29 11:58:49
 */
@ApiModel(value = "FileInfoParam")
@Data
@AllArgsConstructor
@TableName(value = "a_file_info")
@NoArgsConstructor
public class FileInfoParam {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer classId;

    private Integer parentId;

    private String number;

    private String fileName;

    private String suffix;

    private String prefix;

    private String accessUrl;

    private Integer userId;

    private Integer roleId;

    private String hashCode;

    private String type;

    private Boolean isEnable;


    private String state;

    private Date createTime;

    private Date stateTime;

    private byte[] fileCode;


    private String fileCodeStr;

    /**
     * 非对称加密后的对称加密秘钥
     */
    private String aesKeyEncrypt;

    /**
     * 文件的base64编码
     */
    private String base64;



    private List<Object> parameters;

    private List<Integer> roleIdList;

    @ApiModelProperty(value = "所属分类标识")
    private Integer categoryId;

    @ApiModelProperty(value = "所属学年学期标识")
    private Integer termId;

    @ApiModelProperty(value = "关联课程标识")
    private Integer courseId;

    @ApiModelProperty(value = "备注")
    private String remark;


}
