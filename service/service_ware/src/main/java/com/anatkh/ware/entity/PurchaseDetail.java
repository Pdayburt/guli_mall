package com.anatkh.ware.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName wms_purchase_detail
 */
@TableName(value ="wms_purchase_detail")
@Data
public class PurchaseDetail implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 采购单id
     */
    private Long purchaseId;

    /**
     * 采购商品id
     */
    private Long skuId;

    /**
     * 采购数量
     */
    private Integer skuNum;

    /**
     * 采购金额
     */
    private BigDecimal skuPrice;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 状态[0新建，1已分配，2正在采购，3已完成，4采购失败]
     */
    private Integer status;

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
        PurchaseDetail other = (PurchaseDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPurchaseId() == null ? other.getPurchaseId() == null : this.getPurchaseId().equals(other.getPurchaseId()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getSkuNum() == null ? other.getSkuNum() == null : this.getSkuNum().equals(other.getSkuNum()))
            && (this.getSkuPrice() == null ? other.getSkuPrice() == null : this.getSkuPrice().equals(other.getSkuPrice()))
            && (this.getWareId() == null ? other.getWareId() == null : this.getWareId().equals(other.getWareId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPurchaseId() == null) ? 0 : getPurchaseId().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getSkuNum() == null) ? 0 : getSkuNum().hashCode());
        result = prime * result + ((getSkuPrice() == null) ? 0 : getSkuPrice().hashCode());
        result = prime * result + ((getWareId() == null) ? 0 : getWareId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", purchaseId=").append(purchaseId);
        sb.append(", skuId=").append(skuId);
        sb.append(", skuNum=").append(skuNum);
        sb.append(", skuPrice=").append(skuPrice);
        sb.append(", wareId=").append(wareId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}