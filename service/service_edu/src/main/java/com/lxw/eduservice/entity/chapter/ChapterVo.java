package com.lxw.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lxw
 * @Date: 2022/3/8 12:04
 * @Description: 章节实体类
 */
@Data
public class ChapterVo {
    private String id; //章节ID
    private String title; //章节名称

    //表示小节
    private List<VideoVo> children = new ArrayList<>();
}
