package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.domain.param.ListConfigParam;
import org.jit.sose.domain.vo.ListConfigVo;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.mapper.ConfigurationMapper;
import org.jit.sose.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/** 
* @author jinyu: 
* @Date 2020年10月20日 下午2:11:26 
*  
*/
@Service
public class ConfigurationServiceImpl implements ConfigurationService {
	
	@Autowired
	private ConfigurationMapper configurationMapper;

	@Transactional
	@Override
	public PageInfoVo<ListConfigVo> listConfigByCondition(ListConfigParam param) {
		// 设置分页参数
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        //【标题、发布部门、发布用户、分页参数】
        List<ListConfigVo> voList = configurationMapper.listConfigByCondition(param);
        PageInfo<ListConfigVo> pageInfo = new PageInfo<>(voList);
        return new PageInfoVo<>(pageInfo);
	}

	@Override
	public void removeConfig(Integer id) {
		configurationMapper.removeConfig(id);
	}

	@Override
	public void removeConfigSelect(List<Integer> idList) {
		configurationMapper.removeConfigSelect(idList);
	}

	@Override
	public void disableConfig(Integer id) {
		configurationMapper.disableConfig(id);
	}

	@Override
	public void isableConfig(Integer id) {
		configurationMapper.isableConfig(id);
	}

	@Transactional
	@Override
	public void editConfig(ListConfigVo vo) {
		configurationMapper.editConfig(vo);
	}

	@Transactional
	@Override
	public void addConfig(ListConfigVo vo) {
		configurationMapper.addConfig(vo);
	}

	@Override
	public Boolean checkMessage4Audit() {
		Boolean check = true ;
		Integer enable = configurationMapper.checkMessage4Audit();
		if(enable==1) {
			check = true;
		}else {
			check = false;
		}
		return check;
	}

	@Override
	public Boolean checkFileArchiveNameChange() {
		Boolean check ;
		Integer enable = configurationMapper.checkFileArchiveNameChange();
		if(enable==1) {
			check = true;
		}else {
			check = false;
		}
		return check;
	}

	@Override
	public Boolean checkArchiveNotAuditInSet() {
		Boolean check ;
		Integer enable = configurationMapper.checkArchiveNotAuditInSet();
		if(enable==1) {
			check = true;
		}else {
			check = false;
		}
		return check;
	}

}
 