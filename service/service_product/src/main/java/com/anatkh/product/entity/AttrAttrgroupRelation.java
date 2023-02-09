package com.anatkh.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pms_attr_attrgroup_relation
 */
@TableName(value ="pms_attr_attrgroup_relation")
@Data
public class AttrAttrgroupRelation implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long attrId;

    /**
     * 
     */
    private Long attrGroupId;

    /**
     * 
     */
    private Integer attrSort;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}