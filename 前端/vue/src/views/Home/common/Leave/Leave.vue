<template>
	<div>
		<!-- 搜索栏 -->
		<div style="margin-bottom: 10px;">
			
			<el-select v-if="roleId === 1 || roleId === 2" v-model="classNum" placeholder="请选择班级" size="mini">
				<el-option v-for="clazz in classes" :key="clazz.id" :label="clazz.classNum" :value="clazz.classNum">
				</el-option>
			</el-select>

			<el-date-picker v-model="dateValue" value-format="yyyy-MM-dd" align="right" class="ml-5" type="date" size="mini" placeholder="选择日期"
				:picker-options="pickerOptions">
			</el-date-picker>
			<!-- <el-input size="mini" style="width: 200px;" v-model="leaveRequest" placeholder="请输入名称"></el-input> -->

			<el-button size="mini" type="primary" @click="search" icon="el-icon-search" style="margin-left: 10px;">搜索</el-button>
			<el-button size="mini" class="ml-5" type="primary" icon="el-icon-refresh" @click="reset" style="margin-left: 5px;">重置</el-button>
			<!-- 学生和教职工显示按钮-->
			<el-button v-if="roleId === 3 || roleId === 4" size="mini" class="mr-5" type="success" @click="handleAdd">
				申请离校 <i class="el-icon-circle-plus-outline"></i>
			</el-button>

			<el-popconfirm confirm-button-text='确定' cancel-button-text='再想想' icon="el-icon-info" icon-color="red"
				title="确定删除吗？" @confirm="delBatch">
				<!-- 若是 管理员角色或其它教职工 则不显示新增按钮，否则显示按钮-->
				<el-button v-if="roleId === 1 || roleId === 2" size="mini" class="ml-10" type="danger"
					slot="reference">
					批量删除 <i class="el-icon-remove-outline"></i>
				</el-button>
			</el-popconfirm>
		</div>

		<!-- 表格 -->
		<el-table :data="tableData" border stripe size="mini" :header-cell-style="{background:'#ecd9c7',color:'#555'}"
			@selection-change="handleSelectionChange">
			<!-- 多选框 -->
			<el-table-column align="center" type="selection" width="40"></el-table-column>
			<el-table-column type="expand">
				<template v-slot="props">
					<el-form label-position="left" inline class="demo-table-expand">
						<el-form-item label="用户名">
							<span>{{ props.row.user.username}}</span>
						</el-form-item>
						<el-form-item label="手机号码">
							<span>{{ props.row.user.phone}}</span>
						</el-form-item>
						<el-form-item label="用户邮箱">
							<span>{{ props.row.user.email}}</span>
						</el-form-item>
						<el-form-item label="学院">
							<span>{{ props.row.user.academy}}</span>
						</el-form-item>
					</el-form>
				</template>
			</el-table-column>
			<el-table-column align="center" prop="user.sno" label="学号"></el-table-column>
			<el-table-column align="center" prop="user.realName" label="真实姓名"></el-table-column>
			<el-table-column align="center" prop="user.classNum" label="班级"></el-table-column>
			<el-table-column align="center" prop="reason" label="离校原因" show-overflow-tooltip></el-table-column>
			<el-table-column align="center" prop="trajectory" label="行程轨迹" show-overflow-tooltip></el-table-column>
			<el-table-column align="center" prop="leaveTime" label="离校日期" show-overflow-tooltip></el-table-column>
			<el-table-column align="center" prop="returnTime" label="返校日期" show-overflow-tooltip></el-table-column>
			<el-table-column align="center" prop="status" label="审核状态">
				<template v-slot="scope">
					<el-tag :type="getType(scope.row.status)">{{getStatus(scope.row.status)}}</el-tag>
				</template>
			</el-table-column>
			<el-table-column align="center" label="操作" width="160px">
				<template v-slot="scope">

					<template>
						<!-- 若是学生，则有编辑和删除功能 -->
						<!-- 若是辅导员，则有批准和拒绝功能 -->
						<!-- 编辑(学生) -->
						<el-button v-if="roleId === 3" circle size="mini" type="primary" slot="reference"
							@click="handleEdit(scope.row)">
							<i class="el-icon-edit"></i>
						</el-button>

						<!-- 批准 -->
						<el-button v-if="roleId === 2" circle size="mini" type="success" slot="reference"
							@click="updateStatusBtn(scope.row.id,2)">
							<i class="el-icon-check"></i>
						</el-button>
						<!-- 拒绝 -->
						<el-button v-if="roleId === 2" style="margin-left: 10px;" type="danger" circle size="mini"
							@click="updateStatusBtn(scope.row.id,3)">
							<i class="el-icon-close"></i>
						</el-button>
					</template>

					<!-- 删除记录 -->
					<el-popconfirm confirm-button-text='确定' cancel-button-text='再想想' icon="el-icon-info"
						icon-color="red" title="确定删除吗？" @confirm="handleDel(scope.row.id)">
						<el-button style="margin-left: 10px;" type="danger" circle size="mini" slot="reference">
							<i class="el-icon-delete"></i>
						</el-button>
					</el-popconfirm>
				</template>
			</el-table-column>
		</el-table>

		<!-- 分页栏 -->
		<div style="padding: 10px 0;">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
				:current-page="currentPage" :page-sizes="pageSizes" :page-size="pageSize" :total="total"
				layout="total, sizes, prev, pager, next, jumper">
			</el-pagination>
		</div>

		<!-- 对话框表单 -->
		<el-dialog title="离校申请" :visible.sync="dialogFormVisible" append-to-body>
			<el-form :model="form" :rules="rules" ref="form">
				<el-form-item label="学号" :label-width="formLabelWidth">
					<el-input disabled :value="this.$store.state.currentUser.sno" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="真实姓名" :label-width="formLabelWidth">
					<el-input disabled :value="this.$store.state.currentUser.realName" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="行程轨迹" :label-width="formLabelWidth" prop="trajectory">
					<el-cascader :options="cityData" v-model="form.trajectory" separator=">" clearable></el-cascader>
				</el-form-item>
				<el-form-item label="离校时间" :label-width="formLabelWidth" prop="leaveTime" >
					<el-date-picker v-model="form.leaveTime" type="date" placeholder="选择日期" :picker-options="pickerOptions_form">
					</el-date-picker>
				</el-form-item>
				<el-form-item label="返校时间" :label-width="formLabelWidth" prop="returnTime" >
					<el-date-picker v-model="form.returnTime" type="date" placeholder="选择日期" :picker-options="pickerOptions_form">
					</el-date-picker>
				</el-form-item>
				<el-form-item label="离校原因" :label-width="formLabelWidth" prop="reason">
					<el-input type="textarea" show-word-limit maxlength="120" v-model="form.reason" autocomplete="off">
					</el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialogFormVisible = false">取 消</el-button>
				<el-button type="primary" @click="submit('form')">确 定</el-button>
			</div>
		</el-dialog>

	</div>
