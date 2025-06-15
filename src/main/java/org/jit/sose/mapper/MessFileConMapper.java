package org.jit.sose.mapper;

import org.jit.sose.domain.entity.MessFileCon;
import org.jit.sose.domain.param.AddEditMessParam;

public interface MessFileConMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessFileCon record);

    int insertSelective(MessFileCon record);

    MessFileCon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessFileCon record);

    int updateByPrimaryKey(MessFileCon record);

    /**
     * 增加绑定消息和文件相关，加入到t_mess_file_con表中
     * @param param
     */
	void addMessFileCon(AddEditMessParam param);
}