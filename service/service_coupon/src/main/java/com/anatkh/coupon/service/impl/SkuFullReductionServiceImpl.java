package com.anatkh.coupon.service.impl;

import com.anatkh.commonUtil.to.MemberPrice;
import com.anatkh.commonUtil.to.SkuReductionTo;
import com.anatkh.coupon.entity.SkuFullReduction;
import com.anatkh.coupon.entity.SkuLadder;
import com.anatkh.coupon.service.MemberPriceService;
import com.anatkh.coupon.service.SkuFullReductionService;
import com.anatkh.coupon.service.SkuLadderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.coupon.mapper.SkuFullReductionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author anatkh
* @description 针对表【sms_sku_full_reduction】的数据库操作Service实现
* @createDate 2022-12-13 10:53:04
*/
@Service
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionMapper, SkuFullReduction>
    implements SkuFullReductionService {

    @Autowired
    private SkuLadderService skuLadderService;
    @Autowired
    private MemberPriceService memberPriceService;

    @Override
    public void saveSkuReductionTo(SkuReductionTo skuReductionTo) {
        //5.4 sku的优惠满减信息 sms_sku_ladder/sms_sku_full_reduction/sms_member_price
        SkuLadder skuLadder = new SkuLadder();
        skuLadder.setSkuId(skuReductionTo.getSkuId());
        skuLadder.setFullCount(skuReductionTo.getFullCount());
        skuLadder.setDiscount(skuReductionTo.getDiscount());
        skuLadder.setAddOther(skuReductionTo.getCountStatus());
        if (skuReductionTo.getFullCount()>0){
            skuLadderService.save(skuLadder);
        }
//        sms_sku_full_reduction
        SkuFullReduction skuFullReduction = new SkuFullReduction();
        BeanUtils.copyProperties(skuReductionTo,skuFullReduction);
        if (skuFullReduction.getFullPrice().compareTo(new BigDecimal("0"))==1){
            baseMapper.insert(skuFullReduction);
        }
//         sms_member_price
        List<MemberPrice> memberPrice = skuReductionTo.getMemberPrice();
        List<com.anatkh.coupon.entity.MemberPrice> memberPriceList = memberPrice.stream().map(price -> {
            com.anatkh.coupon.entity.MemberPrice priceMemberEntity = new com.anatkh.coupon.entity.MemberPrice();
            priceMemberEntity.setSkuId(skuReductionTo.getSkuId());
            priceMemberEntity.setMemberLevelId(price.getId());
            priceMemberEntity.setMemberLevelName(price.getName());
            priceMemberEntity.setMemberPrice(price.getPrice());
            priceMemberEntity.setAddOther(1);
            return priceMemberEntity;
        }).filter(price->{
            return price.getMemberPrice().compareTo(new BigDecimal("0")) == 1;
        }).collect(Collectors.toList());
        memberPriceService.saveBatch(memberPriceList);
    }

}




