import request from '@/utils/request'

// 查询民宿床类型列表
export function listBedType(query) {
  return request({
    url: '/homestay/bedType/list',
    method: 'get',
    params: query
  })
}

// 查询民宿床类型详细
export function getBedType(id) {
  return request({
    url: '/homestay/bedType/' + id,
    method: 'get'
  })
}

// 新增民宿床类型
export function addBedType(data) {
  return request({
    url: '/homestay/bedType',
    method: 'post',
    data: data
  })
}

// 修改民宿床类型
export function updateBedType(data) {
  return request({
    url: '/homestay/bedType',
    method: 'put',
    data: data
  })
}

// 删除民宿床类型
export function delBedType(id) {
  return request({
    url: '/homestay/bedType/' + id,
    method: 'delete'
  })
}
