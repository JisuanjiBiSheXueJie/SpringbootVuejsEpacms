import Vue from 'vue'
import Vuex from 'vuex'
import {
	login
} from '@/network/login.js'
import {getCurrentUserByUsername} from "@/network/user";

Vue.use(Vuex)

export default new Vuex.Store({
	state: {
		//当前用户部分信息: id,username,角色id
		currentUser: localStorage.getItem('currentUser') ? JSON.parse(localStorage.getItem('currentUser')) : '',
		token: localStorage.getItem('token') || ''
	},
	getters: {
		token: state => state.token,
		currentUser: state => state.currentUser
	},
	mutations: {
		SET_TOKEN: (state, token) => {
			state.token = token
		},
		SET_CURRENT_USER: (state, currentUser) => {
			state.currentUser = JSON.parse(currentUser)
		}
	},
	actions: {
		Login({commit}, loginForm){
			return new Promise((resolve, reject) => {
				// 请求后台登录接口
				login(loginForm).then(res => {
					if (res.code !== 200) {
						//登录失败
						return resolve(res)
					}
					// 拼接token字符串信息
					const tokenStr = `${res.data.tokenHead} ${res.data.token}`
					// 将token字符串存入localStorage
					localStorage.setItem('token', tokenStr)
					commit('SET_TOKEN', tokenStr)

					// 通过用户名获取用户信息
					getCurrentUserByUsername(loginForm.username).then(res => {
						localStorage.setItem('currentUser', JSON.stringify(res))
						commit('SET_CURRENT_USER', JSON.stringify(res))
					})
					resolve(res)
				}).catch(error => {
					reject(error)
				})
			})
		},
		Logout({commit}){
			commit('SET_TOKEN', '')
			commit('SET_CURRENT_USER', JSON.stringify(''))
			window.localStorage.removeItem('token')
			window.localStorage.removeItem('currentUser')
		}
	},
	modules: {}
})
