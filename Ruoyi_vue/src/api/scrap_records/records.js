import request from '@/utils/request'

// 查询售货机报废列表
export function listRecords(query) {
  return request({
    url: '/scrap_records/records/list',
    method: 'get',
    params: query
  })
}

// 查询售货机报废详细
export function getRecords(scrapId) {
  return request({
    url: '/scrap_records/records/' + scrapId,
    method: 'get'
  })
}

// 新增售货机报废
export function addRecords(data) {
  return request({
    url: '/scrap_records/records',
    method: 'post',
    data: data
  })
}

// 修改售货机报废
export function updateRecords(data) {
  return request({
    url: '/scrap_records/records',
    method: 'put',
    data: data
  })
}

// 删除售货机报废
export function delRecords(scrapId) {
  return request({
    url: '/scrap_records/records/' + scrapId,
    method: 'delete'
  })
}
