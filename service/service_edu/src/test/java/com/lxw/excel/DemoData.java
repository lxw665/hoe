package com.lxw.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: lxw
 * @Date: 2022/3/6 20:12
 * @Description:
 */
@Data
public class DemoData {
    @ExcelProperty(value = "编号",index = 0)
    private int sno;
    @ExcelProperty(value = "姓名",index = 1)
    private String sname;


}
