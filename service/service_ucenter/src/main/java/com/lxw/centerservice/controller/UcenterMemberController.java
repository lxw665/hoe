package com.lxw.centerservice.controller;


import com.lxw.centerservice.entity.UcenterMember;
import com.lxw.centerservice.entity.vo.RegisterVo;
import com.lxw.centerservice.service.UcenterMemberService;
import com.lxw.commonutils.JwtUtils;
import com.lxw.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author lxw
 * @since 2022-03-24
 */
@RestController
@RequestMapping("/centerservice/member")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    //登陆
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember ucenterMember) {
        String token = memberService.login(ucenterMember);
        return R.ok().data("token", token);
    }

    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    //挺不安全的接口有token就能获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //获得用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        //根据id查询数据库
        UcenterMember ucenterMember = memberService.getById(memberId);

        return R.ok().data("member", ucenterMember);
    }
}

