package com.lxw.eduservice.entity.chapter;

import lombok.Data;

/**
 * @Author: lxw
 * @Date: 2022/3/8 12:04
 * @Description: 小结实体类
 */
@Data
public class VideoVo {
    private String id; //小节ID
    private String title; //小节名称
    private String videoSourceId; //视频id
}
