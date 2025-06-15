package org.jit.sose.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.jit.sose.domain.entity.Category;
import org.jit.sose.domain.vo.ListCategoryVo;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.mapper.CategoryMapper;
import org.jit.sose.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wufang
 * @Date 2020-09-22 11:17:48
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<ListCategoryVo> listCategoryByType(String type) {
        return categoryMapper.listCategoryByType(type);
    }

    @Override
    public PageInfoVo<Category> selectPageInfo(Category one, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // 查询集合
        List<Category> CategoryList = categoryMapper.selectCategory(one);
        PageInfo<Category> pageInfo = new PageInfo<Category>(CategoryList);
        // 封装集合
        return new PageInfoVo<Category>(pageInfo);
    }

    @Override
    public Integer insertOneCategory(Category oneCategory) {
        return categoryMapper.insertOneCategory(oneCategory);
    }

    @Override
    public Integer updateOneCategory(Category oneCategory) {
        return categoryMapper.updateOneCategory(oneCategory);
    }

    @Override
    public Integer deleteOneCategory(Integer id) {
        return categoryMapper.deleteOneCategory(id);
    }

    @Override
    public Integer deleteSelectedCategory(int[] idList) {
        return categoryMapper.deleteSelectedCategory(idList);
    }
}
