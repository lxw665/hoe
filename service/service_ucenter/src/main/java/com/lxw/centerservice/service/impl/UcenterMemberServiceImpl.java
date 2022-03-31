package com.lxw.centerservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxw.centerservice.entity.UcenterMember;
import com.lxw.centerservice.entity.vo.RegisterVo;
import com.lxw.centerservice.mapper.UcenterMemberMapper;
import com.lxw.centerservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxw.commonutils.JwtUtils;
import com.lxw.commonutils.MD5;
import com.lxw.servicebase.exceptionhandler.GuliException;
import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author lxw
 * @since 2022-03-24
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**
     * 登陆方法
     * @param ucenterMember 用户实体
     * @return token
     */
    @Override
    public String login(UcenterMember ucenterMember) {
        String mobilePhone = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();

        //如果手机号或者密码为空
        if (StringUtils.isEmpty(mobilePhone) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001,"登陆失败!!!");
        }

        //根据手机号查出用户信息
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobilePhone);
        UcenterMember member = baseMapper.selectOne(wrapper);

        //如何查不出来这个手机号则登陆失败
        if (member == null) {
            throw new GuliException(20001,"登陆失败!!!");
        }

        //如果密码错误登陆失败
        //把输入的密码先加密在与数据库中的进行比较 加密方式MD5
        if (!MD5.encrypt(password).equals(member.getPassword())) {
            throw new GuliException(20001,"登陆失败!!!");
        }

        //判断是否禁用
        if (member.getIsDisabled()) {
            throw new GuliException(20001,"该账号已被禁用!!!");
        }


        //根据id和名字生成token
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());


        return token;
    }

    /**
     * 注册方法
     * @param registerVo 传入的注册对象
     */
    @Override
    public void register(RegisterVo registerVo) {
        String mobilePhone = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //判断四个字段是否为空
        if (StringUtils.isEmpty(mobilePhone) || StringUtils.isEmpty(nickname)
                || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            throw new GuliException(20001, "注册失败！！");
        }
        //该数据库中是否有该手机号
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper();
        wrapper.eq("mobile", mobilePhone);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new GuliException(20001, "注册失败！！");
        }
        //判断redis是否有验证码
        String redisCode = redisTemplate.opsForValue().get(mobilePhone);
        if (!code.equals(redisCode)) {
            throw new GuliException(20001, "注册失败！！");
        }

        //传入数据库中
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(mobilePhone);
        ucenterMember.setNickname(nickname);
        ucenterMember.setPassword(MD5.encrypt(password));//密码加密
        ucenterMember.setAvatar("https://edu-66656.oss-cn-beijing.aliyuncs.com/2022/03/062f73d75119fd4c579bf102e99e4a79dbfile.png");
        ucenterMember.setIsDisabled(false);

        baseMapper.insert(ucenterMember);

    }
}
