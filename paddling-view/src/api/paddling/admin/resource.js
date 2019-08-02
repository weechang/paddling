import request from '@/utils/request'

export function toPage() {
  return request({
    url: '/paddling/admin/resource/toPage',
    method: 'get'
  })
}

export function page(query) {
  return request({
    url: '/paddling/admin/resource',
    method: 'get',
    params: query
  })
}

export function detail(id) {
  return request({
    url: '/paddling/admin/resource/' + id,
    method: 'get'
  })
}

export function saveOrUpdate(data) {
  return request({
    url: '/paddling/admin/resource',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: '/paddling/admin/resource/' + id,
    method: 'delete'
  })
}
