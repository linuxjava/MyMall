package com.xiao.mall.controller;

import com.xiao.mall.common.api.CommonResult;
import com.xiao.mall.common.api.ResultCode;
import com.xiao.mall.common.exceptionhandler.CustomException;
import com.xiao.mall.entity.pms.LoginEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.apache.ibatis.jdbc.Null;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Api(tags = "全局异常测试")
public class ExceptionController {
    private String aa = null;

    /**
     * 登录获取token
     * @return
     */
    @PostMapping("/exception")
    @ApiOperation(value = "触发全局异常", notes = "触发全局异常")
    public CommonResult<Null> globalException() {
        int length = aa.length();
        return CommonResult.success(null);
    }

    @PostMapping("/exception/custom")
    @ApiOperation(value = "触发自定义异常", notes = "触发自定义异常")
    public CommonResult<Null> customException() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new CustomException(ResultCode.EXCEPTION_TEST.getCode(), ResultCode.EXCEPTION_TEST.getMessage());
        }

        return CommonResult.success(null);
    }
}
