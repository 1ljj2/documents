package org.jit.sose.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.ArchiveExampleProcess;
import org.jit.sose.domain.param.ListChargingArchiveParam;
import org.jit.sose.domain.vo.ListChargingArchiveVo;

import java.util.List;

public interface ArchiveExampleProcessMapper extends BaseMapper<ArchiveExampleProcess> {
	
    /**
     * 根据实例标识查询当前实例由谁发起
     *
     * @param exampleId
     * @return
     */
    String selectUserStep(@Param("exampleId") Integer exampleId);

	/**
	 * 禁用系统档案
	 * @param id
	 */
	void disableArchiveExample(Integer id);
	
	/**
	 * 根据【用户档案编号、用户档案名称、负责人、创建者、分页参数】筛选我的档案
	 * @param param
	 * @return
	 */
	List<ListChargingArchiveVo> listChargingArchiveByCondition(ListChargingArchiveParam param);

    /**
     * 新增或更新个人档案实体
     * @param record
     * @return
     */
    int insertOrUpdate(ArchiveExampleProcess record);

    /**
     * 按照档案模板id和用户id查询
     * @param record
     * @return
     */
    ArchiveExampleProcess selectByUserIdAndArchiveTemplateId(ArchiveExampleProcess record);

    int deleteByPrimaryKey(Integer id);

    int insert(ArchiveExampleProcess record);

    int insertSelective(ArchiveExampleProcess record);

    ArchiveExampleProcess selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArchiveExampleProcess record);

    int updateByPrimaryKey(ArchiveExampleProcess record);

    int addOneExample(ArchiveExampleProcess archiveExampleProcessParam);
}