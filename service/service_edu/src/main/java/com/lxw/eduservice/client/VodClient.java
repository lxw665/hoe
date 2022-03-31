package com.lxw.eduservice.client;

import com.lxw.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: lxw
 * @Date: 2022/3/11 8:50
 * @Description:
 */
@Component
@FeignClient(value = "service-vod",fallback = VodClientImpl.class)
public interface VodClient {
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);
}
