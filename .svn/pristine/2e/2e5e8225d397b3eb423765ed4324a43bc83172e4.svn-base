package org.jit.sose.service;

import org.jit.sose.domain.entity.Category;
import org.jit.sose.domain.vo.ListCategoryVo;
import org.jit.sose.domain.vo.PageInfoVo;

import java.util.List;

/**
 * @author wufang
 * @Date 2020-09-22 11:17:32
 */
public interface CategoryService {


    /**
     * 根据类型获取分类
     *
     * @param type
     * @return
     */
    List<ListCategoryVo> listCategoryByType(String type);

    PageInfoVo<Category> selectPageInfo(Category one, Integer pageNum, Integer pageSize);

    Integer insertOneCategory(Category oneCategory);

    Integer updateOneCategory(Category oneCategory);

    Integer deleteOneCategory(Integer id);

    Integer deleteSelectedCategory(int[] idList);
}
