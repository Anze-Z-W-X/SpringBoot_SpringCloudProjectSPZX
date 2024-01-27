package com.anze.spzx.model.vo.system;

import lombok.Data;
import java.util.List;

@Data
public class SysMenuVo {

    private String title;
    private String name;
    private List<SysMenuVo> children;

}