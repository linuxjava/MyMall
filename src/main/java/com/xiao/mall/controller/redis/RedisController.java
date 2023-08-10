package com.xiao.mall.controller.redis;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;

import com.xiao.mall.common.api.CommonResult;
import com.xiao.mall.entity.pms.PmsBrandEntity;
import com.xiao.mall.service.pms.IPmsBrandService;
import com.xiao.mall.service.redis.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis测试Controller
 * Created by macro on 2020/3/3.
 */
@Api(tags = "RedisController", description = "redis测试")
@Controller
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private IPmsBrandService brandService;

    @ApiOperation("测试简单缓存")
    @RequestMapping(value = "/simpleTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrandEntity> simpleTest() {
        List<PmsBrandEntity> brandList = brandService.list();
        PmsBrandEntity brand = brandList.get(0);
        String key = "redis:simple:" + brand.getId();
        redisService.set(key, brand);
        PmsBrandEntity cacheBrand = (PmsBrandEntity) redisService.get(key);
        return CommonResult.success(cacheBrand);
    }

    @ApiOperation("测试Hash结构的缓存")
    @RequestMapping(value = "/hashTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrandEntity> hashTest() {
        List<PmsBrandEntity> brandList = brandService.list();
        PmsBrandEntity brand = brandList.get(0);
        String key = "redis:hash:" + brand.getId();
        Map<String, Object> value = BeanUtil.beanToMap(brand);
        redisService.hSetAll(key, value);
        Map<Object, Object> cacheValue = redisService.hGetAll(key);
        PmsBrandEntity cacheBrand = BeanUtil.mapToBean(cacheValue, PmsBrandEntity.class, true);
        return CommonResult.success(cacheBrand);
    }

    @ApiOperation("测试Set结构的缓存")
    @RequestMapping(value = "/setTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Set<Object>> setTest() {
        List<PmsBrandEntity> brandList = brandService.list();
        String key = "redis:set:all";
        redisService.sAdd(key, (Object[]) ArrayUtil.toArray(brandList, PmsBrandEntity.class));
        redisService.sRemove(key, brandList.get(0));
        Set<Object> cachedBrandList = redisService.sMembers(key);
        return CommonResult.success(cachedBrandList);
    }

    @ApiOperation("测试List结构的缓存")
    @RequestMapping(value = "/listTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Object>> listTest() {
        List<PmsBrandEntity> brandList = brandService.list();
        String key = "redis:list:all";
        redisService.lPushAll(key, (Object[]) ArrayUtil.toArray(brandList, PmsBrandEntity.class));
        redisService.lRemove(key, 1, brandList.get(0));
        List<Object> cachedBrandList = redisService.lRange(key, 0, 3);
        return CommonResult.success(cachedBrandList);
    }
}
