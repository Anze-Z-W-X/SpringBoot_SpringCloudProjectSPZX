package com.anze.spzx.manager.controller;

import com.anze.spzx.common.log.annotation.Log;
import com.anze.spzx.manager.service.SysRoleService;
import com.anze.spzx.model.dto.system.SysRoleDto;
import com.anze.spzx.model.entity.system.SysRole;
import com.anze.spzx.model.vo.common.Result;
import com.anze.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService ;

    //查询所有角色
    @GetMapping("/findAllRoles/{userId}")
    public Result findAllRoles(@PathVariable("userId")Long userId){
        Map<String ,Object> map = sysRoleService.findAll(userId);
        return Result.build(map,ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysRole>> findByPage(@PathVariable(value = "pageNum") Integer pageNum ,
                                                @PathVariable(value = "pageSize") Integer pageSize,
                                                @RequestBody SysRoleDto sysRoleDto) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto , pageNum , pageSize) ;
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    //角色添加方法
    @Log(title = "角色添加",businessType = 1) //添加Log注解，设置属性
    @PostMapping("/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole){
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole){
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/deleteById/{roleId}")
    public Result deleteById(@PathVariable("roleId")Long roleId){
        sysRoleService.delById(roleId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

}