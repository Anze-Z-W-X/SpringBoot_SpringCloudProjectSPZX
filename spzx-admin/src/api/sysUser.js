import request from '@/utils/request'

const base_api = '/admin/system/sysUser'

// 用户列表
export const GetSysUserListByPage = (current , limit , queryDto) => {
    return request({
        url: `${base_api}/findByPage/${current}/${limit}` ,
        method: 'get',
        params: queryDto
    })
}

//用户添加
export const SaveSysUser = (sysUser)=>{
    return request({
        url: `${base_api}/saveSysUser`,
        method: 'post',
        data: sysUser
    })
}

//用户修改
export const UpdateSysUser = (sysUser)=>{
    return request({
        url: `${base_api}/updateSysUser`,
        method: 'put',
        data: sysUser
    })
}

//用户删除
export const DeleteById = (userId)=>{
    return request({
        url: `${base_api}/deleteById/${userId}`,
        method: 'delete'
    })
}

// 给用户分配角色请求
export const DoAssignRoleToUser = (assginRoleVo) => {
    return request({
      url: "/admin/system/sysUser/doAssign",
      method: "post",
      data: assginRoleVo,
    });
  };