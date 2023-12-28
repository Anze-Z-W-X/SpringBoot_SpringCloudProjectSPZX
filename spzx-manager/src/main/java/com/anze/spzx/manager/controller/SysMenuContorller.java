package com.anze.spzx.manager.controller;

import com.anze.spzx.manager.service.SysMenuService;
import com.anze.spzx.model.entity.system.SysMenu;
import com.anze.spzx.model.vo.common.Result;
import com.anze.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuContorller {
    @Autowired
    private SysMenuService sysMenuService;

    //1.菜单列表
    @GetMapping("/findNodes")
    public Result findNodes(){
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    //2.菜单添加
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu){
        sysMenuService.save(sysMenu);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //3.菜单修改
    @PutMapping("/update")
    public Result update(@RequestBody SysMenu sysMenu){
        sysMenuService.update(sysMenu);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //4.菜单删除
    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable("id") Long id){
        sysMenuService.removeById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
