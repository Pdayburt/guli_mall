package com.anatkh.member.service.impl;

import com.anatkh.member.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.member.entity.Member;
import com.anatkh.member.mapper.MemberMapper;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【ums_member】的数据库操作Service实现
* @createDate 2022-12-13 10:54:45
*/
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService {

}




