package org.jit.sose.domain.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author jinyu: 
* @Date 2020年10月18日 上午11:41:42 
*  
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListChargingArchiveParam {
	
	private Integer pageNum;

    private Integer pageSize;
    
    private String userArchiveNumber;
    
    private String userArchiveName;
    
    private Integer leaderId;
    
    private Integer userId;

    private Integer id;
}
 