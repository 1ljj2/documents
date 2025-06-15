package org.jit.sose.service;

import org.jit.sose.domain.param.ListChargingArchiveParam;
import org.jit.sose.domain.vo.ListArchiveFileVo;
import org.jit.sose.domain.vo.ListChargingArchiveVo;
import org.jit.sose.domain.vo.PageInfoVo;

/** 
* @author jinyu: 
* @Date 2020年10月18日 上午11:51:21 
*  
*/
public interface ArchiveExampleWatchService {

	/**
	 * 根据【绑定id、分页参数】筛选文件列表对象
	 * @param param
	 * @return
	 */
	PageInfoVo<ListArchiveFileVo> listArchiveFileByCondition(ListChargingArchiveParam param);
	
	/**
	 * 禁用系统档案
	 * @param id
	 */
	void disableArchiveExample(Integer id);
	
	/**
	 * 根据【用户档案编号、用户档案名称、负责人、创建者、分页参数】筛选我的档案
	 * @param param
	 * @return
	 */
	PageInfoVo<ListChargingArchiveVo> listChargingArchiveByCondition(ListChargingArchiveParam param);

}
 