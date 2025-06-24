import request from '@/utils/request'

// 查询售货机维修记录列表
export function listRepairRecords(query) {
  return request({
    url: '/repair_records/repairRecords/list',
    method: 'get',
    params: query
  })
}

// 查询售货机维修记录详细
export function getRepairRecords(recordId) {
  return request({
    url: '/repair_records/repairRecords/' + recordId,
    method: 'get'
  })
}

// 新增售货机维修记录
export function addRepairRecords(data) {
  return request({
    url: '/repair_records/repairRecords',
    method: 'post',
    data: data
  })
}

// 修改售货机维修记录
export function updateRepairRecords(data) {
  return request({
    url: '/repair_records/repairRecords',
    method: 'put',
    data: data
  })
}

// 删除售货机维修记录
export function delRepairRecords(recordId) {
  return request({
    url: '/repair_records/repairRecords/' + recordId,
    method: 'delete'
  })
}