</template>

<script>
	import {
    getLeavesByCondition,
		addOrUpdateLeave,
		delLeave,
		updateStatus,
		delLeaveByBatch
	} from '@/network/leave.js'
	
	import {
		getRoleByUid
	} from '@/network/role.js'
	
	import { getAllClass } from '@/network/user.js'

	import cityData from '../../../../common/citydata.js'

	export default {
		name: 'Leave',
		data() {
			return {
        searchConditions: [],
				dateValue: '',
				pickerOptions: {
					disabledDate(time) {
						return time.getTime() > Date.now();
					},
					shortcuts: [{
						text: '今天',
						onClick(picker) {
							picker.$emit('pick', new Date());
						}
					}, {
						text: '昨天',
						onClick(picker) {
				 		const date = new Date();
							date.setTime(date.getTime() - 3600 * 1000 * 24);
							picker.$emit('pick', date);
						}
					}, {
						text: '一周前',
						onClick(picker) {
							const date = new Date();
							date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
							picker.$emit('pick', date);
						}
					}]
				},
				// 1 -> 管理员 
				// 2 -> 辅导员 
				// 3 -> 学生 
				// 4 -> 教职工 
				roleId: this.$store.state.currentUser.rid,
				id: this.$store.state.currentUser.id,
				classNum: '',
				cityData,
				classes: [],
				tableData: [],
				currentPage: 1,
				total: 0,
				pageSize: 10,
				pageSizes: [5, 10, 15, 20],
				leaveRequest: '',
				pickerOptions_form: {
					disabledDate(time) {
						return time.getTime() <= Date.now();
					}
				},
				user: {
					username: '',
					email: ''
				},
				rules: {
					trajectory: [{
						required: true,
						message: '请输入行程轨迹',
						trigger: 'blur'
					}],
					leaveTime: [{
						required: true,
						message: '离校时间',
						trigger: 'blur'
					}],
					returnTime: [{
						required: true,
						message: '返校时间',
						trigger: 'blur'
					}],
					reason: [{
						required: true,
						message: '离校原因',
						trigger: 'blur'
					}]
				},
				dialogFormVisible: false, //是否显示对话框
				form: { //对话框表单
					id: null,
					uid: this.$store.getters.currentUser.id,
					trajectory: '',
					leaveTime: '',
					returnTime: '',
					reason: ''
				},
				formLabelWidth: '100px', //对话框表单宽度
				multipleSelection: [] //多选框选中对象
			}
		},
		methods: {
			reset() { //重置搜索栏
				this.classNum = ''
				this.dateValue = ''
				this.load(); //重新加载数据
			},
			updateStatusBtn(id, status) {
				updateStatus(id, status).then(res => {
					if (res.code !== 200) {
						this.$message.error('操作失败')
					}
          this.$message.success('操作成功')
          this.load()
				})
			},
			getType(status) {
				if (status === 1) {
					return ''
				} else if (status === 2) {
					return 'success'
				} else {
					return 'danger'
				}
			},
			//1 -> 待审核
			//2 -> 批准
			//3 -> 拒绝
			getStatus(status) {
				if (status === 1) {
					return '待审核'
				} else if (status === 2) {
					return '批准'
				} else if (status === 3) {
					return '拒绝'
				}
			},
			delBatch() { //批量删除
				let ids = this.multipleSelection.map(obj => obj.id);
				if (ids.length !== 0) {
					delLeaveByBatch(ids).then(res => {
						if (res.code !== 200) {
							this.$message.error('批量删除失败');
						}
            this.$message.success('批量删除成功');
            this.load()
					})
				} else {
					this.$message.warning("请选择要删除的记录");
				}
			},
			handleSelectionChange(objArray) {
				this.multipleSelection = objArray; //将选中的一些对象赋值给multipleSelection
			},
			handleDel(id) { //删除记录
				delLeave(id).then(res => {
					if (res.code !== 200) {
						this.$message.error('删除失败');
					}
          this.$message.success('删除成功');
          this.load();
				})
			},
			handleEdit(row) { //填写编辑表单
				this.dialogFormVisible = !this.dialogFormVisible;
				this.form = row
			},
			submit(form) { //保存用户
				this.$refs[form].validate((valid) => {
					if (valid) {
						this.dialogFormVisible = false;
						//将数组拼接成字符串
						this.form.trajectory = this.form.trajectory.join('')
						addOrUpdateLeave(this.form).then(res => {
							if (res.code !== 200) {
								this.$message.error(res.message)
							}
              this.form.trajectory = ''
              this.form.leaveTime = ''
              this.form.returnTime = ''
              this.form.reason = ''
              this.$message.success(res.result)
              this.load()
						})
					}
				})
			},
			handleAdd() {
				this.dialogFormVisible = !this.dialogFormVisible
				this.form.id = null
			},
			search() {
				this.load();
			},
			handleSizeChange(pageSize) {
				this.pageSize = pageSize
				this.load()
			},
			handleCurrentChange(currentPage) {
				this.currentPage = currentPage
				this.load()
			},
			load() {
				getLeavesByCondition(this.currentPage, this.pageSize, this.searchConditions).then(res => {
					this.total = res.data.totalCount
					this.tableData = res.data.dataList
				})
			}
		},
		created() {
      this.load()
			// 获取所有班级
			getAllClass().then(res => {
					this.classes = res.data
			})
		}
	}
</script>

<style>
	.demo-table-expand {
		font-size: 0;
	}

	.demo-table-expand label {
		width: 90px;
		color: #99a9bf;
	}

	.demo-table-expand .el-form-item {
		margin-right: 0;
		margin-bottom: 0;
		width: 50%;
	}
</style>
