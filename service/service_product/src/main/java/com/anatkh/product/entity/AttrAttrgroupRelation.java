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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AttrAttrgroupRelation other = (AttrAttrgroupRelation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAttrId() == null ? other.getAttrId() == null : this.getAttrId().equals(other.getAttrId()))
            && (this.getAttrGroupId() == null ? other.getAttrGroupId() == null : this.getAttrGroupId().equals(other.getAttrGroupId()))
            && (this.getAttrSort() == null ? other.getAttrSort() == null : this.getAttrSort().equals(other.getAttrSort()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAttrId() == null) ? 0 : getAttrId().hashCode());
        result = prime * result + ((getAttrGroupId() == null) ? 0 : getAttrGroupId().hashCode());
        result = prime * result + ((getAttrSort() == null) ? 0 : getAttrSort().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", attrId=").append(attrId);
        sb.append(", attrGroupId=").append(attrGroupId);
        sb.append(", attrSort=").append(attrSort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}