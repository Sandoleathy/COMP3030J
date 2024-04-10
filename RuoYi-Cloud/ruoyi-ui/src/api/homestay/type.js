import request from '@/utils/request'

// 查询民宿床类型列表
export function listType(query) {
  return request({
    url: '/homestay/type/list',
    method: 'get',
    params: query
  })
}

// 查询民宿床类型详细
export function getType(id) {
  return request({
    url: '/homestay/type/' + id,
    method: 'get'
  })
}

// 新增民宿床类型
export function addType(data) {
  return request({
    url: '/homestay/type',
    method: 'post',
    data: data
  })
}

// 修改民宿床类型
export function updateType(data) {
  return request({
    url: '/homestay/type',
    method: 'put',
    data: data
  })
}

// 删除民宿床类型
export function delType(id) {
  return request({
    url: '/homestay/type/' + id,
    method: 'delete'
  })
}
