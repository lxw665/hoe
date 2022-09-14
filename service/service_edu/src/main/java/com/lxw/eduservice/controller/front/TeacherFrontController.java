package com.lxw.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxw.commonutils.R;
import com.lxw.eduservice.entity.EduCourse;
import com.lxw.eduservice.entity.EduTeacher;
import com.lxw.eduservice.service.EduCourseService;
import com.lxw.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Map;

/**
 * @Author: lxw
 * @Date: 2022/3/27 19:35
 * @Description:
 */

@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    //前台讲师分页接口
    @GetMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        Map<String, Object> map = teacherService.getTeacherFrontList(pageTeacher);

        //返回分页中的所有值
        return R.ok().data(map);
    }

    /**
     * 根据讲师id查询讲师信息和课程信息
     * @param teacherId
     * @return
     */
    @GetMapping("getTeacherInfo/{teacherId}")
    public R getTeacherInfo(@PathVariable String teacherId) {
        EduTeacher teacherInfo = teacherService.getById(teacherId);

        List<EduCourse> courseList = courseService.getCourseByTeacherId(teacherId);
        return R.ok().data("teacher",teacherInfo).data("courseList",courseList);
    }
}
