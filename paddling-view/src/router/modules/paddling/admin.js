/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const paddlingAdminRouter = {
  path: '/paddlingAdmin',
  component: Layout,
  redirect: 'noRedirect',
  name: '基础管理',
  meta: {
    title: '基础管理',
    icon: 'chart'
  },
  children: [
    {
      path: 'resources',
      component: () => import('@/views/paddling/admin/resource'),
      name: '资源管理',
      meta: { title: '资源管理', noCache: true }
    },
    {
      path: 'role',
      component: () => import('@/views/paddling/admin/role'),
      name: '角色管理',
      meta: { title: '角色管理', noCache: true }
    },
    {
      path: 'user',
      component: () => import('@/views/paddling/admin/user'),
      name: '用户管理',
      meta: { title: '用户管理', noCache: true }
    }
  ]
}

export default paddlingAdminRouter
