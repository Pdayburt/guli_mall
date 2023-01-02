package com.anatkh.member.controller;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.member.entity.MemberLevel;
import com.anatkh.member.service.MemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("member/memberlevel")
public class MemberLevelController {

    @Autowired
    private MemberLevelService memberLevelService;

    @GetMapping("test")
    public R test(){
        return R.ok().put("data","test");
    }
    @GetMapping("list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberLevelService.queryPage(params);

        return R.ok().put("page", page);
    }

    @PostMapping("save")
    public R save(@RequestBody MemberLevel memberLevel){
        memberLevelService.save(memberLevel);
        return R.ok();
    }

}
