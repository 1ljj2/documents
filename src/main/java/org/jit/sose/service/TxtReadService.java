package org.jit.sose.service;

import java.util.Map;

/**
 * @author wufang
 * @Date 2020-09-17 14:28:53
 */
public interface TxtReadService {

    /**
     * 获取项目名称
     *
     * @return
     */
    Map<String,String> getSystemInfo();
}
