package org.jit.sose.domain.vo;

import java.sql.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author jinyu: 
* @Date 2020年10月19日 下午9:47:40 
*  
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListArchiveFileVo {
	@ApiModelProperty(value = "主键")
    private Integer id;
	
	@ApiModelProperty(value = "文档名")
    private String fileName;
}
 