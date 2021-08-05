package com.chen.sample1.web.exception;

import com.chen.sample1.tool.message.MsgCode;
import com.chen.sample1.tool.message.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ChenTian
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 全局异常捕捉处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMsg errorHandler(Exception e) {
        logger.error("system error:", e);
        return new ResponseMsg(MsgCode.Error);
    }
}
