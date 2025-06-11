package org.jit.sose.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.jit.sose.domain.entity.ArchiveAudit;
import org.jit.sose.domain.entity.ArchiveExampleProcess;
import org.jit.sose.domain.param.ForReviewArcToNextParam;
import org.jit.sose.domain.param.MyArchiveForReviewParam;
import org.jit.sose.domain.vo.ListAuditArchiveVo;
import org.jit.sose.domain.vo.NextStepVo;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.domain.vo.UserStepArcVo;
import org.jit.sose.mapper.ArchiveAuditMapper;
import org.jit.sose.mapper.ArchiveExampleProcessMapper;
import org.jit.sose.mapper.ProcessStepsMapper;
import org.jit.sose.service.ArchiveAuditService;
import org.jit.sose.service.ProcessStepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: LJH
 * @Date: 2020/10/17 16:20
 */
@Service
public class ArchiveAuditServiceImpl implements ArchiveAuditService {

    @Autowired
    private ArchiveAuditMapper archiveAuditMapper;

    @Autowired
    private ArchiveExampleProcessMapper archiveExampleProcessMapper;

    @Autowired
    private ProcessStepsService processStepsService;

    @Autowired
    private ProcessStepsMapper processStepsMapper;


    @Transactional
    @Override
    public void forReviewArcToNext(ForReviewArcToNextParam param) {
        System.out.println(param);
        //更新当前文档审核运转实例的数据  即 审核过程

        archiveAuditMapper.updateAuditById(param.getArcAuditId(), param.getAuditState(), param.getOpinion());
        if ( "C".equals(param.getAuditState()) &&  param.getStepId()==null){
            ArchiveExampleProcess example = new ArchiveExampleProcess();
            example.setAuditState("C");
            example.setId(param.getExampleId());
            archiveExampleProcessMapper.updateByPrimaryKeySelective(example);
        }
        //检验审核状态
        if ("C".equals(param.getAuditState()) && param.getStepId()!=null){
            //通过-生成下一条记录
            archiveAuditMapper.addAuditRecord(param.getArcAuditId(), param.getExampleId(), param.getUserId(), param.getStepId(), "B");
        }else if("D".equals(param.getAuditState())){
            //不通过-更新实例表的信息
            ArchiveExampleProcess example = new ArchiveExampleProcess();
            example.setAuditState("D");
            archiveExampleProcessMapper.updateByPrimaryKeySelective(example);
        }

    }

    @Transactional
    @Override
    public Map<String, Object> getAuditArchiveModalInfo(Integer exampleId, Integer processId, Integer nowStepId) {
        //流程步骤条
        List<UserStepArcVo> userStepVos = processStepsService.checkArchiveAuditStepInfo(exampleId, processId);
        System.out.println(userStepVos);
        //下一个步骤的信息
        NextStepVo nextStepVo = processStepsMapper.selectNextStepByNowStep(nowStepId);
        System.out.println(nextStepVo);
        Map<String, Object> map = new HashMap<>();
        map.put("userStepVos", userStepVos);
        map.put("nextStepVo", nextStepVo);
        return map;
    }

    @Override
    public PageInfoVo<ListAuditArchiveVo> listAuditArchiveMyRemind(Integer userId, Integer pageNum, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        List<ListAuditArchiveVo> resultVo = archiveAuditMapper.listAuditArchiveMyRemind(userId);
        PageInfo<ListAuditArchiveVo> pageInfo = new PageInfo<>(resultVo);
        return new PageInfoVo<>(pageInfo);
    }

