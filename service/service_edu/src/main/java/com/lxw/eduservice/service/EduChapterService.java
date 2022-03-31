package com.lxw.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxw.eduservice.entity.EduChapter;
import com.lxw.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * @Author: lxw
 * @Date: 2022/3/7 17:53
 * @Description:
 */
public interface EduChapterService extends IService<EduChapter> {
    /**
     * 根据课程id查询课程大纲
     * @param courseId 课程ID
     * @return 返回一个课程的list集合
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    /**
     * 删除章节（包括小节）
     */
    boolean deleteChapter(String chapterId);

    /**
     * 根据课程id删除章节
     * @param courseId 课程id
     */
    void removeChapterByCourseId(String courseId);
}
