package com.lxw.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxw.eduservice.entity.EduCourse;
import com.lxw.eduservice.entity.frontvo.CourseWebVo;
import com.lxw.eduservice.entity.vo.CoursePublishVo;

/**
 * @Author: lxw
 * @Date: 2022/3/7 17:52
 * @Description:
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}
