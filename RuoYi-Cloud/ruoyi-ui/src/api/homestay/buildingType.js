import request from '@/utils/request'

// 查询民宿栋类型列表
export function listBuildingType(query) {
  return request({
    url: '/homestay/buildingType/list',
    method: 'get',
    params: query
  })
}

// 查询民宿栋类型详细
export function getBuildingType(id) {
  return request({
    url: '/homestay/buildingType/' + id,
    method: 'get'
  })
}

// 新增民宿栋类型
export function addBuildingType(data) {
  return request({
    url: '/homestay/buildingType',
    method: 'post',
    data: data
  })
}

// 修改民宿栋类型
export function updateBuildingType(data) {
  return request({
    url: '/homestay/buildingType',
    method: 'put',
    data: data
  })
}

// 删除民宿栋类型
export function delBuildingType(id) {
  return request({
    url: '/homestay/buildingType/' + id,
    method: 'delete'
  })
}
