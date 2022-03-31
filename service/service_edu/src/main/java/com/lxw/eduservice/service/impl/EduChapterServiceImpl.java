package com.lxw.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxw.eduservice.entity.EduChapter;
import com.lxw.eduservice.entity.EduVideo;
import com.lxw.eduservice.entity.chapter.ChapterVo;
import com.lxw.eduservice.entity.chapter.VideoVo;
import com.lxw.eduservice.mapper.EduChapterMapper;
import com.lxw.eduservice.service.EduChapterService;
import com.lxw.eduservice.service.EduVideoService;
import com.lxw.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lxw
 * @Date: 2022/3/7 17:59
 * @Description:
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        //根据课程id查询所有章节
        QueryWrapper<EduChapter> wrapper = new QueryWrapper();
        wrapper.eq("course_id", courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);

        //根据课程ID查询所有小节
        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper();
        wrapper1.eq("course_id", courseId);
        List<EduVideo> eduVideos = eduVideoService.list(wrapper1);
        //遍历章节进行封装
        List<ChapterVo> chapterVos = new ArrayList<>();

        for (int i = 0; i < eduChapters.size(); i++) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapters.get(i), chapterVo);

            List<VideoVo> videoVos = new ArrayList<>();
            for (int j = 0; j < eduVideos.size(); j++) {
                //vo对象
                VideoVo videoVo = new VideoVo();
                //小节对象
                EduVideo eduVideo = new EduVideo();
                eduVideo = eduVideos.get(j);
                if (eduVideo.getChapterId().equals(eduChapters.get(i).getId())) {
                    //把小节变成VO对象
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    //放到Video集合中
                    videoVos.add(videoVo);
                }

            }
            //把封装之后的list集合放到章节对象中
            chapterVo.setChildren(videoVos);
            chapterVos.add(chapterVo);

        }
        //遍历小节封装到章节里面
        return chapterVos;
    }

    //删除章节
    @Override
    public boolean deleteChapter(String chapterId) {
        //查询小节看是否有小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper();
        wrapper.eq("chapter_id", chapterId);
        //得到小节条数
        int count = eduVideoService.count(wrapper);
        if (count > 0) {//不进行删除
            throw new GuliException(20001,"不能删除");
        } else {
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }

    }

    //根据课程id删除章节
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
