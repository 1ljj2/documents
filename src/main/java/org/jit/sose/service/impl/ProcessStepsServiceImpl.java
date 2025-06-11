package org.jit.sose.service.impl;

import org.jit.sose.domain.vo.ListStepsVo;
import org.jit.sose.domain.vo.UserStepArcVo;
import org.jit.sose.domain.vo.UserStepVo;
import org.jit.sose.mapper.*;
import org.jit.sose.service.ProcessStepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wufang
 * @Date 2020-07-02 14:30:42
 */
@Service
public class ProcessStepsServiceImpl implements ProcessStepsService {

    @Autowired
    private ProcessStepsMapper processStepsMapper;

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private FileExampleProcessMapper fileExampleProcessMapper;

    @Autowired
    private ArchiveExampleProcessMapper archiveExampleProcessMapper;

    @Autowired
    private ProcessStepsService processStepsService;

    @Autowired
    private FileAuditMapper fileAuditMapper;

    @Autowired
    private ArchiveAuditMapper archiveAuditMapper;

    @Transactional
    @Override
    public List<UserStepArcVo> checkArchiveAuditStepInfo(Integer exampleId, Integer processId) {
        //实例化返回对象集合
        List<UserStepArcVo> voList = new ArrayList<>();
        //先获取第一个流程步骤信息
        UserStepArcVo firVo = processStepsMapper.selectFirArcUserStep(exampleId);
        //根据实例标识查询该文件所属
        firVo.setUserName(archiveExampleProcessMapper.selectUserStep(exampleId));
        voList.add(firVo);
        //获取审核运转的第一个审核实例
        UserStepArcVo secVo = archiveAuditMapper.selectUserStep(0, exampleId);
        voList.add(secVo);
        //获取id作为下一个的parId
        Integer arcAuditId = secVo.getArchiveAuditId();
        //控制循环的标志
        Boolean whileFlag = true;
        while (whileFlag) {
            //检验是否有该fileAuditId作为parId的文档审核运转实例
            if (archiveAuditMapper.selectAuditByParId(arcAuditId) != null && !archiveAuditMapper.selectAuditByParId(arcAuditId).equals("")) {
                UserStepArcVo userStepArcVo = archiveAuditMapper.selectAuditByParId(arcAuditId);
                voList.add(userStepArcVo);
                arcAuditId = userStepArcVo.getArchiveAuditId();
            } else {
                whileFlag = false;
            }
        }
        //查找有数据的流程步骤最后一个stepId是否为流程的最后一个
        Integer stepId = voList.get(voList.size() - 1).getStepId();
        Boolean whileFlag2 = true;
        while (whileFlag2) {
            if (processStepsMapper.selectEmpFileAuditByPStepId(stepId) != null && !processStepsMapper.selectEmpFileAuditByPStepId(stepId).equals("")) {
                UserStepArcVo empUserStepVo = processStepsMapper.selectEmpArcAuditByPStepId(stepId);
                voList.add(empUserStepVo);
                stepId = empUserStepVo.getStepId();
            } else {
                whileFlag2 = false;
            }
        }
        return voList;
    }

