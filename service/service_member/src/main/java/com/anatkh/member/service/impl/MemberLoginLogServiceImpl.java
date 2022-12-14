package com.anatkh.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.member.entity.MemberLoginLog;
import com.anatkh.member.service.MemberLoginLogService;
import com.anatkh.member.mapper.MemberLoginLogMapper;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【ums_member_login_log】的数据库操作Service实现
* @createDate 2022-12-13 10:54:45
*/
@Service
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogMapper, MemberLoginLog>
    implements MemberLoginLogService{

}




