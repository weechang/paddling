import request from '@/utils/request'

export function page(query) {
  return request({
    url: '/paddling/admin/role',
    method: 'get',
    params: query
  })
}

export function detail(id) {
  return request({
    url: '/paddling/admin/role/' + id,
    method: 'get'
  })
}

export function saveOrUpdate(data) {
  return request({
    url: '/paddling/admin/role',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: '/paddling/admin/role/' + id,
    method: 'delete'
  })
}

export function listAll(query) {
  return request({
    url: '/paddling/admin/role/listAll',
    method: 'get',
    params: query
  })
}
