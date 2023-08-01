package com.xiao.mall.pms.brand.controller;


import com.xiao.mall.R;
import com.xiao.mall.pms.brand.entity.PmsBrand;
import com.xiao.mall.pms.brand.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author robin
 * @since 2023-08-01
 */
@RestController
@RequestMapping("/pms/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    //查询所有banner
    @GetMapping("getAllBanner")
    public R getAll() {
        List<PmsBrand> list = brandService.getAll();
        return R.ok().data("list", list);
    }
}

