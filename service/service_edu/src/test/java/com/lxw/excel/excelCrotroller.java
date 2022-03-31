package com.lxw.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lxw
 * @Date: 2022/3/6 20:23
 * @Description:
 */
public class excelCrotroller {
    public static void main(String[] args) {
        String fileName = "d://11.xlsx";

        EasyExcel.read(fileName, DemoData.class, new ExcelListenr()).sheet().doRead();

    }

    public static List<DemoData> getList() {
        List<DemoData> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("姜恒" + i + "号");
            list.add(demoData);
        }
        return list;
    }
}
