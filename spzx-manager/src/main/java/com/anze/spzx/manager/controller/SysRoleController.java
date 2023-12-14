package com.anze.spzx.manager.controller;

import com.anze.spzx.manager.service.SysRoleService;
import com.anze.spzx.model.dto.system.SysRoleDto;
import com.anze.spzx.model.entity.system.SysRole;
import com.anze.spzx.model.vo.common.Result;
import com.anze.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService ;

    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysRole>> findByPage(@PathVariable(value = "pageNum") Integer pageNum ,
                                                @PathVariable(value = "pageSize") Integer pageSize,
                                                @RequestBody SysRoleDto sysRoleDto) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto , pageNum , pageSize) ;
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

}