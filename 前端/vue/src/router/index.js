import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home'
Vue.use(VueRouter)

const routes = [
	{
		path: '/',
		name: '首页',
		component: Home,
		redirect: '/home',
		meta: {
			title: '校园疫情防控管理系统',
			requireAuth: true
		},
		children: [
			{
				path: 'home',
				name: 'Home',
				component: () => import('@/views/Home/common/Main'),
				meta: {
					title: '我的主页',
					requireAuth: true
				}
			},
			{
				path: 'health',
				name: 'Health',
				component: () => import('@/views/Home/common/Health'),
				meta: {
					title: '健康打卡',
					requireAuth: true
				}
			},
			{
				path: 'leave',
				name: 'Leave',
				component: () => import('@/views/Home/common/Leave/Leave.vue'),
				meta: {
					title: '离校管理',
					requireAuth: true
				}
			},
			{
				path: 'epidemic',
				name: 'Epidemic',
				component: () => import('@/views/Home/common/EpidemicInfo'),
				meta: {
					title: '全国疫情',
					requireAuth: true
				}
			},
			{
				path: 'personInfo',
				name: 'PersonInfo',
				component: () => import('@/views/Home/common/PersonInfo'),
				meta: {
					title: '个人信息',
					requireAuth: true
				}
			},
			{
				path: 'noticeDetail',
				name: 'NoticeDetail',
				component: () => import('@/views/Home/common/NoticeContent'),
				meta: {
					title: '通知详情',
					requireAuth: true
				}
			},
			//*************************admin******************************
			{
				path: 'user',
				name: 'User',
				component: () => import('@/views/Home/admin/User'),
				meta: {
					title: '用户管理',
					requireAuth: true
				}
			},
			{
				path: 'editor',
				name: '/Editor',
				component: () => import('@/views/Home/admin/Editor'),
				meta: {
					title: '通知编辑',
					requireAuth: true
				}
			},
			{
				path: 'news',
				name: 'News',
				component: () => import('@/views/Home/admin/News'),
				meta: {
					title: '新闻管理',
					requireAuth: true
				}
			},
			{
				path: 'notice',
				name: 'Notice',
				component: () => import('@/views/Home/admin/Notice'),
				meta: {
					title: '通知管理',
					requireAuth: true
				}
			},
			{
				path: 'permission',
				name: 'Permission',
				component: () => import('@/views/Home/admin/Permission'),
				meta: {
					title: '系统权限',
					requireAuth: true
				}
			},
			{
				path: 'statistics',
				name: 'Statistics',
				component: () => import('@/views/Home/admin/Statistics'),
				meta: {
					title: '统计分析',
					requireAuth: true
				}
			},
			{
				path: 'file',
				name: 'File',
				component: () => import('@/views/Home/admin/File'),
				meta: {
					title: '文件管理',
					requireAuth: true
				}
			}
		]
	},
	{
		path: '/login',
		name: 'Login',
		component: () => import('../views/Login'),
		meta: {
			title: '登录'
		}
	},
	{
		path: '/register',
		name: 'Register',
		component: () => import('../views/Register'),
		meta: {
			title: '注册'
		}
	}
]

const router = new VueRouter({
	// mode: 'history',
	routes
})

export default router
