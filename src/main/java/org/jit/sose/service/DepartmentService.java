package org.jit.sose.service;

import org.jit.sose.domain.entity.Department;
import org.jit.sose.domain.entity.Role;
import org.jit.sose.domain.vo.ListDepartmentVo;
import org.jit.sose.domain.vo.ListIdNameVo;
import org.jit.sose.domain.vo.PageInfoVo;

import java.util.List;

/**
 * @author jinyu
 * @Date 2020-07-05 19:37:46
 */
public interface DepartmentService {

    /**
     * 查询par_id为1(软件工程学院)的部门name和id集合
     *
     * @return
     */
    List<ListIdNameVo> getDepartmentList();

    /**
     * 查询父部门
     *
     * @param id
     * @return
     */
    List<Department> getParIdList(Integer id);

    /**
     * 根据nameList获取部门idList
     *
     * @param departmentNameList
     * @return
     */
    List<Integer> translateNameToId(List<String> departmentNameList);

    /**
     * 根据idList获取部门名称List
     *
     * @param departmentIdList
     * @return
     */
    List<String> showDepartmentSelected(List<Integer> departmentIdList);

    /**
     * 根据部门名称模糊查询部门对象集合
     *
     * @param departmentName
     * @return
     */
    List<ListDepartmentVo> listDepartmentByName(String departmentName);

    List<Department> listPage();

    PageInfoVo<Role> listPageInfo(Department department, Integer pageNum, Integer pageSize);

    Integer insert(Department department);


    Integer deleteList(int[] idList);

    Integer deleteOneDepartment(Integer id);

    Integer edit(Department department);

    PageInfoVo<Department> selectPageInfo(Department department, Integer pageNum, Integer pageSize);

    Integer selectLevelById(int id);
}
