import request from '@/utils/request'

export function toPage() {
  return request({
    url: '/paddling/admin/user/toPage',
    method: 'get'
  })
}

export function page(query) {
  return request({
    url: '/paddling/admin/user',
    method: 'get',
    params: query
  })
}

export function detail(id) {
  return request({
    url: '/paddling/admin/user/' + id,
    method: 'get'
  })
}

export function saveOrUpdate(data) {
  return request({
    url: '/paddling/admin/user',
    method: 'post',
    data
  })
}

export function resetPwd(userId) {
  return request({
    url: '/paddling/admin/user/resetPwd/' + userId,
    method: 'post'
  })
}

export function del(id) {
  return request({
    url: '/paddling/admin/user/' + id,
    method: 'delete'
  })
}
