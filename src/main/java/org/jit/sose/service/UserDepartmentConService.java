package org.jit.sose.service; 
/** 
* @author jinyu: 
* @Date 2020年9月24日 下午5:14:27 
*  
*/
public interface UserDepartmentConService {

	/**
	 * 根据用户id查询部门id
	 * @param id
	 * @return
	 */
	int selectDepartmentIdByUserId(Integer id);

}
 