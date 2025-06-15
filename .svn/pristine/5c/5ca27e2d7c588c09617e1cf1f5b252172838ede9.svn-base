package org.jit.sose.web.exception;

/**
 * 如403、404、500等异常
 * 
 * @author wangyue
 * @date 2020-04-08 01:56:38
 */
public class HttpException extends BaseException {

	private static final long serialVersionUID = -4655558773387825720L;

	/**
	 * 创建http异常，让全局异常处理器捕获
	 * 
	 * @param code 异常错误码
	 */
	public HttpException(Integer code) {
		super(code, "HTTP异常");
	}

	public HttpException(Integer code, String msg) {
		super(code, msg);
	}

}
