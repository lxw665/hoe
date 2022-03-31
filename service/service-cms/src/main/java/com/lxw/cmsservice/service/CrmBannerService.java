package com.lxw.cmsservice.service;

import com.lxw.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author lxw
 * @since 2022-03-13
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 查询所有banner
     * @return 返回一个banner集合
     */
    List<CrmBanner> seletcAllBanner();
}
