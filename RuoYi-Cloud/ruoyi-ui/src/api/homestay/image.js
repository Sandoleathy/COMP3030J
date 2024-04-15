import request from '@/utils/request'

// 查询图列表
export function listImage(query) {
  return request({
    url: '/homestay/image/list',
    method: 'get',
    params: query
  })
}

// 查询图详细
export function getImage(id) {
  return request({
    url: '/homestay/image/' + id,
    method: 'get'
  })
}

// 新增图
export function addImage(data) {
  return request({
    url: '/homestay/image',
    method: 'post',
    data: data
  })
}

// 修改图
export function updateImage(data) {
  return request({
    url: '/homestay/image',
    method: 'put',
    data: data
  })
}

// 删除图
export function delImage(id) {
  return request({
    url: '/homestay/image/' + id,
    method: 'delete'
  })
}
