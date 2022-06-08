package com.lxw.eduorder.service;

import com.lxw.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author lxw
 * @since 2022-04-25
 */
public interface PayLogService extends IService<PayLog> {

    /**
     * 获取二维码地址等信息
     * @param orderNo 订单号
     * @return 返回二维码地址等信息的map
     */
    Map createNative(String orderNo);

    /**
     * 添加支付记录，更新订单状态
     * @param map
     */
    void updateOrderStatus(Map<String, String> map);

    /**
     * 根据订单号查询订单状态
     * @param orderNo 订单号
     * @return 订单信息
     */
    Map<String, String> queryPayStatus(String orderNo);
}
