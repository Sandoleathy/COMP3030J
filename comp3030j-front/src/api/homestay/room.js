import request from '@/utils/request'

// 查询民宿房间列表
export function listRoom(query) {
  return request({
    url: '/homestay/room/list',
    method: 'get',
    params: query
  })
}

// 查询民宿房间详细
export function getRoom(id) {
  return request({
    url: '/homestay/room/' + id,
    method: 'get'
  })
}

// 新增民宿房间
export function addRoom(data) {
  return request({
    url: '/homestay/room',
    method: 'post',
    data: data
  })
}

// 修改民宿房间
export function updateRoom(data) {
  return request({
    url: '/homestay/room',
    method: 'put',
    data: data
  })
}

// 删除民宿房间
export function delRoom(id) {
  return request({
    url: '/homestay/room/' + id,
    method: 'delete'
  })
}
