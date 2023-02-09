package com.anatkh.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * spu
 * @TableName pms_spu_info_desc
 */
@TableName(value ="pms_spu_info_desc")
@Data
public class SpuInfoDesc implements Serializable {
    /**
     * 
     */
    @TableId(type= IdType.INPUT)
    private Long spuId;

    /**
     * 
     */
    private String decript;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}