package com.anatkh.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName sms_spu_bounds
 */
@TableName(value ="sms_spu_bounds")
@Data
public class SpuBounds implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long spuId;

    /**
     * 
     */
    private BigDecimal growBounds;

    /**
     * 
     */
    private BigDecimal buyBounds;

    /**
     * 
     */
    private Integer work;

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
        SpuBounds other = (SpuBounds) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpuId() == null ? other.getSpuId() == null : this.getSpuId().equals(other.getSpuId()))
            && (this.getGrowBounds() == null ? other.getGrowBounds() == null : this.getGrowBounds().equals(other.getGrowBounds()))
            && (this.getBuyBounds() == null ? other.getBuyBounds() == null : this.getBuyBounds().equals(other.getBuyBounds()))
            && (this.getWork() == null ? other.getWork() == null : this.getWork().equals(other.getWork()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpuId() == null) ? 0 : getSpuId().hashCode());
        result = prime * result + ((getGrowBounds() == null) ? 0 : getGrowBounds().hashCode());
        result = prime * result + ((getBuyBounds() == null) ? 0 : getBuyBounds().hashCode());
        result = prime * result + ((getWork() == null) ? 0 : getWork().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", spuId=").append(spuId);
        sb.append(", growBounds=").append(growBounds);
        sb.append(", buyBounds=").append(buyBounds);
        sb.append(", work=").append(work);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}