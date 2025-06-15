package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.FileAudit;
import org.jit.sose.domain.vo.ListAuditFileVo;
import org.jit.sose.domain.vo.UserStepVo;

import java.util.List;

public interface FileAuditMapper {

    /**
     * 更新当前文档审核运转实例的数据
     *
     * @param id
     * @param auditState
     * @param opinion
     */
    void updateAuditById(@Param("id") Integer id, @Param("auditState") String auditState, @Param("opinion") String opinion);

    /**
     * 以parId作为条件查找文档审核运转实例
     *
     * @param parId
     * @return
     */
    UserStepVo selectAuditByParId(@Param("parId") Integer parId);

    /**
     * 根据父标识和实例标识查询用户步骤文档审核实例
     *
     * @param parId
     * @param exampleId
     * @return
     */
    UserStepVo selectUserStep(@Param("parId") Integer parId, @Param("exampleId") Integer exampleId);

    /**
     * 根据当前用户标识获取自身需要审核的文档
     *
     * @param userId
     * @return
     */
    List<ListAuditFileVo> listAuditFileMyRemind(@Param("userId") Integer userId);

    /**
     * t_file_audit新增未审核的记录
     */
    void addAuditRecord(@Param("parId")Integer parId,@Param("exampleId") Integer exampleId, @Param("auditUserId") Integer auditUserId, @Param("stepId") Integer stepId, @Param("auditState") String auditState);

    int deleteByPrimaryKey(Integer id);

    int insert(FileAudit record);

    int insertSelective(FileAudit record);

    FileAudit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileAudit record);

    int updateByPrimaryKey(FileAudit record);
}