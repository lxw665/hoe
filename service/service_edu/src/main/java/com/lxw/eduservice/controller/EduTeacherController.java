package com.lxw.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxw.commonutils.R;
import com.lxw.eduservice.entity.EduTeacher;
import com.lxw.eduservice.entity.vo.TeacherQuery;
import com.lxw.eduservice.service.EduTeacherService;
import com.lxw.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lxw
 * @Date: 2022/2/17 14:30
 * @Description:
 */

@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService teacherService;

    // 1、查询讲师列表的所有数据
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("item",list);
    }

    /**
     * 根据id逻辑删除
     * @param id 讲师id
     * @return
     */
    @DeleteMapping("deleteTeacherById/{id}")
    public R removeTeacher(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);

        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 根据id进行查询
     * @param id 讲师id
     * @return 成功返回查询结果，失败返回错误信息
     */
    @GetMapping("selectTeacherById/{id}")
    public R selectTeacherById(@PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);

        if (eduTeacher != null) {
            return R.ok().data("item",eduTeacher);
        } else {
            return R.error();
        }
    }

    /**
     * 分页查询
     * @param current 当前页数
     * @param limit 每页最多数据量
     * @return
     */
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListFindTeacher(@PathVariable long current,
                                 @PathVariable long limit) {

        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        teacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list的集合

        return R.ok().data("total",total).data("rows",records);
    }


    /**
     * 分页条件查询
     * @param current 当前页数
     * @param limit 每页最多数据量
     * @param teacherQuery 查询条件对象
     * @return
     */
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,@RequestBody(required = false) TeacherQuery teacherQuery) {

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        //构建条件
        QueryWrapper<EduTeacher> wapper = new QueryWrapper<>();
        //获取数据
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件是否为空
        if (!StringUtils.isEmpty(name)) {
            wapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wapper.ge("gmt-modified", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wapper.le("gmt-modified", end);
        }
        //根据时间排序
        //wapper.orderByDesc("gmt-create");

        teacherService.page(pageTeacher,wapper);

        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list的集合

        return R.ok().data("total",total).data("rows",records);


    }

    /**
     * 讲师添加功能
     * @param eduTeacher 新添加的讲师
     * @return
     */
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);

        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 讲师修改
     * @param eduTeacher
     * @return
     */
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);

        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}
