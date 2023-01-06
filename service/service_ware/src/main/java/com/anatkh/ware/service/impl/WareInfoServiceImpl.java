package com.anatkh.ware.service.impl;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.Query;
import com.anatkh.ware.entity.WareInfo;
import com.anatkh.ware.service.WareInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.ware.mapper.WareInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
* @author anatkh
* @description 针对表【wms_ware_info(仓库信息)】的数据库操作Service实现
* @createDate 2022-12-13 10:59:10
*/
@Service
public class WareInfoServiceImpl extends ServiceImpl<WareInfoMapper, WareInfo>
    implements WareInfoService {

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        String key = (String) params.get("key");
        LambdaQueryWrapper<WareInfo> wareInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        wareInfoLambdaQueryWrapper
                .and(!StringUtils.isEmpty(key),wrapper->{
                    wrapper.eq(WareInfo::getId,key)
                            .or()
                            .like(WareInfo::getName,key)
                            .or()
                            .like(WareInfo::getAddress,key)
                            .or()
                            .like(WareInfo::getAreacode,key);
                });
        IPage<WareInfo> wareInfoIPage = baseMapper.selectPage(new Query<WareInfo>().getPage(params), wareInfoLambdaQueryWrapper);
        IPage<WareInfo> page = new Query<WareInfo>().getPage(params);
        return new PageUtils(wareInfoIPage);
    }
}




