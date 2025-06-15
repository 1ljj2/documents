package org.jit.sose.service.impl;

import java.util.List;

import org.jit.sose.domain.param.ListChargingArchiveParam;
import org.jit.sose.domain.vo.ListArchiveFileVo;
import org.jit.sose.domain.vo.ListChargingArchiveVo;
import org.jit.sose.domain.vo.ListMyFileVo;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.mapper.ArchiveExampleFileMapper;
import org.jit.sose.mapper.ArchiveExampleProcessMapper;
import org.jit.sose.service.ArchiveExampleWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/** 
* @author jinyu: 
* @Date 2020年10月18日 上午11:58:37 
*  
*/
@Service
public class ArchiveExampleWatchServiceImpl implements ArchiveExampleWatchService {
	
	@Autowired
	private ArchiveExampleProcessMapper archiveExampleProcessMapper;
	
	@Autowired
	private ArchiveExampleFileMapper archiveExampleFileMapper;

	@Override
	public PageInfoVo<ListChargingArchiveVo> listChargingArchiveByCondition(ListChargingArchiveParam param) {
		// 设置分页参数
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        // 查询集合(包括用户档案编号、用户档案名称、负责人idList、创建者idList、分页参数)
        List<ListChargingArchiveVo> voList = archiveExampleProcessMapper.listChargingArchiveByCondition(param);
        PageInfo<ListChargingArchiveVo> pageInfo = new PageInfo<>(voList);
        return new PageInfoVo<>(pageInfo);
	}

	@Override
	public void disableArchiveExample(Integer id) {
		archiveExampleProcessMapper.disableArchiveExample(id);
	}

	@Override
	public PageInfoVo<ListArchiveFileVo> listArchiveFileByCondition(ListChargingArchiveParam param) {
		// 设置分页参数
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        // 查询集合(包括用户档案id、、分页参数)
        List<ListArchiveFileVo> voList = archiveExampleFileMapper.listArchiveFileByCondition(param);
        PageInfo<ListArchiveFileVo> pageInfo = new PageInfo<>(voList);
        return new PageInfoVo<>(pageInfo);
	}

}
 