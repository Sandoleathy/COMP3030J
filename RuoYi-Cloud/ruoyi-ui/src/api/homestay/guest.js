import request from '@/utils/request'

// 查询民宿客户表列表
export function listGuest(query) {
  return request({
    url: '/homestay/guest/list',
    method: 'get',
    params: query
  })
}

// 查询民宿客户表详细
export function getGuest(id) {
  return request({
    url: '/homestay/guest/' + id,
    method: 'get'
  })
}

// 新增民宿客户表
export function addGuest(data) {
  return request({
    url: '/homestay/guest',
    method: 'post',
    data: data
  })
}

// 修改民宿客户表
export function updateGuest(data) {
  return request({
    url: '/homestay/guest',
    method: 'put',
    data: data
  })
}

// 删除民宿客户表
export function delGuest(id) {
  return request({
    url: '/homestay/guest/' + id,
    method: 'delete'
  })
}
