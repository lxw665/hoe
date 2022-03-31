package com.lxw.eduservice.service;

import com.lxw.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.Map;

/**
 * @Author: lxw
 * @Date: 2022/2/17 14:32
 * @Description:
 */

public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 分页查询讲师的方法
     * @param pageTeacher
     * @return
     */
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
