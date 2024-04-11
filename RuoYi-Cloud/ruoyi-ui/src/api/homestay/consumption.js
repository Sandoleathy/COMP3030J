import request from '@/utils/request'

// 查询民宿消费列表
export function listConsumption(query) {
  return request({
    url: '/homestay/consumption/list',
    method: 'get',
    params: query
  })
}

// 查询民宿消费详细
export function getConsumption(id) {
  return request({
    url: '/homestay/consumption/' + id,
    method: 'get'
  })
}

// 新增民宿消费
export function addConsumption(data) {
  return request({
    url: '/homestay/consumption',
    method: 'post',
    data: data
  })
}

// 修改民宿消费
export function updateConsumption(data) {
  return request({
    url: '/homestay/consumption',
    method: 'put',
    data: data
  })
}

// 删除民宿消费
export function delConsumption(id) {
  return request({
    url: '/homestay/consumption/' + id,
    method: 'delete'
  })
}
