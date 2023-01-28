<template>
	<div>
		<!-- 表格 -->
		<el-table :data="tableData" border stripe size="mini" :header-cell-style="{background:'#a3bbdb',color:'#555'}">
			<!-- 多选框 -->
			<el-table-column prop="id" label="ID" ></el-table-column>
			<el-table-column prop="name" label="权限英文名"></el-table-column>
			<el-table-column prop="description" label="角色名称"></el-table-column>
			<el-table-column prop="comment" label="备注"></el-table-column>
		</el-table>


	</div>
</template>

<script>
	import {
		getRoles
	} from '@/network/role.js'
	
	export default {
		name: 'Role',
		data() {
			return {
				tableData: [],
				formLabelWidth: '100px', //对话框表单宽度
			}
		},
		methods: {
	
		},
		created() {
			getRoles().then(res => {
				if(res.code === 200){
					this.tableData = res.result
				}
			})
		}
	}
</script>

<style>
</style>
