package com.lxw.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxw.eduservice.entity.EduVideo;
import com.lxw.eduservice.mapper.EduVideoMapper;
import com.lxw.eduservice.service.EduVideoService;
import org.springframework.stereotype.Service;

/**
 * @Author: lxw
 * @Date: 2022/3/7 18:01
 * @Description:
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    //根据课程id删除小节
    @Override
    public void removeVideoByCourseId(String id) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }
}
