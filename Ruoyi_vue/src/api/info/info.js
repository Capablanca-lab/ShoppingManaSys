import request from '@/utils/request'

// 查询售货机管理列表
export function listInfo(query) {
  return request({
    url: '/info/info/list',
    method: 'get',
    params: query
  })
}
  
// 查询售货机管理详细
export function getInfo(id) {
  return request({
    url: '/info/info/' + id,
    method: 'get'
  })
}

// 新增售货机管理
export function addInfo(data) {
  return request({
    url: '/info/info',
    method: 'post',
    data: data
  })
}

// 修改售货机管理
export function updateInfo(data) {
  return request({
    url: '/info/info',
    method: 'put',
    data: data
  })
}

// 删除售货机管理
export function delInfo(id) {
  return request({
    url: '/info/info/' + id,
    method: 'delete'
  })
}
