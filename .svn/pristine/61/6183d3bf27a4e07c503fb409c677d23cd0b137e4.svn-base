package org.jit.sose.service.impl;

import org.jit.sose.domain.vo.ListIdNameVo;
import org.jit.sose.mapper.YearTermMapper;
import org.jit.sose.service.YearTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wufang
 * @Date 2020-08-05 11:59:21
 */
@Service
public class YearTermServiceImpl implements YearTermService {

    @Autowired
    private YearTermMapper yearTermMapper;

    @Override
    public List<Integer> ListInterval(String startYearTerm, String endYearTerm) {
        //定义返回类
        List<Integer> intervalIdList = new ArrayList<>();
        //获取所有的学年学期（标识、名称）
        List<ListIdNameVo> termVoList = yearTermMapper.listTermByName(null);
        //遍历所有的学年学期
        for (int i = 0; i < termVoList.size(); i++) {
            String termNameItem = termVoList.get(i).getName();
            //获取开始学年（如：2020-2021-1中的2020）、学期（如：2020-2021-1中的1）
            String year = termNameItem.substring(0, termNameItem.indexOf("-"));
            String term = String.valueOf(termNameItem.charAt(termNameItem.length() - 1));
            //将开始学年和学期拼接起来，并转化为Float类型数据方便比较
            Float yearTerm = Float.parseFloat(year + "." + term);
            Float start = null;
            Float end = null;
            if (startYearTerm != null) {
                String startYear = startYearTerm.substring(0, startYearTerm.indexOf("-"));
                String startTerm = String.valueOf(startYearTerm.charAt(startYearTerm.length() - 1));
                start = Float.parseFloat(startYear + "." + startTerm);
            }
            if (endYearTerm != null) {
                String endYear = endYearTerm.substring(0, endYearTerm.indexOf("-"));
                String endTerm = String.valueOf(endYearTerm.charAt(endYearTerm.length() - 1));
                end = Float.parseFloat(endYear + "." + endTerm);
            }
            if (start != null && end != null && yearTerm >= start && yearTerm <= end) {
                intervalIdList.add(termVoList.get(i).getId());
            } else if (start == null && end != null && yearTerm <= end) {
                intervalIdList.add(termVoList.get(i).getId());
            } else if (start != null && end == null && yearTerm >= start) {
                intervalIdList.add(termVoList.get(i).getId());
            }
        }
        return intervalIdList;
    }

    @Override
    public List<ListIdNameVo> listTermByName(String name) {
        return yearTermMapper.listTermByName(name);
    }
}
