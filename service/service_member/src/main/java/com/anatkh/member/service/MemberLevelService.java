package com.anatkh.member.service;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.member.entity.MemberLevel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author anatkh
* @description 针对表【ums_member_level】的数据库操作Service
* @createDate 2022-12-13 10:54:45
*/
public interface MemberLevelService extends IService<MemberLevel> {

    PageUtils queryPage(Map<String, Object> params);
}
