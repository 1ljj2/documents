package org.jit.sose.service.impl;

import org.jit.sose.domain.entity.ArchiveExampleFile;
import org.jit.sose.domain.vo.ListArchiveFileForAuditVo;
import org.jit.sose.mapper.ArchiveExampleFileMapper;
import org.jit.sose.service.ArchiveExampleFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LJH
 * @Date: 2020/10/16 10:08
 */
@Service
public class ArchiveExampleServiceImpl implements ArchiveExampleFileService {

    @Autowired
    ArchiveExampleFileMapper archiveExampleFileMapper;

    @Override
    public int insertList(List<ArchiveExampleFile> list) {
        return archiveExampleFileMapper.insertList(list);
    }

    @Override
    public List<ListArchiveFileForAuditVo> listArchiveFile(Integer exampleId) {
        return archiveExampleFileMapper.listArchiveFile(exampleId);
    }
}
