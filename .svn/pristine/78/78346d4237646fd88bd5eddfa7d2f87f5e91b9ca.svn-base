package org.jit.sose.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回值
 * 
 * @author wangyue
 * @date 2019-06-03 01:11:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CommonResp", description = "统一返回值")
public class CommonResp {

	@ApiModelProperty(value = "状态码", required = true, notes = "哈哈")
	private Integer code;

	@ApiModelProperty(value = "返回信息", required = true)
	private String msg;

	@ApiModelProperty(value = "返回参数对象")
	private Object obj;

}
