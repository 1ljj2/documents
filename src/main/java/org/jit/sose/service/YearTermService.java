package org.jit.sose.service;

import org.jit.sose.domain.vo.ListIdNameVo;

import java.util.List;

/**
 * @author wufang
 * @Date 2020-08-05 11:58:58
 */
public interface YearTermService {

    /**
     * 找出在startTerm和endTerm之间的学年学期标识
     *
     * @param startTerm
     * @param endTerm
     * @return
     */
    List<Integer> ListInterval(String startTerm, String endTerm);

    /**
     * 根据输入的termName获得termList
     *
     * @param name
     * @return
     */
    List<ListIdNameVo> listTermByName(String name);
}
