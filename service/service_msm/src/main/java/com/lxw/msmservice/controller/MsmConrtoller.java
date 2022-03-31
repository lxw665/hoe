package com.lxw.msmservice.controller;
;
import com.lxw.commonutils.R;
import com.lxw.msmservice.service.MsmService;
import com.lxw.msmservice.untils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.Temporal;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lxw
 * @Date: 2022/3/23 21:57
 * @Description:
 */

@RestController
@RequestMapping("/edumsm/message")
@CrossOrigin
public class MsmConrtoller {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("sendMsm/{phoneNumber}")
    public R sendMsm(@PathVariable String phoneNumber) {
        //先看redis中有没有存货
        String hasCode = redisTemplate.opsForValue().get(phoneNumber);
        if (!StringUtils.isEmpty(hasCode)) {
            return R.ok();
        }

        String code = "1234";

        boolean flag = msmService.send(phoneNumber, code);
        if (flag) {
            //发送成功把验证码放入redis中
            redisTemplate.opsForValue().set(phoneNumber,code,5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送失败、、、");
        }

    }

}