    @Override
    public int insertSelective(ArchiveAudit record) {
        return archiveAuditMapper.insertSelective(record);
    }

//    @Transactional
//    @Override
//    public int myArchiveForReview(MyArchiveForReviewParam param) {
//        //审核实例
//        ArchiveExampleProcess archiveExampleProcessParam = new ArchiveExampleProcess();
//        archiveExampleProcessParam.setUserId(param.getNowUserId());//当前送审的档案所属的 用户的id(user_id)
//        archiveExampleProcessParam.setLeaderId(param.getUserId());//送给谁进行审核 即审核人的id(leader_id)
//        archiveExampleProcessParam.setArchiveTemplateId(param.getArchiveTemplateId());//档案id()
//        archiveExampleProcessParam.setProcessId(param.getProcessId());//流程id
//        archiveExampleProcessParam.setAuditState("B");//为审核中状态
//        archiveExampleProcessParam.setUserArchiveName(param.getArchiveName());//名称
//        archiveExampleProcessParam.setUserArchiveNumber(param.getArchiveNumber());//标号
//        //加入到  t_archive_example_process  表中
//        int flag = archiveExampleProcessMapper.addOneExample(archiveExampleProcessParam);
//
//        //获取到 t_archive_example_process新增的id
//        Integer archiveExampleId = archiveExampleProcessParam.getId();
//        //要获取param.getStepId()的下一步stepId
//        int pStepId = param.getStepId();
//        int stepId = processStepsMapper.listStepIdByPStepId(pStepId);
//        //审核步骤
//        ArchiveAudit archiveAudit = new ArchiveAudit();
//        archiveAudit.setParId(0);//第一个审核流程的父id为 0
//        archiveAudit.setExampleId(archiveExampleId);//设置 审核实例的id
//        archiveAudit.setAuditUserId(param.getUserId());//设置 第一个审核人的 id
//        archiveAudit.setStepId(stepId); //设置 下一个步骤id
//        archiveAudit.setAuditState("B");// 设置 状态B 审核中
//        int flag1= archiveAuditMapper.insertSelective(archiveAudit);
//
//        return flag+flag1;
//    }




    @Transactional
    @Override
    public int myArchiveForReview(MyArchiveForReviewParam param) {
        //先根据 用户的 id 和 档案模板的id 查到实例的信息
        ArchiveExampleProcess archiveExampleProcessParam = new ArchiveExampleProcess();
        archiveExampleProcessParam.setUserId(param.getNowUserId());//当前送审的档案所属的 用户的id
        archiveExampleProcessParam.setArchiveTemplateId(param.getArchiveTemplateId());//档案模板的id
        //当前的实例
        ArchiveExampleProcess archiveExampleProcessResult =
                archiveExampleProcessMapper.selectByUserIdAndArchiveTemplateId(archiveExampleProcessParam);

        //更新实例地信息
        archiveExampleProcessResult.setUserArchiveNumber(param.getArchiveNumber());//档案编号
        archiveExampleProcessResult.setLeaderId(param.getUserId());//档案审核人
        archiveExampleProcessResult.setProcessId(param.getProcessId());//档案的流程id
        archiveExampleProcessResult.setAuditState("B");//为审核中状态

        archiveExampleProcessMapper.updateById(archiveExampleProcessResult);
        //获取到 t_archive_example_process的 档案实例id
        Integer archiveExampleId = archiveExampleProcessResult.getId();

        //要获取param.getStepId()的下一步stepId
        int pStepId = param.getStepId();
        int stepId = processStepsMapper.listStepIdByPStepId(pStepId);
        //审核步骤
        ArchiveAudit archiveAudit = new ArchiveAudit();
        archiveAudit.setParId(0);//第一个审核流程的父id为 0
        archiveAudit.setExampleId(archiveExampleId);//设置 审核实例的id
        archiveAudit.setAuditUserId(param.getUserId());//设置 第一个审核人的 id
        archiveAudit.setStepId(stepId); //设置 下一个步骤id
        archiveAudit.setAuditState("B");// 设置 状态B 审核中
        int flag1= archiveAuditMapper.insertSelective(archiveAudit);


        return flag1;
    }

//    @Transactional
//    @Override
//    public int myArchiveForReview(MyArchiveForReviewParam param) {
//        ArchiveExampleProcess archiveExampleProcessParam = new ArchiveExampleProcess();
//        archiveExampleProcessParam.setUserId(param.getNowUserId());//当前送审的档案所属的 用户的id
//        archiveExampleProcessParam.setArchiveTemplateId(param.getArchiveTemplateId());
//        ArchiveExampleProcess archiveExampleProcessResult =
//                archiveExampleProcessMapper.selectByUserIdAndArchiveTemplateId(archiveExampleProcessParam);
//        archiveExampleProcessResult.setProcessId(param.getProcessId());
//        archiveExampleProcessResult.setAuditState("B");
//        archiveExampleProcessMapper.updateById(archiveExampleProcessResult);
//        ArchiveAudit archiveAudit = new ArchiveAudit();
//        archiveAudit.setAuditUserId(param.getUserId());
//        archiveAudit.setExampleId(archiveExampleProcessResult.getId());
//        //要获取param.getStepId()的下一步stepId
//        int pStepId = param.getStepId();
//        int stepId = processStepsMapper.listStepIdByPStepId(pStepId);
//        archiveAudit.setStepId(stepId);
//        return archiveAuditMapper.insertSelective(archiveAudit);
//    }
}
