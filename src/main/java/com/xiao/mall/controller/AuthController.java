package com.xiao.mall.controller;

import com.xiao.mall.common.api.CommonResult;
import com.xiao.mall.entity.pms.LoginEntity;
import com.xiao.mall.entity.pms.PmsBrandEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.jdbc.Null;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Api(tags = "Security授权测试")
public class AuthController {

    /**
     * 登录获取token
     * @return
     */
    @PostMapping("/admin/acl/login")
    @ApiOperation(value = "登录获取token", notes = "登录获取token")
    public CommonResult<Null> login(@RequestBody LoginEntity entity) {
        return CommonResult.success(null);
    }

    /**
     * 该接口有权限访问
     * @return
     */
    @GetMapping("/security/auth")
    @ApiOperation(value = "该接口有权限访问", notes = "该接口有权限访问")
    @PreAuthorize("hasAnyAuthority('security.auth')")
    public CommonResult<Null> auth() {
        return CommonResult.success(null);
    }

    /**
     * 该接口无权限访问
     * @return
     */
    @GetMapping("/security/unauth")
    @ApiOperation(value = "该接口无权限访问", notes = "该接口无权限访问")
    @PreAuthorize("hasAnyAuthority('security.unauth')")
    public CommonResult<Null> unAuth() {
        return CommonResult.success(null);
    }
}
