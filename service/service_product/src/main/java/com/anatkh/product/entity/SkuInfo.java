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
        SkuInfo other = (SkuInfo) that;
        return (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getSpuId() == null ? other.getSpuId() == null : this.getSpuId().equals(other.getSpuId()))
            && (this.getSkuName() == null ? other.getSkuName() == null : this.getSkuName().equals(other.getSkuName()))
            && (this.getSkuDesc() == null ? other.getSkuDesc() == null : this.getSkuDesc().equals(other.getSkuDesc()))
            && (this.getCatalogId() == null ? other.getCatalogId() == null : this.getCatalogId().equals(other.getCatalogId()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getSkuDefaultImg() == null ? other.getSkuDefaultImg() == null : this.getSkuDefaultImg().equals(other.getSkuDefaultImg()))
            && (this.getSkuTitle() == null ? other.getSkuTitle() == null : this.getSkuTitle().equals(other.getSkuTitle()))
            && (this.getSkuSubtitle() == null ? other.getSkuSubtitle() == null : this.getSkuSubtitle().equals(other.getSkuSubtitle()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getSaleCount() == null ? other.getSaleCount() == null : this.getSaleCount().equals(other.getSaleCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getSpuId() == null) ? 0 : getSpuId().hashCode());
        result = prime * result + ((getSkuName() == null) ? 0 : getSkuName().hashCode());
        result = prime * result + ((getSkuDesc() == null) ? 0 : getSkuDesc().hashCode());
        result = prime * result + ((getCatalogId() == null) ? 0 : getCatalogId().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getSkuDefaultImg() == null) ? 0 : getSkuDefaultImg().hashCode());
        result = prime * result + ((getSkuTitle() == null) ? 0 : getSkuTitle().hashCode());
        result = prime * result + ((getSkuSubtitle() == null) ? 0 : getSkuSubtitle().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getSaleCount() == null) ? 0 : getSaleCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", skuId=").append(skuId);
        sb.append(", spuId=").append(spuId);
        sb.append(", skuName=").append(skuName);
        sb.append(", skuDesc=").append(skuDesc);
        sb.append(", catalogId=").append(catalogId);
        sb.append(", brandId=").append(brandId);
        sb.append(", skuDefaultImg=").append(skuDefaultImg);
        sb.append(", skuTitle=").append(skuTitle);
        sb.append(", skuSubtitle=").append(skuSubtitle);
        sb.append(", price=").append(price);
        sb.append(", saleCount=").append(saleCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}