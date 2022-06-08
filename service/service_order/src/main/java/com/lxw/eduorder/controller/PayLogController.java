package com.lxw.eduorder.controller;


import com.lxw.commonutils.R;
import com.lxw.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author lxw
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/eduorder/paylog")
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        //返回二维码地址什么的
        Map map = payLogService.createNative(orderNo);
        System.out.println("二维码的map集合" + map);
        return R.ok().data(map);
    }

    //查询订单状态
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("订单状态的map集合" + map);
        if (map == null) {
            return R.error().message("出错了！");
        }
        if (map.get("trade_state").equals("SUCCESS")) {
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功！");
        }
        return R.ok().code(25000).message("正在支付中...");
    }

}

