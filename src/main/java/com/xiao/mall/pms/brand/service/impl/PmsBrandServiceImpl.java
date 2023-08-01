package com.xiao.mall.pms.brand.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.mall.pms.brand.entity.PmsBrand;
import com.xiao.mall.pms.brand.mapper.PmsBrandMapper;
import com.xiao.mall.pms.brand.service.PmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author robin
 * @since 2023-08-01
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Override
    public List<PmsBrand> getAll() {
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        return baseMapper.selectList(wrapper);
    }
}
