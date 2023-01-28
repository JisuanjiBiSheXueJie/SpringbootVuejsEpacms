import Vue from 'vue' 
import App from './App.vue' 
import router from './router'
import store from './store'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloable.css'
import 'animate.css';

import BaiduMap from 'vue-baidu-map'
Vue.use(BaiduMap, {
    ak: 'ahIOatfzIo4gD0L7GvVzP2pGYyuZh2GG'
});
Vue.use(mavonEditor) //使用markdown编辑器插件
Vue.use(ElementUI); //使用ElementUI插件
Vue.config.productionTip = false

import {getCurrentUserByUsername} from './network/user.js'


//钩子函数
router.beforeEach((to, from, next) => {
	window.document.title = to.meta.title === undefined ? '默认标题':to.meta.title
    if (to.meta.requireAuth) {
		let currentUser = store.getters.currentUser
		if (store.getters.token) {
			// 通过用户名查询用户部分信息
			/*getCurrentUserByUsername(store.getters.currentUser).then(res=>{
				if(res.code !== 200){
					next({
						path: '/login',
						query: {redirect: to.fullPath}
					})
				}
				store.commit('SET_CURRENT_USER', res.data)
				next()
			})*/
			next()
		} else {
			next({
				path: '/login',
				query: {redirect: to.fullPath}
			})
		}
	} else {
      next()
    }
  }
)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
