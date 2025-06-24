import request from '@/utils/request'

// 查询售货机利润列表
export function listProfile(query) {
  return request({
    url: '/total_profile/profile/list',
    method: 'get',
    params: query
  })
}

// 查询售货机利润详细
export function getProfile(vemId) {
  return request({
    url: '/total_profile/profile/' + vemId,
    method: 'get'
  })
}

// 新增售货机利润
export function addProfile(data) {
  return request({
    url: '/total_profile/profile',
    method: 'post',
    data: data
  })
}

// 修改售货机利润
export function updateProfile(data) {
  return request({
    url: '/total_profile/profile',
    method: 'put',
    data: data
  })
}

// 删除售货机利润
export function delProfile(vemId) {
  return request({
    url: '/total_profile/profile/' + vemId,
    method: 'delete'
  })
}
