package org.jit.sose.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.jit.sose.enums.ResponseEnum;
import org.jit.sose.util.ResponseUtil;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 全局捕获异常
 *
 * @author wangyue
 * @date 2019年4月4日 下午11:08:20
 */
@Slf4j
@ControllerAdvice(basePackages = {"org.jit.sose.controller", "org.jit.sose.redis", "org.jit.sose.web"})
// 作为全局异常处理的切面类，可以设置包的范围
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public String handleBaseException(BaseException e) {
        log.error("自定义异常：" + e.getMsg());
        e.printStackTrace();
        return ResponseUtil.error(ResponseEnum.ERROR.code(), e.getMsg());
    }

    /**
     * 自定义数据格式异常
     */
    @ExceptionHandler(DataFormatException.class)
    public String handleDataFormatException(DataFormatException e) {
        log.error("数据格式异常：" + e.getMsg());
        e.printStackTrace();
        return ResponseUtil.error(e.getCode(), e.getMsg());
    }

    /**
     * 参数传入异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("参数传入异常：");
        e.printStackTrace();
        return ResponseUtil.error(ResponseEnum.DATA_FORMAT_ERROR.code(), ResponseEnum.DATA_FORMAT_ERROR.msg());
    }

    /**
     * hibernate-validator统一异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuffer stringBuffer = new StringBuffer();
        Integer count = 1;
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                //该格式仅仅作为response展示和log作用，前端应自己做校验
                stringBuffer.append("(" + (count++) + ")" + fieldError.getObjectName() + "." + fieldError.getField() + ":" + fieldError.getDefaultMessage() + "     ");
            }
        }
        log.error(stringBuffer.toString());
        return ResponseUtil.error(ResponseEnum.DATA_FORMAT_ERROR.code(), stringBuffer.toString());
    }

    /**
     * redis缓存异常
     */
    @ExceptionHandler(RedisException.class)
    public String handleRedisException(RedisException e) {
        log.error("Redis缓存异常：" + e.getMsg());
        e.printStackTrace();
        return ResponseUtil.error(e.getCode(), e.getMsg());
    }

    /**
     * 捕获运行时异常,取代springboot跳转500错误页面
     */
    @ExceptionHandler(RuntimeException.class) // 设置具体捕获异常类
    public String RuntimeException(RuntimeException e) {
        System.out.println("*****************全局捕获运行时异常****************");
//		LogUtil.logResult("RuntimeException", e);
        e.printStackTrace();
        return ResponseUtil.error(ResponseEnum.ERROR.code(), "全局捕获运行时异常");
    }

    /**
     * 捕获空指针异常
     *
     * @param e 异常内容
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public String NullPointException(Exception e) {
        System.out.println("*****************全局捕获空指针异常*****************");
//		LogUtil.logResult("NullPointerException", e);
        e.printStackTrace();
        return ResponseUtil.error(ResponseEnum.ERROR.code(), "全局捕获空指针异常");
    }

    // 类型转换异常
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public String classCastExceptionHandler(ClassCastException ex) {
        ex.printStackTrace();
        return null;
    }

    // IO异常
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public String iOExceptionHandler(IOException ex) {
        ex.printStackTrace();
        return null;
    }

    // 未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public String noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        ex.printStackTrace();
        return null;
    }

    // 数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        ex.printStackTrace();
        return null;
    }

}
