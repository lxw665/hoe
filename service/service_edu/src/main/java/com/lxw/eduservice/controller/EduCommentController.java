package com.lxw.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxw.commonutils.JwtUtils;
import com.lxw.commonutils.R;
import com.lxw.eduservice.client.UcenterClient;
import com.lxw.eduservice.entity.EduComment;
import com.lxw.eduservice.entity.vo.UcenterVo;
import com.lxw.eduservice.service.EduCommentService;
import com.lxw.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author lxw
 * @since 2022-03-31
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class EduCommentController {
    @Autowired
    private EduCommentService commentService;

    @Autowired
    private UcenterClient ucenterClient;

    @ApiOperation(value = "评论分页列表")
    @GetMapping("getCommentPage/{page}/{limit}")
    public R getCommentPage(@PathVariable long page, @PathVariable long limit,String courseId) {
        Page<EduComment> commentPage = new Page<>(page, limit);
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseId)) {
            wrapper.eq("courseId",courseId);
        }
        //按照最新排序
        wrapper.orderByDesc("gmt_create");
        //数据会被封装到commentPage中
        commentService.page(commentPage, wrapper);


        List<EduComment> commentList = commentPage.getRecords();
        long current = commentPage.getCurrent();//当前页
        long size = commentPage.getSize();//一页记录数
        long total = commentPage.getTotal();//总记录数
        long pages = commentPage.getPages();//总页数
        boolean hasPrevious = commentPage.hasPrevious();//是否有上页
        boolean hasNext = commentPage.hasNext();//是否有下页

        HashMap<String, Object> map = new HashMap<>();
        map.put("current",current);
        map.put("size",size);
        map.put("total",total);
        map.put("pages",pages);
        map.put("hasPrevious",hasPrevious);
        map.put("hasNext",hasNext);
        map.put("list",commentList);

        return R.ok().data(map);

    }

    //添加评论
    @PostMapping("addComment")
    public R addComment(HttpServletRequest request, @RequestBody EduComment eduComment) {
        String memberId  = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println(memberId);
        if (StringUtils.isEmpty(memberId)) {
            throw new GuliException(20001,"请先登录");
        }
        eduComment.setMemberId(memberId);
        UcenterVo ucenterVo = ucenterClient.getInfoUc(memberId);
        eduComment.setAvatar(ucenterVo.getAvatar());
        eduComment.setNickname(ucenterVo.getNickname());

        //保存
        commentService.save(eduComment);
        return R.ok();
    }
}

