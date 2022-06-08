package com.lxw.eduservice.client;

import com.lxw.commonutils.R;
import com.lxw.eduservice.entity.vo.UcenterVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: lxw
 * @Date: 2022/3/31 20:53
 * @Description:
 */
@Component
@FeignClient(name="service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {
    @GetMapping("/centerservice/member/getInfoUc/{id}")
    public UcenterVo getInfoUc(@PathVariable String id);
}
