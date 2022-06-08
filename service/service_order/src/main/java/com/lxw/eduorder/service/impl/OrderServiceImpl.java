package com.lxw.eduorder.service.impl;

import com.lxw.commonutils.ordervo.CourseWebVoOrder;
import com.lxw.commonutils.ordervo.UcenterMemberOrder;
import com.lxw.eduorder.client.EduClient;
import com.lxw.eduorder.client.UcenterClient;
import com.lxw.eduorder.entity.Order;
import com.lxw.eduorder.mapper.OrderMapper;
import com.lxw.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxw.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author lxw
 * @since 2022-04-25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrders(String courseId, String memberIdByJwtToken) {
        //通过远程调用换取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberIdByJwtToken);

        //通过远程调用获得课程信息
        CourseWebVoOrder courseInfo = eduClient.getCourseInfoOrder(courseId);
        //构造一个订单
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo()); //订单号
        order.setCourseId(courseId);
        order.setPayType(1);//支付类型 1.微信 2.支付宝
        order.setStatus(0); //支付状态：（ 0：已支付，1：未支付 ）
        order.setTotalFee(courseInfo.getPrice());
        order.setCourseTitle(courseInfo.getTitle());
        order.setTeacherName(courseInfo.getTeacherName());
        order.setCourseCover(courseInfo.getCover());
        order.setCourseId(courseId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setMemberId(memberIdByJwtToken);
        //加入数据库
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
