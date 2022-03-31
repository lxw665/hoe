package com.lxw.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: lxw
 * @Date: 2022/3/5 21:36
 * @Description:
 */
public interface OssService {

    String uploadFileAvatar(MultipartFile file);
}
