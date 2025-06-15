package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.Eecstate;

import java.util.List;

public interface EecstateMapper {

	/**
	 * 根据table和colm查询域表集合
	 * 
	 * @param table
	 * @param colm
	 * @return Eecstate集合
	 */
	List<Eecstate> listByTableColm(@Param("table") String table, @Param("colm") String colm);

	/**
	 * 逻辑删除域表信息
	 * 
	 * @param id 域表信息标识
	 * @return 受影响行数
	 */
	void delete(Integer id);

	/**
	 * 批量逻辑删除域表信息
	 * 
	 * @param idList 需要删除的id的集合
	 * @return 受影响行数
	 */
	Integer deleteSelection(List<Integer> idList);

	/**
	 * 插入新数据
	 * 
	 * @param eecstate
	 */
	void insert(Eecstate eecstate);

	/**
	 * 通过id查询所有数据
	 * 
	 * @param id
	 * @return Eecstate
	 */
	Eecstate selectById(Integer id);

	/**
	 * 通过id更新数据
	 * 
	 * @param eecstate
	 */
	void update(Eecstate eecstate);

	/**
	 * 过滤查询域表信息
	 * 
	 * @param eecstate 域表信息类
	 * @return 域表信息集合
	 */
	List<Eecstate> listByEecstate(Eecstate eecstate);

	/**
	 * 查重
	 * 
	 * @param eecstate
	 * @return 数据相同的条数
	 */
	Integer countByEecstate(Eecstate eecstate);

}