package com.lxw.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: lxw
 * @Date: 2022/3/10 14:35
 * @Description:
 */
public interface VodService {
    String uploadVideoAly(MultipartFile file);
}
