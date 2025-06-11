package org.jit.sose.web.exception;

import org.jit.sose.enums.ResponseEnum;

/**
 * 数据格式异常类
 * 
 * @author wangyue
 * @date 2019-06-06 01:01:36
 */
public class DataFormatException extends BaseException {

	private static final long serialVersionUID = -4655558773387825720L;

	public DataFormatException() {
		super(ResponseEnum.DATA_FORMAT_ERROR.code(), ResponseEnum.DATA_FORMAT_ERROR.msg());
	}

	public DataFormatException(String msg) {
		super(ResponseEnum.DATA_FORMAT_ERROR.code(), msg);
	}

}
