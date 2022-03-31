package com.lxw.msmservice.service;

/**
 * @Author: lxw
 * @Date: 2022/3/23 22:01
 * @Description:
 */
public interface MsmService {

    boolean send(String phone, String code);

}
