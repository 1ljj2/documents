package org.jit.sose.service;

import org.jit.sose.domain.entity.Eecstate;
import org.jit.sose.domain.vo.PageInfoVo;

import java.util.List;

public interface EecstateService {


    /**
     * 根据table和colm查询域表集合
     *
     * @param table
     * @param colm
     * @return Eecstate集合
     */
    List<Eecstate> listByTableColm(String table, String colm);

    /**
     * 插入新数据
     *
     * @param eecstate
     */
    String insert(Eecstate eecstate);

    /**
     * 通过id更新数据
     *
     * @param eecstate
     */
    void update(Eecstate eecstate);

    /**
     * 通过id假删除数据
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 批量逻辑删除域表信息
     *
     * @param idList 需要删除的id的集合
     * @return success/fail
     */
    Integer deleteSelection(List<Integer> idList);

    /**
     * 过滤查询(查询条件为课程信息类里面相关属性)
     *
     * @param eecstate 课程信息类
     * @return Eecstate集合
     */
    PageInfoVo<Eecstate> selectPageInfo(Eecstate eecstate, Integer pageNum, Integer pageSize);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Eecstate selectById(Integer id);

}
