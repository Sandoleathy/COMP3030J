import request from '@/utils/request'

// 查询民宿预订列表
export function listReservation(query) {
  return request({
    url: '/homestay/reservation/list',
    method: 'get',
    params: query
  })
}

// 查询民宿预订详细
export function getReservation(id) {
  return request({
    url: '/homestay/reservation/' + id,
    method: 'get'
  })
}

// 新增民宿预订
export function addReservation(data) {
  return request({
    url: '/homestay/reservation',
    method: 'post',
    data: data
  })
}

// 修改民宿预订
export function updateReservation(data) {
  return request({
    url: '/homestay/reservation',
    method: 'put',
    data: data
  })
}

// 删除民宿预订
export function delReservation(id) {
  return request({
    url: '/homestay/reservation/' + id,
    method: 'delete'
  })
}
