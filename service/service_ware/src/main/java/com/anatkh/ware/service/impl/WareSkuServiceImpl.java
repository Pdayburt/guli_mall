package com.anatkh.ware.service.impl;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.Query;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.ware.entity.WareSku;
import com.anatkh.ware.feign.ProductFeignService;
import com.anatkh.ware.service.WareSkuService;
import com.anatkh.ware.vo.SkuHasStockVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.ware.mapper.WareSkuMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author anatkh
* @description 针对表【wms_ware_sku(商品库存)】的数据库操作Service实现
* @createDate 2022-12-13 10:59:10
*/
@Service
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSku>
    implements WareSkuService {

    @Autowired
    private ProductFeignService productFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String skuId = (String) params.get("skuId");
        String wareId = (String) params.get("wareId");
        LambdaQueryWrapper<WareSku> wareSkuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        wareSkuLambdaQueryWrapper
                .eq(!StringUtils.isEmpty(skuId),WareSku::getSkuId,skuId)
                .eq(!StringUtils.isEmpty(wareId),WareSku::getWareId,wareId);
        IPage<WareSku> wareSkuIPage = baseMapper.selectPage(new Query<WareSku>().getPage(params), wareSkuLambdaQueryWrapper);
        return new PageUtils(wareSkuIPage);
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        //1,判断如果没有这个记录则新增
        LambdaQueryWrapper<WareSku> wareSkuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        wareSkuLambdaQueryWrapper
                .eq(WareSku::getSkuId,skuId)
                        .eq(WareSku::getWareId,wareId);
        int count = count(wareSkuLambdaQueryWrapper);
        if (count == 0){
            WareSku wareSku = new WareSku();
            wareSku.setSkuId(skuId);
            wareSku.setWareId(wareId);
            wareSku.setStock(skuNum);
            wareSku.setStockLocked(0);
            try {
                R r = productFeignService.infoBySkuId(skuId);
                Map<String,Object> skuInfo = (Map<String, Object>) r.get("skuInfo");
                if (r.getCode() == 0) {
                    wareSku.setSkuName((String) skuInfo.get("skuName"));
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            save(wareSku);
        }else {
            baseMapper.addStock(skuId,wareId, skuNum);
        }
    }

    @Override
    public List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds) {
        List<SkuHasStockVo> skuHasStockVoList = skuIds.stream().map(skuId -> {
            SkuHasStockVo skuHasStockVo = new SkuHasStockVo();
            Long count = baseMapper.getSkuStock(skuId);
            skuHasStockVo.setSkuId(skuId);
            skuHasStockVo.setHasStock(count == null?false:count>0);
            return skuHasStockVo;
        }).collect(Collectors.toList());
        return skuHasStockVoList;
    }
}




