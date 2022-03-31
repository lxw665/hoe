package com.lxw.eduservice.client;

import com.lxw.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: lxw
 * @Date: 2022/3/13 21:05
 * @Description:
 */
@Component
public class VodClientImpl implements VodClient{


    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错了");
    }
}
