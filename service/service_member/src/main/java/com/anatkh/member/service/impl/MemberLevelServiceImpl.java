package com.anatkh.member.service.impl;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.member.entity.MemberLevel;
import com.anatkh.member.service.MemberLevelService;
import com.anatkh.member.mapper.MemberLevelMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author anatkh
* @description 针对表【ums_member_level】的数据库操作Service实现
* @createDate 2022-12-13 10:54:45
*/
@Service
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevel>
    implements MemberLevelService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberLevel> page = this.page(
                new Query<MemberLevel>().getPage(params),
                new QueryWrapper<MemberLevel>()
        );
        return new PageUtils(page);
    }
}




