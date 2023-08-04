package com.xiao.mall.entity.pms;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.lang.Long;
import java.lang.String;
import java.sql.Blob;
import java.lang.Integer;

/**
 * 品牌表 实体类。
 *
 * @author robincxiao
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "品牌表", description = "品牌表")
@Table("pms_brand")
public class PmsBrandEntity {

    @Id(keyType = KeyType.Auto)
    private Long id;

    @Column(value = "name")
    private String name;

    /**
     * 首字母
     */
    @ApiModelProperty(value = "首字母")
    @Column(value = "first_letter")
    private String firstLetter;

    @Column(value = "sort")
    private Integer sort;

    /**
     * 是否为品牌制造商：0->不是；1->是
     */
    @ApiModelProperty(value = "是否为品牌制造商：0->不是；1->是")
    @Column(value = "factory_status")
    private Integer factoryStatus;

    @Column(value = "show_status")
    private Integer showStatus;

    /**
     * 产品数量
     */
    @ApiModelProperty(value = "产品数量")
    @Column(value = "product_count")
    private Integer productCount;

    /**
     * 产品评论数量
     */
    @ApiModelProperty(value = "产品评论数量")
    @Column(value = "product_comment_count")
    private Integer productCommentCount;

    /**
     * 品牌logo
     */
    @ApiModelProperty(value = "品牌logo")
    @Column(value = "logo")
    private String logo;

    /**
     * 专区大图
     */
    @ApiModelProperty(value = "专区大图")
    @Column(value = "big_pic")
    private String bigPic;

    /**
     * 品牌故事
     */
    @ApiModelProperty(value = "品牌故事")
    @Column(value = "brand_story")
    private String brandStory;


}
