package org.jit.sose.mapper;

import org.jit.sose.TestAppDocument;
import org.jit.sose.domain.entity.FileCategoryCon;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: LJH
 * @Date: 2020/10/2 21:42
 */

public class FileCategoryConMapperTest extends TestAppDocument {
    @Autowired
    FileCategoryConMapper fileCategoryConMapper;

    @Test
    public void insertTest(){
        Integer categoryId = 1;
        Integer fileId = 102;
        FileCategoryCon fileCategoryCon = new FileCategoryCon();
        fileCategoryCon.setCategoryId(categoryId);
        fileCategoryCon.setFileId(fileId);
        fileCategoryConMapper.insert(fileCategoryCon);
        System.out.println(fileCategoryCon.getId());
    }
}
