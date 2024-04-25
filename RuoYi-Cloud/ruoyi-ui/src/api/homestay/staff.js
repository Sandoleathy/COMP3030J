import request from '@/utils/request'

// 查询民宿员工表列表
export function listStaff(query) {
  return request({
    url: '/homestay/staff/list',
    method: 'get',
    params: query
  })
}

// 查询民宿员工表详细
export function getStaff(id) {
  return request({
    url: '/homestay/staff/' + id,
    method: 'get'
  })
}

// 新增民宿员工表
export function addStaff(data) {
  return request({
    url: '/homestay/staff',
    method: 'post',
    data: data
  })
}

// 修改民宿员工表
export function updateStaff(data) {
  return request({
    url: '/homestay/staff',
    method: 'put',
    data: data
  })
}

// 删除民宿员工表
export function delStaff(id) {
  return request({
    url: '/homestay/staff/' + id,
    method: 'delete'
  })
}
