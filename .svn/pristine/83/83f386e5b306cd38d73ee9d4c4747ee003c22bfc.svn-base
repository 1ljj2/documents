package org.jit.sose.service;

import java.util.List;

import org.jit.sose.domain.param.ListConfigParam;
import org.jit.sose.domain.vo.ListConfigVo;
import org.jit.sose.domain.vo.PageInfoVo;

/** 
* @author jinyu: 
* @Date 2020年10月20日 下午2:10:20 
*  
*/
public interface ConfigurationService {

	/**
	 * 查询所有的配置表项
	 * @param param
	 * @return
	 */
	PageInfoVo<ListConfigVo> listConfigByCondition(ListConfigParam param);

	/**
	 * 删除单个配置表信息
	 * @param id
	 */
	void removeConfig(Integer id);

	/**
	 * 删除选择的多个配置表信息
	 * @param idList
	 */
	void removeConfigSelect(List<Integer> idList);

	/**
	 * 禁用配置表信息
	 * @param id
	 */
	void disableConfig(Integer id);

	/**
	 * 启用配置表信息
	 * @param id
	 */
	void isableConfig(Integer id);

	/**
	 * 编辑配置项
	 * @param vo
	 */
	void editConfig(ListConfigVo vo);

	/**
	 * 新增配置项
	 * @param vo
	 */
	void addConfig(ListConfigVo vo);

	/**
	 * 发布审核短信开关
	 * @return
	 */
	Boolean checkMessage4Audit();

	/**
	 * 模板/文档名称能否编辑开关
	 * @return
	 */
	Boolean checkFileArchiveNameChange();

	/**
	 * 档案中能否存在未审核文档开关
	 * @return
	 */
	Boolean checkArchiveNotAuditInSet();

}
 