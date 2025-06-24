import request from '@/utils/request'

// 查询售货机单商品销售信息列表
export function listSales(query) {
  return request({
    url: '/vemSys/sales/list',
    method: 'get',
    params: query
  })
}

// 查询售货机单商品销售信息详细
export function getSales(id) {
  return request({
    url: '/vemSys/sales/' + id,
    method: 'get'
  })
}

// 新增售货机单商品销售信息
export function addSales(data) {
  return request({
    url: '/vemSys/sales',
    method: 'post',
    data: data
  })
}

// 修改售货机单商品销售信息
export function updateSales(data) {
  return request({
    url: '/vemSys/sales',
    method: 'put',
    data: data
  })
}

// 删除售货机单商品销售信息
export function delSales(id) {
  return request({
    url: '/vemSys/sales/' + id,
    method: 'delete'
  })
}
