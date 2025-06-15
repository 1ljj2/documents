package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.domain.entity.Configuration;
import org.jit.sose.domain.param.ListConfigParam;
import org.jit.sose.domain.vo.ListConfigVo;

public interface ConfigurationMapper {

	/**
	 * 档案中能否存在未审核文档开关
	 * @return
	 */
	Integer checkArchiveNotAuditInSet();

	/**
	 * 模板/文档名称能否编辑开关
	 * @return
	 */
	Integer checkFileArchiveNameChange();

	/**
	 * 发布审核短信开关
	 * @return
	 */
	Integer checkMessage4Audit();
	
	/**
	 * 新增配置表项
	 * @param vo
	 */
	void addConfig(ListConfigVo vo);

	/**
	 * 编辑配置表项
	 * @param vo
	 */
	void editConfig(ListConfigVo vo);

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
	 * 删除多个配置表信息
	 * @param idList
	 */
	void removeConfigSelect(List<Integer> idList);

	/**
	 * 删除单个配置表信息
	 * @param id
	 */
	void removeConfig(Integer id);

	/**
	 * 查询所有的配置表项
	 * @param param
	 * @return
	 */
	List<ListConfigVo> listConfigByCondition(ListConfigParam param);
	/**
	 * 根据名字查询是否enable
	 * @param name
	 * @return
	 */
	int ListEnableByName(Configuration record);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Configuration record);

    int insertSelective(Configuration record);

    Configuration selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Configuration record);

    int updateByPrimaryKey(Configuration record);
}