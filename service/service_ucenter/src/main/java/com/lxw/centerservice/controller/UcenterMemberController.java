package com.lxw.centerservice.controller;


import com.lxw.centerservice.entity.UcenterMember;
import com.lxw.centerservice.entity.vo.RegisterVo;
import com.lxw.centerservice.entity.vo.UcenterVo;
import com.lxw.centerservice.service.UcenterMemberService;
import com.lxw.commonutils.JwtUtils;
import com.lxw.commonutils.R;
import com.lxw.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.beans.BeanUtils;
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

    //根据用户id获取用户信息
    //返回id，头像，
    @PostMapping("getInfoUc/{id}")
    public R getInfoUc(@PathVariable String id) {
        UcenterMember byId = memberService.getById(id);
        UcenterVo ucenterVo = new UcenterVo();
        BeanUtils.copyProperties(byId, ucenterVo);
        return R.ok().data("ucenterVo",ucenterVo);
    }

    //根据用户id获得用户信息
    /**
     *  因为跨模块所以不能用原本的UcenterMember对象，而是在common里定义了一个新的UcenterMemberOrder（其实是一模一样的）
     *  然后返回的时候把得到的UcenterMember对象的值赋给ucenterMemberOrder使用了BeanUtils的copy方法
     */

    @GetMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember info = memberService.getById(id);
        //把info复制给UcenterMemberOrder
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(info, ucenterMemberOrder);
        return ucenterMemberOrder;
    }
}

