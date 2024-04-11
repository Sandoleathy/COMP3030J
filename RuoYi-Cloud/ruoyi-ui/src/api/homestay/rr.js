import request from '@/utils/request'

// 查询订单客户联合列表
export function listRr(query) {
  return request({
    url: '/homestay/rr/list',
    method: 'get',
    params: query
  })
}

// 查询订单客户联合详细
export function getRr(id) {
  return request({
    url: '/homestay/rr/' + id,
    method: 'get'
  })
}

// 新增订单客户联合
export function addRr(data) {
  return request({
    url: '/homestay/rr',
    method: 'post',
    data: data
  })
}

// 修改订单客户联合
export function updateRr(data) {
  return request({
    url: '/homestay/rr',
    method: 'put',
    data: data
  })
}

// 删除订单客户联合
export function delRr(id) {
  return request({
    url: '/homestay/rr/' + id,
    method: 'delete'
  })
}
