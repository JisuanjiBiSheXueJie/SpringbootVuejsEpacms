<template>
	<el-container style="min-height: 100vh;">
		<!-- 侧边栏 -->
		<el-aside :width="sideWidth+'px'">
			<Aside :isCollapse="isCollapse" :logoShow="logoShow"> </Aside>
		</el-aside>

		<!-- 主体 -->
		<el-container>
			<!-- 头部 -->
			<el-header>
				<MyHeader ref="myheader" :collapseBtnClass="collapseBtnClass" @collapse="collapse"></MyHeader>
			</el-header>

			<!-- 核心展示区 -->
			<el-main style="padding: 15px;">
				<!-- 当前页面的子路由会在router-view中展示 -->
					<router-view class="animate__animated animate__fadeIn">
					</router-view>
			</el-main>

		</el-container>
	</el-container>
</template>

<script>
	import Aside from '@/components/Siderbar'
	import MyHeader from '@/components/Header'
	import {
		isClock
	} from '@/network/health'

	export default {
		name: 'Home',
		components: { Aside, MyHeader},
		data() {
			return {
				rid: this.$store.getters.currentUser.rid,
				id: this.$store.getters.currentUser.id,
				uid: '',
				collapseBtnClass: 'el-icon-s-fold',
				sideWidth: 200,
				logoShow: true,
				dialogFormVisible: false, //是否显示对话框
				multipleSelection: [], //多选框选中对象
				isCollapse: false, //侧边栏收缩
			}
		},
		methods: {
			collapse(sideWidth, logoShow) {
				this.sideWidth = sideWidth
				this.logoShow = logoShow
				this.isCollapse = !this.isCollapse
			}
		},
		created() {
			//如果是学生则检查今日是否打卡
			if (this.rid === 3 || this.rid === 4) {
				isClock(this.id).then(res => {
					if (res.code === 200) {
						//如果今日已经打卡，则存入vuex中
						this.$notify({
							title: '完成',
							type: 'success',
							duration: 3000,
							offset: 0,
							message: '今日已打卡',
							position: 'top-left'
						});
					} else {
						this.$notify({
							title: '警告',
							type: 'warning',
							duration: 5000,
							offset: 0,
							message: '今日还未打卡',
							position: 'top-left'
						});
					}
				})
			}
		}
	};
</script>

<style>
	.el-aside {
		min-height: 100%;
		text-align: left;
		box-shadow: 2px 0 6px rgb(0, 21, 41, 35%);
	}

	.animate__animated{
		--animate-duration: 1.5s;
	}
</style>
