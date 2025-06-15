package org.jit.sose.web.exception;

import org.jit.sose.enums.ResponseEnum;

/**
 * Redis缓存异常类
 * 
 * @author wangyue
 * @date 2020-04-09 17:33:29
 */
public class RedisException extends BaseException {

	private static final long serialVersionUID = -4655558773387825720L;

	public RedisException() {
		super(ResponseEnum.REDIS_ERROR.code(), ResponseEnum.REDIS_ERROR.msg());
	}

	public RedisException(String msg) {
		super(ResponseEnum.REDIS_ERROR.code(), msg);
	}

}
