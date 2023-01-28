<template>
	<div style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex;align-items: center;">
		<!-- 收缩侧边栏logo -->
		<div style="flex: 1; font-size: 20px;">
			<span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
		</div>

		<div style="flex: 25;">
			<!-- 面包屑 -->
			<el-breadcrumb separator=">">
				<el-breadcrumb-item :to="{path: '/'}"><strong>首页</strong></el-breadcrumb-item>
				<el-breadcrumb-item v-if="this.path.meta !== undefined">{{this.path.meta.title}}</el-breadcrumb-item>
			</el-breadcrumb>
		</div>

		<!-- 全屏 -->
		<div style="margin-right: 20px;" >
			<a style="display: flex;justify-content: center;align-items: center;" href="#" title="全屏" @click="fullScreen()">
				<img src="./icons/fullscreen.png" width="32" >
			</a>
		</div>

		<el-dropdown style="width: 80px; cursor: pointer;" @command="handleCommand">
			<!-- 头像 -->
			<div style="display: flex;align-items: center; position: relative;">
				<el-avatar :size="35" shape="square" :src="avatarURL" @error="errorHandler">
					<img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png" />
				</el-avatar>
				<span style="margin-left: 5px;position: absolute;left: 2.5em;top: 0px;">
					<i class="el-icon-caret-bottom"></i>
				</span>
			</div>

			<!-- 头部的用户名显示 -->
			<el-dropdown-menu slot="dropdown">
				<el-dropdown-item command="personInfo">个人信息</el-dropdown-item>
				<el-dropdown-item command="logout">退出</el-dropdown-item>
			</el-dropdown-menu>
		</el-dropdown>
	</div>
</template>

<script>
  import store from "@/store";

	export default {
		name: 'Header',
		data() {
			return {
				collapseBtnClass: 'el-icon-s-fold',
				sideWidth: 200,
				logoShow: true,
				path: '/',
				fullscreen: false,
			}
		},
		methods: {
			//全屏
			fullScreen() {
				let element = document.documentElement;
				if (this.fullscreen) {
			  if (document.exitFullscreen) {
						document.exitFullscreen();
					} else if (document.webkitCancelFullScreen) {
						document.webkitCancelFullScreen();
					} else if (document.mozCancelFullScreen) {
						document.mozCancelFullScreen();
					} else if (document.msExitFullscreen) {
						document.msExitFullscreen();
					}
				} else {
					if (element.requestFullscreen) {
						element.requestFullscreen();
					} else if (element.webkitRequestFullScreen) {
						element.webkitRequestFullScreen();
					} else if (element.mozRequestFullScreen) {
						element.mozRequestFullScreen();
					} else if (element.msRequestFullscreen) {
						// IE11
						element.msRequestFullscreen();
					}
				}
				this.fullscreen = !this.fullscreen;
			},
			errorHandler() {
				return true
			},
			handleCommand(command) {
				if (command === 'logout') {
          store.dispatch('Logout')
          this.$router.replace('/login')
				} else {
					this.$router.push('/personInfo')
				}
			},
			collapse() { //点击收缩按钮触发
				this.isCollapse = !this.isCollapse
				if (this.isCollapse) { //侧边栏收缩
					this.collapseBtnClass = 'el-icon-s-unfold'
					this.$emit('collapse', 64, false) //向Manage通信修改
				} else { //侧边栏展开
					this.collapseBtnClass = ' el-icon-s-fold'
					this.$emit('collapse', 200, true)
				}
			}
		},
    computed:{
      avatarURL(){
        return store.getters.currentUser.avatarUrl;
      }
    },
		watch: { //监听路由
			$route(to, from) {
				this.path = to
			},
      avatarURL(newAvatarUrl){
        this.avatarUrl = newAvatarUrl
      }
		}
	}
</script>
