package com.xiao.mall.pms.brand.service;

import com.xiao.mall.pms.brand.entity.PmsBrand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author robin
 * @since 2023-08-01
 */
public interface PmsBrandService extends IService<PmsBrand> {
    List<PmsBrand> getAll();
}
