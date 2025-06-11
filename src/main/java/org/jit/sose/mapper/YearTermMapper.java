package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.YearTerm;
import org.jit.sose.domain.vo.ListIdNameVo;

import java.util.List;

public interface YearTermMapper {

    String selectNameById(@Param("id") Integer id);

    /**
     * 根据输入的termName获得termList
     */
    List<ListIdNameVo> listTermByName(@Param("name") String name);

    int deleteByPrimaryKey(Integer id);

    int insert(YearTerm record);

    int insertSelective(YearTerm record);

    YearTerm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YearTerm record);

    int updateByPrimaryKey(YearTerm record);
}