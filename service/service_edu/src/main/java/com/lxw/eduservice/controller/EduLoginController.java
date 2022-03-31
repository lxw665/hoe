package com.lxw.eduservice.controller;

import com.lxw.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lxw
 * @Date: 2022/3/3 17:11
 * @Description:
 */

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin //解决跨域问题
public class EduLoginController {

    //login
    @PostMapping("login")
    public R login() {

        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("info")
    public R info() {

        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","http://nimg.ws.126.net/?url=http://dingyue.ws.126.net/2021/1013/d993d19fj00r0x02e0027c000cf00j5c.jpg&thumbnail=650x2147483647&quality=80&type=jpg");
    }

}
