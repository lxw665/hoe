package com.lxw.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lxw
 * @Date: 2022/3/2 12:47
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{
    private Integer code; //状态码
    private String msg; //异常信息
}
