package com.lxw.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxw.eduservice.entity.EduTeacher;
import com.lxw.eduservice.mapper.EduTeacherMapper;
import com.lxw.eduservice.service.EduTeacherService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lxw
 * @Date: 2022/2/17 14:33
 * @Description:
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    //分页查询讲师的方法
    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher) {

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper();
        wrapper.orderByDesc("id");
        baseMapper.selectPage(pageTeacher, wrapper);
        //取出分页的数据放入map集合中
        List<EduTeacher> records = pageTeacher.getRecords();
        long current = pageTeacher.getCurrent();
        long pages = pageTeacher.getPages();
        long size = pageTeacher.getSize();
        long total = pageTeacher.getTotal();
        //是否有下一页
        boolean hasNext = pageTeacher.hasNext();
        //是否有上一页
        boolean hasPrevious = pageTeacher.hasPrevious();
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
}
