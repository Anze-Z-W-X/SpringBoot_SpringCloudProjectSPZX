package com.anze.spzx.utils;

import com.anze.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;
//封装树形菜单数据
public class MenuHelper {
    //递归实现封装过程
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList){
        // 完成封装过程
        //1.创建List<SysMenu>
        List<SysMenu> trees = new ArrayList<>();
        sysMenuList.forEach(sysMenu -> {
            //找到递归的入口，即第一层根菜单parent_id=0
            if(sysMenu.getParentId()==0){
                //根据第一层找到下层
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        });
        return trees;
    }

    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<>());
        sysMenuList.forEach(menu->{
            if(menu.getParentId().longValue()==sysMenu.getId().longValue()){
                //递归
                sysMenu.getChildren().add(findChildren(menu,sysMenuList));
            }
        });
        return sysMenu;
    }
}
