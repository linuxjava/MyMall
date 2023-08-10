package com.xiao.mall.controller.pms;

import com.mybatisflex.core.paginate.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.xiao.mall.service.pms.IPmsBrandService;
import com.xiao.mall.entity.pms.PmsBrandEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

import com.xiao.mall.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;

/**
 * 品牌表 控制层。
 *
 * @author robincxiao
 * @since 1.0
 */
@RestController
@RequestMapping("/pmsBrand")
@Api(tags = "品牌表")
public class PmsBrandController {

    @Autowired
    private IPmsBrandService pmsBrandService;

    /**
     * 添加 品牌表
     *
     * @param pmsBrand 品牌表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加品牌表", notes = "添加品牌表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = ""),

            @ApiImplicitParam(name = "name", value = ""),

            @ApiImplicitParam(name = "firstLetter", value = "首字母"),

            @ApiImplicitParam(name = "sort", value = ""),

            @ApiImplicitParam(name = "factoryStatus", value = "是否为品牌制造商：0->不是；1->是"),

            @ApiImplicitParam(name = "showStatus", value = ""),

            @ApiImplicitParam(name = "productCount", value = "产品数量"),

            @ApiImplicitParam(name = "productCommentCount", value = "产品评论数量"),

            @ApiImplicitParam(name = "logo", value = "品牌logo"),

            @ApiImplicitParam(name = "bigPic", value = "专区大图"),

            @ApiImplicitParam(name = "brandStory", value = "品牌故事")
    })
    public CommonResult<Boolean> save(@RequestBody PmsBrandEntity pmsBrand) {
        return CommonResult.success(pmsBrandService.save(pmsBrand));
    }


    /**
     * 根据主键删除品牌表
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "根据主键删除品牌表", notes = "根据主键删除品牌表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", required = true)
    })
    public CommonResult<Boolean> remove(@PathVariable Serializable id) {
        return CommonResult.success(pmsBrandService.removeById(id));
    }


    /**
     * 根据主键更新品牌表
     *
     * @param pmsBrand 品牌表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @ApiOperation(value = "根据主键更新品牌表", notes = "根据主键更新品牌表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", required = true),

            @ApiImplicitParam(name = "name", value = ""),

            @ApiImplicitParam(name = "firstLetter", value = "首字母"),

            @ApiImplicitParam(name = "sort", value = ""),

            @ApiImplicitParam(name = "factoryStatus", value = "是否为品牌制造商：0->不是；1->是"),

            @ApiImplicitParam(name = "showStatus", value = ""),

            @ApiImplicitParam(name = "productCount", value = "产品数量"),

            @ApiImplicitParam(name = "productCommentCount", value = "产品评论数量"),

            @ApiImplicitParam(name = "logo", value = "品牌logo"),

            @ApiImplicitParam(name = "bigPic", value = "专区大图"),

            @ApiImplicitParam(name = "brandStory", value = "品牌故事")
    })
    public CommonResult<Boolean> update(@RequestBody PmsBrandEntity pmsBrand) {
        return CommonResult.success(pmsBrandService.updateById(pmsBrand));
    }


    /**
     * 查询所有品牌表
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有品牌表", notes = "查询所有品牌表")
    public CommonResult<List<PmsBrandEntity>> list() {
        return CommonResult.success(pmsBrandService.list());
    }


    /**
     * 根据品牌表主键获取详细信息。
     *
     * @param id pmsBrand主键
     * @return 品牌表详情
     */
    @GetMapping("/getInfo/{id}")
    @ApiOperation(value = "根据品牌表主键获取详细信息", notes = "根据品牌表主键获取详细信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", required = true)
    })
    public CommonResult<PmsBrandEntity> getInfo(@PathVariable Serializable id) {
        return CommonResult.success(pmsBrandService.getById(id));
    }


    /**
     * 分页查询品牌表
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询品牌表", notes = "分页查询品牌表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageNumber", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true)
    })
    public CommonResult<Page<PmsBrandEntity>> page(Page<PmsBrandEntity> page) {
        return CommonResult.success(pmsBrandService.page(page));
    }
}