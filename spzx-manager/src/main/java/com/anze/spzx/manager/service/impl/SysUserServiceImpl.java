package com.anze.spzx.manager.service.impl;

import com.anze.spzx.manager.mapper.SysUserMapper;
import com.anze.spzx.manager.service.SysUserService;
import com.anze.spzx.model.dto.system.LoginDto;
import com.anze.spzx.model.entity.system.SysUser;
import com.anze.spzx.model.vo.system.LoginVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        
        //3.生成token,保存到redis中
        String token = UUID.randomUUID().toString().replace("-","");
        return null;
    }
}
