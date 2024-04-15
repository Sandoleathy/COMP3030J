import request from '@/utils/request'

// 查询房间图片列表
export function listRoomImage(query) {
  return request({
    url: '/homestay/roomImage/list',
    method: 'get',
    params: query
  })
}

// 查询房间图片详细
export function getRoomImage(id) {
  return request({
    url: '/homestay/roomImage/' + id,
    method: 'get'
  })
}

// 新增房间图片
export function addRoomImage(data) {
  return request({
    url: '/homestay/roomImage',
    method: 'post',
    data: data
  })
}

// 修改房间图片
export function updateRoomImage(data) {
  return request({
    url: '/homestay/roomImage',
    method: 'put',
    data: data
  })
}

// 删除房间图片
export function delRoomImage(id) {
  return request({
    url: '/homestay/roomImage/' + id,
    method: 'delete'
  })
}