    @Transactional
    @Override
    public List<UserStepVo> checkFileAuditStepInfo(Integer exampleId, Integer processId) {
        //实例化返回对象集合
        List<UserStepVo> voList = new ArrayList<>();
        //先获取第一个流程步骤信息
        UserStepVo firVo = processStepsMapper.selectFirFileUserStep(exampleId);
        //根据实例标识查询该文件所属
        firVo.setUserName(fileExampleProcessMapper.selectUserStep(exampleId));
        voList.add(firVo);
        //获取审核运转的第一个审核实例
        UserStepVo secVo = fileAuditMapper.selectUserStep(0, exampleId);
        voList.add(secVo);
        //获取id作为下一个的parId
        Integer fileAuditId = secVo.getFileAuditId();
        //控制循环的标志
        Boolean whileFlag = true;
        while (whileFlag) {
            //检验是否有该fileAuditId作为parId的文档审核运转实例
            if (fileAuditMapper.selectAuditByParId(fileAuditId) != null && !fileAuditMapper.selectAuditByParId(fileAuditId).equals("")) {
                UserStepVo userStepVo = fileAuditMapper.selectAuditByParId(fileAuditId);
                voList.add(userStepVo);
                fileAuditId = userStepVo.getFileAuditId();
            } else {
                whileFlag = false;
            }
        }
        //查找有数据的流程步骤最后一个stepId是否为流程的最后一个
        Integer stepId = voList.get(voList.size() - 1).getStepId();
        Boolean whileFlag2 = true;
        while (whileFlag2) {
            if (processStepsMapper.selectEmpFileAuditByPStepId(stepId) != null && !processStepsMapper.selectEmpFileAuditByPStepId(stepId).equals("")) {
                UserStepVo empUserStepVo = processStepsMapper.selectEmpFileAuditByPStepId(stepId);
                voList.add(empUserStepVo);
                stepId = empUserStepVo.getStepId();
            } else {
                whileFlag2 = false;
            }
        }
        return voList;
    }

    @Override
    public List<ListStepsVo> viewStepByIdList(List<Integer> idList) {
        return processStepsMapper.viewStepByIdList(idList);
    }

    @Transactional
    @Override
    public void removeStepByProcessId(Integer processId, Integer stepId) {
        //根据process表的id查询process对应的第一个步骤标识
        Integer firstStepId = processMapper.selectStepIdById(processId);
        System.out.println("firstStepId:" + firstStepId);
        //查询该流程的上一个流程id  (假设删除的id为2 1)
        Integer previousStepId = processStepsMapper.selectPStepIdById(stepId);
        //查询出该流程的下一个流程id
        Integer nextStepId = processStepsMapper.selectNextStepIdById(stepId);
        //如果要删除的这个步骤下面还有步骤
        if (nextStepId != null) {
            //删除
            processStepsMapper.removeStep(stepId);
            //如果firstStepId和要删除的stepId相同（即删除的是第一个步骤，但是下面还有步骤），还需要更改process表
            if (firstStepId == stepId) {
                //更新process表中步骤标识
                processMapper.updateStepId(processId, nextStepId);
            }
            //将processId对应的新的步骤的父步骤置为0
            processStepsMapper.updatePStepIdTo0(nextStepId);
            //更新要删除的步骤的下一个步骤 的父步骤 为要删除步骤的前一个步骤(把id为3的步骤的pStepId置为1)
            processStepsMapper.updateNextStep(nextStepId, previousStepId);
        }
    }

    @Transactional
    @Override
    public List<ListStepsVo> viewStepsByProcessId(Integer processId) {
        //根据流程标识获取第一个流程步骤
        ListStepsVo firstVo = processStepsMapper.selectFirStepByProcessId(processId);
        firstVo.setIsSignName(firstVo.getIsSign().equals(0) ? "无需签章" : "需要签章");
        //创建返回vo并赋值第一个
        List<ListStepsVo> voList = new ArrayList<>();
        voList.add(firstVo);
        //定义父步骤id，并将第一个流程id作为第二个流程的p_step_id
        Integer pStepId = 0;
        pStepId = firstVo.getId();
        //循环标识
        Boolean flag = true;
        while (flag) {
            ListStepsVo nextStepsVo = processStepsMapper.selectStepByPStepId(pStepId);
            //如果下一个对象不空，说明存在
            if (nextStepsVo != null && !nextStepsVo.equals("")) {
                nextStepsVo.setIsSignName(nextStepsVo.getIsSign().equals(0) ? "无需签章" : "需要签章");
                //将步骤存入返回值，并获取下一个流程的p_step_id
                voList.add(nextStepsVo);
                pStepId = nextStepsVo.getId();
            } else {
                //否则停止循环
                flag = false;
            }
        }
        return voList;
    }
}
