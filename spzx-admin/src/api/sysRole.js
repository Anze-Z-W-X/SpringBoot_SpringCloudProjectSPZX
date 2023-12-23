import request from '@/utils/request'

// 分页查询角色数据
export const GetSysRoleListByPage = (pageNum , pageSize , queryDto) => {
    return request({
        url: '/admin/system/sysRole/findByPage/' + pageNum + "/" + pageSize,
        method: 'post',
        data: queryDto
    })
}

//角色添加
export const SaveSysRole = (sysRole)=>{
    return request({
        url: '/admin/system/sysRole/saveSysRole',
        method: 'post',
        data: sysRole
    })
}

//角色修改
export const UpdateSysRole = (sysRole)=>{
    return request({
        url: '/admin/system/sysRole/updateSysRole',
        method: 'put',
        data: sysRole
    })
}

//角色删除
export const DeleteById = (roleId)=>{
    return request({
        url: `/admin/system/sysRole/deleteById/${roleId}`,
        method: 'delete'
    })
}