package com.lxw.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxw.commonutils.JwtUtils;
import com.lxw.commonutils.R;
import com.lxw.eduorder.client.EduClient;
import com.lxw.eduorder.client.UcenterClient;
import com.lxw.eduorder.entity.Order;
import com.lxw.eduorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Retention;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author lxw
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    //生成订单的方法
    @PostMapping("createOrder/{courseId}")
    public R saveOrder(@PathVariable String courseId, HttpServletRequest request) {
        //根据token获得用户id
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = orderService.createOrders(courseId, memberIdByJwtToken);
        return R.ok().data("orderId", orderNo);
    }

    //根据订单id查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderId);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("item", order);
    }

}

