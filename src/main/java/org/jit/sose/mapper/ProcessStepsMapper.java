package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.ProcessSteps;
import org.jit.sose.domain.vo.ListStepsVo;
import org.jit.sose.domain.vo.NextStepVo;
import org.jit.sose.domain.vo.UserStepArcVo;
import org.jit.sose.domain.vo.UserStepVo;

import java.util.List;

public interface ProcessStepsMapper {
	/**
	 * 根据父步骤id获取下一步id
	 * @param pStepId
	 * @return
	 */
	Integer listStepIdByPStepId(Integer pStepId);

    /**
     * 根据父步骤标识获取下一个步骤的角色和角色名称
     *
     * @param preStepId
     * @return
     */
    NextStepVo selectNextStepByNowStep(@Param("preStepId") Integer preStepId);

    /**
     * 根据父流程步骤的标识查询空的流程步骤
     *
     * @param preStepId
     * @return
     */
    UserStepArcVo selectEmpArcAuditByPStepId(@Param("preStepId") Integer preStepId);

    /**
     * 根据父流程步骤的标识查询空的流程步骤
     *
     * @param preStepId
     * @return
     */
    UserStepVo selectEmpFileAuditByPStepId(@Param("preStepId") Integer preStepId);

    /**
     * 根据file_example_process实例标识获取第一个运转的流程步骤    √
     *
     * @param exampleId
     * @return
     */
    UserStepArcVo selectFirArcUserStep(@Param("exampleId") Integer exampleId);

    /**
     * 根据file_example_process实例标识获取第一个运转的流程步骤    √
     *
     * @param exampleId
     * @return
     */
    UserStepVo selectFirFileUserStep(@Param("exampleId") Integer exampleId);

    /**
     * 获取当前步骤的下一个步骤服务的对象id  √
     *
     * @param preStepId
     */
    Integer selectRoleIdByPreStepId(@Param("preStepId") Integer preStepId);

    /**
     * 编辑单个流程步骤  √
     *
     * @param processSteps
     */
    void editProcessStep(ProcessSteps processSteps);


    /**
     * 根据id更新其父步骤标识
     *
     * @param id
     * @param pStepId
     */
    void updateNextStep(@Param("id") Integer id, @Param("pStepId") Integer pStepId);

    /**
     * 将指定的步骤的父步骤置为0
     *
     * @param id
     */
    void updatePStepIdTo0(@Param("id") Integer id);

    /**
     * 删除指定id的流程
     *
     * @param id
     */
    void removeStep(@Param("id") Integer id);

    /**
     * 查询出该流程的下一个流程
     *
     * @param id
     * @return
     */
    Integer selectNextStepIdById(@Param("id") Integer id);

    /**
     * 根据id查询pStepId，即查询该流程的上一个流程id
     *
     * @param id
     * @return
     */
    Integer selectPStepIdById(@Param("id") Integer id);

    /**
     * 新增单个流程步骤    √
     *
     * @param param
     */
    void addStep(ProcessSteps param);

    /**
     * 用id作为p_step_id查询出下一个流程步骤   √
     *
     * @param pStepId
     * @return
     */
    ListStepsVo selectStepByPStepId(@Param("pStepId") Integer pStepId);

    /**
     * 根据流程标识获取第一个流程步骤    √
     *
     * @param processId
     * @return
     */
    ListStepsVo selectFirStepByProcessId(@Param("processId") Integer processId);

    /**
     * 根据idList流程步骤对象
     *
     * @param idList
     * @return
     */
    List<ListStepsVo> viewStepByIdList(List<Integer> idList);

    int deleteByPrimaryKey(Integer id);

    int insert(ProcessSteps record);

    ProcessSteps selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ProcessSteps record);
}