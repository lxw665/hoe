package com.lxw.centerservice.service;

import com.lxw.centerservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lxw.centerservice.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author lxw
 * @since 2022-03-24
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     * 登陆的方法实现
     * @param ucenterMember 用户实体
     * @return 返回token
     */
    String login(UcenterMember ucenterMember);

    /**
     * 注册方法的实现
     * @param registerVo
     */
    void register(RegisterVo registerVo);
}
