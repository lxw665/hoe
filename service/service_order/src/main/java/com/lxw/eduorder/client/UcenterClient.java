package com.lxw.eduorder.client;

import com.lxw.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: lxw
 * @Date: 2022/4/26 19:50
 * @Description:
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping("/centerservice/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
