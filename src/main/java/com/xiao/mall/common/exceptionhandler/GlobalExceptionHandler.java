package com.xiao.mall.common.exceptionhandler;

import com.xiao.mall.common.ExceptionUtils;
import com.xiao.mall.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public CommonResult<Null> error(Exception e) {
        return CommonResult.failed("执行了全局异常处理..");
    }

    //自定义异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody //为了返回数据
    public CommonResult<Null> error(CustomException e) {
        //写入自定义信息
        log.error("执行了自定义异常处理..");
        //写入完成的堆栈信息
        log.error(ExceptionUtils.getStackTrace(e));

        return CommonResult.failed(e.getCode(), e.getMsg());
    }
}
