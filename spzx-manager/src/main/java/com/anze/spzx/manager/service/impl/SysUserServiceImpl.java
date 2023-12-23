package com.anze.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.anze.spzx.common.exception.AnzeException;
import com.anze.spzx.manager.mapper.SysUserMapper;
import com.anze.spzx.manager.service.SysUserService;
import com.anze.spzx.model.dto.system.LoginDto;
import com.anze.spzx.model.dto.system.SysUserDto;
import com.anze.spzx.model.entity.system.SysUser;
import com.anze.spzx.model.vo.common.ResultCodeEnum;
import com.anze.spzx.model.vo.system.LoginVo;
import com.anze.spzx.utils.RedisCache;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor//只会给有final修饰的变量加构造函数,同时依赖注入
public class SysUserServiceImpl implements SysUserService {
    private final SysUserMapper sysUserMapper;

    private final RedisCache redisCache;

    @Override
    public LoginVo login(LoginDto loginDto) {
        //校验验证码是否正确
        String captcha = loginDto.getCaptcha();     // 用户输入的验证码
        String codeKey = loginDto.getCodeKey();     // redis中验证码的数据key
        // 从Redis中获取验证码
        String redisCode = redisCache.getCacheObject("user:login:validatecode:" + codeKey);
        if(StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode , captcha)) {
            throw new AnzeException(ResultCodeEnum.VALIDATECODE_ERROR) ;
        }
        // 验证通过删除redis中的验证码
        redisCache.deleteObject("user:login:validatecode:" + codeKey) ;
        //1.根据用户名查询用户
        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUserName());
        if(sysUser == null) {
            throw new AnzeException(ResultCodeEnum.LOGIN_ERROR);
//            throw new RuntimeException("用户名或者密码错误");
        }
        //2.验证密码是否正确
        String inputPassword = loginDto.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if(!md5InputPassword.equals(sysUser.getPassword())) {
            throw new AnzeException(ResultCodeEnum.LOGIN_ERROR);
//            throw new RuntimeException("用户名或者密码错误");
        }
        //3.生成token,保存到redis中
        String token = UUID.randomUUID().toString().replace("-","");
        redisCache.setCacheObject("user:login:"+token, JSON.toJSONString(sysUser),30, TimeUnit.MINUTES);
        //4.构建响应结果对象
        LoginVo loginVo = new LoginVo() ;
        loginVo.setToken(token);
        loginVo.setRefresh_token("");
        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String userJson = redisCache.getCacheObject("user:login:"+token);
        return JSON.parseObject(userJson,SysUser.class);
    }

    @Override
    public void logout(String token) {
        redisCache.deleteObject("user:login:" + token);
    }

    @Override
    public PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> list = sysUserMapper.findByPage(sysUserDto);
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void saveSysUser(SysUser sysUser) {
        //1.判断用户名不能重复
        String userName = sysUser.getUserName();
        SysUser dbSysUser = sysUserMapper.selectByUserName(userName);
        if(dbSysUser!=null){
            throw new AnzeException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //2.输入密码加密
        String password = sysUser.getPassword();
        String md5_password = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(md5_password);

        //3.设置status值,1可用，0不可用
        sysUser.setStatus(1);
        sysUserMapper.save(sysUser);
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.update(sysUser);
    }

    @Override
    public void deleteById(Long userId) {
        sysUserMapper.delete(userId);
    }
}
