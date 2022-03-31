package com.lxw.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxw.eduservice.entity.EduCourse;
import com.lxw.eduservice.entity.EduCourseDescription;
import com.lxw.eduservice.entity.EduTeacher;
import com.lxw.eduservice.entity.frontvo.CourseFrontVo;
import com.lxw.eduservice.entity.frontvo.CourseWebVo;
import com.lxw.eduservice.entity.vo.CourseInfoVo;
import com.lxw.eduservice.entity.vo.CoursePublishVo;
import com.lxw.eduservice.entity.vo.CourseQuery;
import com.lxw.eduservice.mapper.EduCourseMapper;
import com.lxw.eduservice.service.EduChapterService;
import com.lxw.eduservice.service.EduCourseDescriptionService;
import com.lxw.eduservice.service.EduCourseService;
import com.lxw.eduservice.service.EduVideoService;
import com.lxw.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lxw
 * @Date: 2022/3/7 18:00
 * @Description:
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {

        //向课程表里添加基本信息
        EduCourse eduCourse = new EduCourse();
        //1、先把courseInfoVo转化为eduCourse再进行添加
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);

        if (insert == 0) {
            //添加失败
            throw new GuliException(20001,"添加课程失败");
        }
        //向课程简介表(CourseDescription)里添加基本信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, eduCourseDescription);
        String id = eduCourse.getId();
        eduCourseDescription.setId(id);
        eduCourseDescriptionService.save(eduCourseDescription);

        return id;
    }
    //根据课程id查询课程信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {

        //1.查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);

        //2.查询简介表
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        System.out.println();

        BeanUtils.copyProperties(eduCourseDescription, courseInfoVo);

        return courseInfoVo;

    }

    //修改课程信息
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表
        EduCourse eduCourse = new EduCourse();
        if (courseInfoVo != null) {
            BeanUtils.copyProperties(courseInfoVo,eduCourse);
        }
        int update = baseMapper.updateById(eduCourse);

        if (update < 0) {
            throw new GuliException(20001, "修改信息失败！！");
        }

        //修改描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        eduCourseDescriptionService.updateById(eduCourseDescription);


    }
    //根据课程id查询课程信息 自定义mapper版
    @Override
    public CoursePublishVo publishCourseInfo(String courseId) {

        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);
        return publishCourseInfo;
    }
    //条件分页查询
    @Override
    public Page<EduCourse> pageQuery(Page<EduCourse> pageCourse, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper();
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }

        //wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageCourse, wrapper);
        return pageCourse;

    }

    //删除课程
    @Override
    public boolean removeCourse(String courseId) {
        //根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);

        //根据课程id删除章节
        eduChapterService.removeChapterByCourseId(courseId);

        //根据课程id删除描述
        eduCourseDescriptionService.removeById(courseId);

        int result = baseMapper.deleteById(courseId);

        if (result >= 1) {
            return true;
        } else {
            return false;
        }

    }

    //根据讲师得到课程信息
    @Override
    public List<EduCourse> getCourseByTeacherId(String id) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<EduCourse> courseList = baseMapper.selectList(wrapper);
        return courseList;
    }

    //课程信息分页条件查询
    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //判断条件值是否为空
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) { //一级分类
            wrapper.eq("subject_parent_id", courseFrontVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())) { //二级分类
            wrapper.eq("subject_id", courseFrontVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) { //根据销量
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) { //根据时间排序
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) { //根据价格
            wrapper.orderByDesc("price");
        }
        baseMapper.selectPage(pageCourse, wrapper);
        List<EduCourse> records = pageCourse.getRecords();
        long current = pageCourse.getCurrent();
        long pages = pageCourse.getPages();
        long size = pageCourse.getSize();
        long total = pageCourse.getTotal();
        //是否有下一页
        boolean hasNext = pageCourse.hasNext();
        //是否有上一页
        boolean hasPrevious = pageCourse.hasPrevious();
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}
