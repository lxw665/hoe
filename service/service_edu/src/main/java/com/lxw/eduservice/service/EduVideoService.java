package com.lxw.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxw.eduservice.entity.EduVideo;

/**
 * @Author: lxw
 * @Date: 2022/3/7 17:58
 * @Description:
 */
public interface EduVideoService extends IService<EduVideo> {
    /**
     * 根据课程id删除小节
     * @param id
     */
    public void removeVideoByCourseId(String id);
}
