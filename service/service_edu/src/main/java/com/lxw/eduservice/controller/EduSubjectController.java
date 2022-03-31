package com.lxw.eduservice.controller;

import com.lxw.commonutils.R;
import com.lxw.eduservice.entity.subject.OneSubject;
import com.lxw.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lxw
 * @Date: 2022/3/7 10:10
 * @Description:
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;


    /**
     * 添加课程
     * @param file 获取上传上来的文件，把文件内容读取出来
     * @return
     */
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {

        subjectService.saveSubject(file, subjectService);
        return R.ok();
    }

    /**
     * 树形课程分类列表
     * @return
     */
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list", list);
    }
}
