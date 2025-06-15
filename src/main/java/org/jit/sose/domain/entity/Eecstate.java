package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("c_eecstate")
public class Eecstate {
	private Integer id;

	private String table;

	private String tableName;

	private String colm;

	private String colmName;

	private String code;

	private String codeName;

	private Integer seq;

	private String remark;

	private String state;

	private Date createDate;

	private Date updateDate;

}