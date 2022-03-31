package com.lxw.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lxw
 * @Date: 2022/3/9 14:19
 * @Description:
 */
@Data
public class CoursePublishVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;//课程id

    private String title; //课程名称

    private String cover; //封面

    private Integer lessonNum;//课时数

    private String subjectLevelOne;//一级分类

    private String subjectLevelTwo;//二级分类

    private String teacherName;//讲师名称

    private String price;//价格 ，只用于显示

}

