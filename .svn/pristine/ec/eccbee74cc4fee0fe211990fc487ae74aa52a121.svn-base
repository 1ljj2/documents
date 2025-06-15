package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 腾讯云短信发送返回对象
 * 
 * @author wangyue
 * @date 2020-07-15 10:47:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "QcloudSmsVo", description = "腾讯云短信发送返回对象")
public class QcloudSmsVo {

	@ApiModelProperty(value = "是否发送成功", example = "true")
	private boolean success;

	@ApiModelProperty(value = "错误码", example = "0")
	private Integer code;

	@ApiModelProperty(value = "返回信息", example = "发送成功")
	private String message;

}
