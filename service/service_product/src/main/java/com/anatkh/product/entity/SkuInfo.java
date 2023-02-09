package com.anatkh.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * sku
 * @TableName pms_sku_info
 */
@TableName(value ="pms_sku_info")
@Data
public class SkuInfo implements Serializable {
    /**
     * skuId
     */
    @TableId(type = IdType.AUTO)
    private Long skuId;

    /**
     * spuId
     */
    private Long spuId;

    /**
     * sku
     */
    private String skuName;

    /**
     * sku
     */
    private String skuDesc;

    /**
     * 
     */
    private Long catalogId;

    /**
     * Ʒ
     */
    private Long brandId;

    /**
     * Ĭ
     */
    private String skuDefaultImg;

    /**
     * 
     */
    private String skuTitle;

    /**
     * 
     */
    private String skuSubtitle;

    /**
     * 
     */
    private BigDecimal price;

    /**
     * 
     */
    private Long saleCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}