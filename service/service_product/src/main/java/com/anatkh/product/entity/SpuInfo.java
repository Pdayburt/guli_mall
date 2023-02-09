package com.anatkh.product.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * spu
 * @TableName pms_spu_info
 */
@TableName(value ="pms_spu_info")
@Data
public class SpuInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String spuName;

    /**
     * 
     */
    private String spuDescription;

    /**
     * 
     */
    private Long catalogId;

    /**
     * Ʒ
     */
    private Long brandId;

    /**
     * 
     */
    private BigDecimal weight;

    /**
     * 
     */
    private Integer publishStatus;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}