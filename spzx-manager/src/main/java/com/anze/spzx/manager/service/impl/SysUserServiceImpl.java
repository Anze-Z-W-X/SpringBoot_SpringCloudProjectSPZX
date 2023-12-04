package com.anze.spzx.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.anze.spzx.manager.mapper.SysUserMapper;
import com.anze.spzx.manager.service.SysUserService;
import com.anze.spzx.model.dto.system.LoginDto;
import com.anze.spzx.model.entity.system.SysUser;
import com.anze.spzx.model.vo.system.LoginVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor//只会给有final修饰的变量加构造函数,同时依赖注入
public class SysUserServiceImpl implements SysUserService {
    private final SysUserMapper sysUserMapper;

    private final RedisTemplate<String ,String> redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {
        //1.根据用户名查询用户
        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUserName());
        if(sysUser == null) {
            throw new RuntimeException("用户名或者密码错误");
        }
        //2.验证密码是否正确
        String inputPassword = loginDto.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if(!md5InputPassword.equals(sysUser.getPassword())) {
            throw new RuntimeException("用户名或者密码错误") ;
        }
        //3.生成token,保存到redis中
        String token = UUID.randomUUID().toString().replace("-","");
        redisTemplate.opsForValue().set("user:login:"+token, JSON.toJSONString(sysUser),30, TimeUnit.MINUTES);

        //4.构建响应结果对象
        LoginVo loginVo = new LoginVo() ;
        loginVo.setToken(token);
        loginVo.setRefresh_token("");
        return loginVo;
    }
}
