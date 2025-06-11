package org.jit.sose.service.impl;

import org.jit.sose.domain.vo.NextStepVo;
import org.jit.sose.domain.vo.UserStepVo;
import org.jit.sose.mapper.FileExampleProcessMapper;
import org.jit.sose.mapper.ProcessStepsMapper;
import org.jit.sose.service.FileExampleProcessService;
import org.jit.sose.service.ProcessStepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wufang
 * @Date 2020-10-18 15:26:43
 */
@Service
public class FileExampleProcessServiceImpl implements FileExampleProcessService {

    @Autowired
    FileExampleProcessMapper fileExampleProcessMapper;

    @Autowired
    private ProcessStepsService processStepsService;

    @Autowired
    private ProcessStepsMapper processStepsMapper;

    @Transactional
    @Override
    public Map<String,Object> getAuditFileModalInfo(Integer exampleId, Integer processId, Integer nowStepId) {
        //流程步骤条
        List<UserStepVo> userStepVos = processStepsService.checkFileAuditStepInfo(exampleId,processId);
        //下一个步骤的信息
        NextStepVo nextStepVo = processStepsMapper.selectNextStepByNowStep(nowStepId);
        Map<String,Object> map = new HashMap<>();
        map.put("userStepVos",userStepVos);
        map.put("nextStepVo",nextStepVo);
        return map;
    }
}
