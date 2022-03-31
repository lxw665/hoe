package com.lxw.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lxw.eduservice.entity.EduCourse;
import com.lxw.eduservice.entity.frontvo.CourseFrontVo;
import com.lxw.eduservice.entity.frontvo.CourseWebVo;
import com.lxw.eduservice.entity.vo.CourseInfoVo;
import com.lxw.eduservice.entity.vo.CoursePublishVo;
import com.lxw.eduservice.entity.vo.CourseQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxw
 * @Date: 2022/3/7 17:58
 * @Description:
 */
public interface EduCourseService extends IService<EduCourse> {
    /**
     * 将表单信息保存
     * @param courseInfoVo 课程基本信息表单实体类
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据课程id查询课程信息
     * @param courseId
     * @return
     */
    CourseInfoVo getCourseInfo(String courseId);

    /**
     * 修改课程信息
     * @param courseInfoVo 课程信息表单对象
     */
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据课程id查询课程信息 自定义mapper版
     * @param courseId
     * @return
     */
    CoursePublishVo publishCourseInfo(String courseId);


    Page<EduCourse> pageQuery(Page<EduCourse> pageCourse, CourseQuery courseQuery);

    /**
     * 根据id删除课程
     * @param courseId
     * @return
     */
    boolean removeCourse(String courseId);

    /**
     * 根据讲师id查询讲师的课程信息
     * @return
     */
    List<EduCourse> getCourseByTeacherId(String id);

    /**
     * 分页条件查询课程信息
     * @param pageCourse 分页条件
     * @param courseFrontVo 条件查询
     * @return 课程信息的map
     */
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    /**
     * 根据课程id 查询课程的基本信息
     * @param courseId 课程id
     * @return 返回课程基本信息CourseWebVo类
     */
    CourseWebVo getBaseCourseInfo(String courseId);
}
