import request from '@/utils/request'

// 查询民宿财务表列表
export function listFinance(query) {
  return request({
    url: '/homestay/finance/list',
    method: 'get',
    params: query
  })
}

// 查询民宿财务表详细
export function getFinance(id) {
  return request({
    url: '/homestay/finance/' + id,
    method: 'get'
  })
}

// 新增民宿财务表
export function addFinance(data) {
  return request({
    url: '/homestay/finance',
    method: 'post',
    data: data
  })
}

// 修改民宿财务表
export function updateFinance(data) {
  return request({
    url: '/homestay/finance',
    method: 'put',
    data: data
  })
}

// 删除民宿财务表
export function delFinance(id) {
  return request({
    url: '/homestay/finance/' + id,
    method: 'delete'
  })
}
