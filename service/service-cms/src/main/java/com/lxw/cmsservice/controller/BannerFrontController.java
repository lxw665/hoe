package com.lxw.cmsservice.controller;

import com.lxw.cmsservice.entity.CrmBanner;
import com.lxw.cmsservice.service.CrmBannerService;
import com.lxw.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: lxw
 * @Date: 2022/3/23 14:17
 * @Description: 前台显示界面 显示banner
 */

@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.seletcAllBanner();
        return R.ok().data("list",list);
    }
}
