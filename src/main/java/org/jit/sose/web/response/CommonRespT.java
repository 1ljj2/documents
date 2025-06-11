package org.jit.sose.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Swagger统一返回值(泛型),用于在swagger或knife4j上显示<br>
 * 所有
 * 
 * @author wangyue
 * @date 2020-04-23 01:07:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CommonRespT", description = "swagger统一返回值")
public class CommonRespT<T> {

	@ApiModelProperty(value = "状态码", required = true)
	private Integer code;

	@ApiModelProperty(value = "返回信息", required = true)
	private String msg;

	@ApiModelProperty(value = "返回参数对象")
	private T obj;

}
