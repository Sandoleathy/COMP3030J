import request from '@/utils/request'

// 查询房间预定列表
export function listReservation(query) {
  return request({
    url: '/homestay/reservation/list',
    method: 'get',
    params: query
  })
}

// 查询房间预定详细
export function getReservation(id) {
  return request({
    url: '/homestay/reservation/' + id,
    method: 'get'
  })
}

// 新增房间预定
export function addReservation(data) {
  return request({
    url: '/homestay/reservation',
    method: 'post',
    data: data
  })
}

// 修改房间预定
export function updateReservation(data) {
  return request({
    url: '/homestay/reservation',
    method: 'put',
    data: data
  })
}

// 删除房间预定
export function delReservation(id) {
  return request({
    url: '/homestay/reservation/' + id,
    method: 'delete'
  })
}
