<template>
	<div>
		<el-row>
			<el-input v-model="article.title" style="margin: 10px 0;font-size: 18px;" placeholder="请输入标题"/>
		</el-row>
		<el-row style="height: calc(100vh - 140px);">
			<mavon-editor v-model="article.detailMd"
				:ishljs="false" 
				style="height: 100%;" 
				ref=md
				@save="saveDetail" fontSize="16px">
			</mavon-editor>
		</el-row>
	</div>
</template>

<script>
	import {
		getDetailIdByIdAndType,
		updateDetail
	} from "@/network/notice.js"
	
	
	export default {
		name: 'Editor',
		data() {
			return {
				article: {
					id: '',
					uid: '',
					title: '',
					detailMd: '',
					detailHtml: ''
				}
			}
		},
		methods: {
			// value 是 md，render 是 html
			saveDetail(value, render) {
				this.$confirm('是否保存并发布文章?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					let id = this.$route.query.id;
					updateDetail(id, this.article.title, value, render).then(res => {
						if (res.code !== 200) {
							return this.$message.error('保存失败');
						}
            this.$message.success('保存成功')
          })
				})
			}
		},
		created() {
			let id = this.$route.query.id;
			//根据id查询通知详情
			getDetailIdByIdAndType(id, 'md').then(res => {
					this.article = res.data
			})
		}
	}
</script>