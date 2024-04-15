import request from '@/utils/request'

// 查询床图片列表
export function listBedImage(query) {
  return request({
    url: '/homestay/bedImage/list',
    method: 'get',
    params: query
  })
}

// 查询床图片详细
export function getBedImage(id) {
  return request({
    url: '/homestay/bedImage/' + id,
    method: 'get'
  })
}

// 新增床图片
export function addBedImage(data) {
  return request({
    url: '/homestay/bedImage',
    method: 'post',
    data: data
  })
}

// 修改床图片
export function updateBedImage(data) {
  return request({
    url: '/homestay/bedImage',
    method: 'put',
    data: data
  })
}

// 删除床图片
export function delBedImage(id) {
  return request({
    url: '/homestay/bedImage/' + id,
    method: 'delete'
  })
}
