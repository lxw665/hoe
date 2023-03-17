package com.lxw.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: lxw
 * @Date: 2022/3/1 21:39
 * @Description:
 */
@Data
public class TeacherQuery {

    @ApiModelProperty(value = "教师名称，模糊查询")
    public String name;

    @ApiModelProperty(value = "头衔 1 高级讲师 2 普通讲师")
    public Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    public String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    public String end;
}
