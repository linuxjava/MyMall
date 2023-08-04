package com.xiao.mall.service.pms.impl;


import org.springframework.stereotype.Service;
import com.xiao.mall.service.pms.IPmsBrandService;
import com.xiao.mall.entity.pms.PmsBrandEntity;
import com.xiao.mall.dao.pms.PmsBrandMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 品牌表 服务层实现。
 *
 * @author robincxiao
 * @since 1.0
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrandEntity> implements IPmsBrandService {

}