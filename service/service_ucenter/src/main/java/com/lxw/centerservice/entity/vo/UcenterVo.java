package com.lxw.centerservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: lxw
 * @Date: 2022/3/31 20:43
 * @Description:
 */
@Data
public class UcenterVo {
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "会员id")
    private String id;
}
