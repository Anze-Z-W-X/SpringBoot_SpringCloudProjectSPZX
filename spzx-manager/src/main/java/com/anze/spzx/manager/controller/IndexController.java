package com.anze.spzx.manager.controller;

import com.anze.spzx.manager.service.SysMenuService;
import com.anze.spzx.manager.service.SysUserService;
import com.anze.spzx.manager.service.ValidateCodeService;
import com.anze.spzx.model.dto.system.LoginDto;
import com.anze.spzx.model.entity.system.SysUser;
import com.anze.spzx.model.vo.common.Result;
import com.anze.spzx.model.vo.system.*;
import com.anze.spzx.model.vo.common.ResultCodeEnum;
import com.anze.spzx.model.vo.system.LoginVo;
import com.anze.spzx.model.vo.system.ValidateCodeVo;
import com.anze.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private SysMenuService sysMenuService;

    //查询用户可以操作的菜单
    @GetMapping("/menus")
    public Result menus(){
        List<SysMenuVo> list = sysMenuService.findMenusByUserId();
        return Result.build(list,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "登录接口")
    @PostMapping("/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    //生成图片验证码
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode(){
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo,ResultCodeEnum.SUCCESS);
    }

//    @GetMapping(value = "/getUserInfo")
//    public Result<SysUser> getUserInfo(@RequestHeader(name = "token")String token){
//        SysUser sysUser = sysUserService.getUserInfo(token);
//        return Result.build(sysUser,ResultCodeEnum.SUCCESS);
//    }
    @GetMapping(value = "/getUserInfo")
    public Result<SysUser> getUserInfo() {
        return Result.build(AuthContextUtil.get()  , ResultCodeEnum.SUCCESS) ;
    }


    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(value = "token") String token) {
        sysUserService.logout(token) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
