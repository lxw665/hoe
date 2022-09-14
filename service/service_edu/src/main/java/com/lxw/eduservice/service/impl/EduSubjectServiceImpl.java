package com.lxw.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxw.eduservice.Listener.SubjectExcelListener;
import com.lxw.eduservice.entity.excel.SubjectData;
import com.lxw.eduservice.entity.subject.OneSubject;
import com.lxw.eduservice.entity.subject.TwoSubject;
import com.lxw.eduservice.service.EduSubjectService;
import com.lxw.eduservice.entity.EduSubject;
import com.lxw.eduservice.mapper.EduSubjectMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: lxw
 * @Date: 2022/4/7 10:03
 * @Description:
 */

@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {

        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //查询一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //查询二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建List集合，储存最终数据
        List<OneSubject> finalSubjectList = new ArrayList<>();
        //把EduSubject转换为OneSubject
        for (int i = 0; i < oneSubjectList.size(); i++) {
            OneSubject oneSubject = new OneSubject();
            EduSubject eduSubject = oneSubjectList.get(i);

            BeanUtils.copyProperties(eduSubject, oneSubject);

            finalSubjectList.add(oneSubject);

            //二级分类的集合
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();

            for (int j = 0; j < twoSubjectList.size(); j++) {
                EduSubject twoEduSubject = twoSubjectList.get(j);
                //取出parentId
                String parentId = twoEduSubject.getParentId();
                if (parentId.equals(eduSubject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoEduSubject, twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }

            }
            //把二级分类的集合放入一级分类中
            oneSubject.setChildren(twoFinalSubjectList);

        }

        return finalSubjectList;
    }
}
