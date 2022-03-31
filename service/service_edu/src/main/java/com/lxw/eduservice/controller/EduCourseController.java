package com.lxw.eduservice.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxw.commonutils.R;
import com.lxw.eduservice.entity.CourseStatus;
import com.lxw.eduservice.entity.EduCourse;
import com.lxw.eduservice.entity.EduTeacher;
import com.lxw.eduservice.entity.EduVideo;
import com.lxw.eduservice.entity.vo.CourseInfoVo;
import com.lxw.eduservice.entity.vo.CoursePublishVo;
import com.lxw.eduservice.entity.vo.CourseQuery;
import com.lxw.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @Author: lxw
 * @Date: 2022/3/7 18:03
 * @Description:
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin

public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;
    /**
     * 搜索课程列表
     * @return
     */
    @GetMapping("getCourseList")
    public R getCourseList() {
        List<EduCourse> list = eduCourseService.list(null);
        return R.ok().data("list", list);
    }

    /**
     * 分页查询
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("pageCourse/{current}/{limit}")
    public R pageCourse(@PathVariable long current, @PathVariable long limit) {
        Page<EduCourse> coursePage = new Page<>(current,limit);
        eduCourseService.page(coursePage,null);
        long total = coursePage.getTotal(); //总记录数
        List<EduCourse> courseList = coursePage.getRecords();
        return R.ok().data("total",total).data("list",courseList);

    }



    /**
     * 条件分页
     * @param current 当前页数
     * @param limit 每页最大限制
     * @param courseQuery 搜索条件
     * @return
     */
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageListFindCourse(@PathVariable long current,
                                 @PathVariable long limit,
                                @RequestBody(required = false) CourseQuery courseQuery) {

        Page<EduCourse> pageCourse = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //获取数据
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }
        //根据时间排序
        //wapper.orderByDesc("gmt-create");

        eduCourseService.page(pageCourse,wrapper);

        long total = pageCourse.getTotal(); //总记录数
        List<EduCourse> records = pageCourse.getRecords(); //数据list的集合

        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * 添加课程基本信息的方法
     * @param courseInfoVo
     * @return
     */
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    /**
     * 根据课程id查询课程信息
     * @param courseId 课程id
     * @return 返回一个CourseInfoVo对象传到前端
     */
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        //System.out.println(courseId);
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    /**
     * 修改课程信息
     * @param courseInfoVo 课程信息表单对象
     * @return
     */
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    /**
     * 根据课程id查询课程信息 自定义mapper版
     * @param courseId
     * @return
     */
    @GetMapping("getPublishCourseInfo/{courseId}")
    public R getPublishCourseInfo(@PathVariable String courseId) {
        CoursePublishVo coursePublishVo = new CoursePublishVo();
        coursePublishVo = eduCourseService.publishCourseInfo(courseId);
        return R.ok().data("publishCourse",coursePublishVo);
    }

    //课程最终发布
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus(CourseStatus.COURSE_NORMAL);
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    //删除课程
    @DeleteMapping("removeCourseById/{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        boolean flag = eduCourseService.removeCourse(courseId);

        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}
