package com.lxw.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxw.eduservice.entity.EduSubject;
import com.lxw.eduservice.entity.EduTeacher;
import com.lxw.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: lxw
 * @Date: 2022/3/7 10:02
 * @Description:
 */
public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 添加课程分类
     * @param file
     */
    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    /**
     * 课程分类列表（树形）
     * @return
     */
    List<OneSubject> getAllOneTwoSubject();
}
