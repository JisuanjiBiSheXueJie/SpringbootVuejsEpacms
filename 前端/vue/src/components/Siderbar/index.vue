<template>
	<el-menu 
			default-active="/home"
			style="min-height: 100%;overflow-x: hidden;"
			background-color="#304156" 
			text-color="#ffffff" 
			active-text-color="#82ffed"
			:collapse-transition="isCollapse" 
			:collapse="isCollapse"
			router
	>
		<div style="line-height: 60px; display: flex; justify-content: center; align-items: center; color: #ffffff; font-family: 华文楷体;font-size: 16px;background-color: #73c5c5;">
<!--			<img src="http://localhost:8686/api/file/6c177930ad2d49819d9e62924e2b4aeb.png" width="32px">-->
			<b v-show="logoShow">校园疫情防控管理系统</b>
		</div>
		
		<el-menu-item :index="menu.path" v-for="(menu,index) in menuInfoData" :key="index" >
			<i :class="'el-icon-'+menu.icon"></i>
			<span slot="title">{{menu.description}}</span>
		</el-menu-item>
	</el-menu>
</template>

<script>
	import {getMenuByRid} from '@/network/role.js'
	
	export default {
		name: 'Sidebar',
		props: ['collapseBtnClass','isCollapse','logoShow'],
		data() {
			return {
				menuInfoData: []
			}
		},
    computed: {
      getRid(){
        return this.$store.getters.currentUser.rid
      }
    },
    watch: {
      // 监听
      getRid:{
        immediate: true,
        handler(newVal){
          getMenuByRid(newVal).then(res=>{
            this.menuInfoData = res.data
          })
        }
      }
    }
	}
</script>

<style>
</style>
