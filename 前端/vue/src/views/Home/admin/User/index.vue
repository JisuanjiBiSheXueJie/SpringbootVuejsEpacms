<template>
	<div>
		<!-- 多搜索栏 -->
		<div style="margin-bottom: 10px;">
			<el-input size="mini" style="width: 200px;" v-model="user.username"
				placeholder="请输入名称"></el-input>
			<el-input size="mini" style="width: 200px;" v-model="user.email"
				placeholder="请输入邮箱" class="ml-5"></el-input>
			<el-input size="mini" style="width: 200px;" v-model="user.address"
				placeholder="请输入地址" class="ml-5"></el-input>
			<el-button icon="el-icon-search" size="mini" class="ml-5" type="primary" @click="search" style="margin-left: 5px;">搜索</el-button>
			<el-button icon="el-icon-refresh" size="mini" class="ml-5" type="primary" @click="reset" style="margin-left: 5px;">重置</el-button>
		</div>

		<!-- 多操作 -->
		<div style="margin: 10px 0;">
			<el-button icon="el-icon-circle-plus-outline"
                 size="mini" class="mr-5"
                 type="success" @click="handleAdd">新增</el-button>
			
			<el-popconfirm confirm-button-text='确定' cancel-button-text='再想想' icon="el-icon-info" icon-color="red"
				title="确定删除吗？" @confirm="delBatch">
				<el-button icon="el-icon-remove-outline"
                   size="mini" class="ml-5" type="danger"
                   slot="reference">批量删除</el-button>
      </el-popconfirm>

			<!-- 导入 -->
			<!-- <el-button size="small" class="ml-10" type="primary" @click="">导入 <i class="el-icon-bottom"></i></el-button> -->

			<!-- 导出 -->
			<el-button icon="el-icon-top" size="mini" class="ml-10" type="primary" @click="exp">导出Excel</el-button>
		</div>

		<!-- 表格 -->
		<el-table :data="tableData" border stripe size="mini"
              :header-cell-style="{background:'#b1d5c8',color:'#555'}"
              @selection-change="handleSelectionChange">
			<!-- 多选框 -->
			<el-table-column align="center" type="selection"  width="40"></el-table-column>
			<el-table-column type="expand" >
			      <template v-slot="props">
			        <el-form label-position="left" inline class="demo-table-expand">
								<el-form-item label="用户名">
								  <span>{{ props.row.username}}</span>
								</el-form-item>
			          <el-form-item label="手机号码">
			            <span>{{ props.row.phone}}</span>
			          </el-form-item>
								<el-form-item label="用户邮箱">
								  <span>{{ props.row.email}}</span>
								</el-form-item>
								<el-form-item label="学院">
								  <span>{{ props.row.academy}}</span>
								</el-form-item>
								<el-form-item label="班级">
								  <span>{{ props.row.classNum}}</span>
								</el-form-item>
								<el-form-item label="注册时间">
								  <span>{{ props.row.registerTime}}</span>
								</el-form-item>
			        </el-form>
			      </template>
			    </el-table-column>
			<el-table-column align="center" prop="id" label="ID" width="60"></el-table-column>
			<el-table-column align="center" label="头像" width="80">
				<template v-slot="scope">
					  <el-avatar shape="square" style="width: 25px; height: 25px" @error="errorHandler" :src="scope.row.avatarUrl" fit="cover">
              <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png" />
            </el-avatar>
				</template>
			</el-table-column>
			<el-table-column align="center" prop="username" label="用户名"></el-table-column>
			<el-table-column align="center" prop="realName" label="真实姓名"></el-table-column>
			<el-table-column align="center" :show-overflow-tooltip='true' prop="address" label="详细地址"></el-table-column>
			<el-table-column align="center" :show-overflow-tooltip='true' prop="email" label="邮箱"></el-table-column>
			<el-table-column align="center" label="角色">
				<template v-slot="scope">
					<el-tag :type="roleType[scope.row.rid]">{{roles[scope.row.rid]}}</el-tag>
				</template>
			</el-table-column>
			<el-table-column align="center" label="操作" width="160px">
				<template v-slot="scope">
					<!-- 重置用户密码 -->
					<el-popconfirm confirm-button-text='确定' cancel-button-text='再想想' icon="el-icon-info"
						icon-color="red" title="确定要重置密码吗？" @confirm="resetPasswordBtn(scope.row.id)">
						<el-button circle size="small" type="warning" slot="reference">
							<i class="el-icon-refresh"></i>
						</el-button>
					</el-popconfirm>

					<el-button style="margin-left: 10px;" type="primary" circle size="small" @click="handleEdit(scope.row)">
						<i class="el-icon-edit"></i>
					</el-button>

					<!-- 删除用户 -->
					<el-popconfirm confirm-button-text='确定' cancel-button-text='再想想' icon="el-icon-info"
						icon-color="red" title="确定删除吗？" @confirm="handleDel(scope.row.id)">
						<el-button style="margin-left: 10px;" circle size="small" type="danger" slot="reference">
							<i class="el-icon-delete"></i>
						</el-button>
					</el-popconfirm>
				</template>
			</el-table-column>
		</el-table>

		<!-- 对话框表单 -->
		<el-dialog title="用户信息" :visible.sync="dialogFormVisible" append-to-body>
			<el-form :model="form">
				<el-form-item label="学号" :label-width="formLabelWidth">
					<el-input disabled v-model="form.sno" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="用户名" :label-width="formLabelWidth">
					<el-input v-model="form.username" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="真实姓名" :label-width="formLabelWidth">
					<el-input v-model="form.realName" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="性别" :label-width="formLabelWidth">
					<el-radio v-model="form.sex" label="男">男</el-radio>
					<el-radio v-model="form.sex" label="女">女</el-radio>
				</el-form-item>
				<el-form-item label="学院" :label-width="formLabelWidth">
					<el-select v-model="form.academy" placeholder="请选择">
						<el-option v-for="academy in academys" :key="academy.value" :label="academy.label" :value="academy.value">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="邮箱" :label-width="formLabelWidth">
					<el-input v-model="form.email" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="电话号码" :label-width="formLabelWidth">
					<el-input v-model="form.phone" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="角色" :label-width="formLabelWidth">
					<el-radio-group v-model="roleName">
					  <el-radio-button label="辅导员"></el-radio-button>
					  <el-radio-button label="学生"></el-radio-button>
					  <el-radio-button label="教职工"></el-radio-button>
					</el-radio-group>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialogFormVisible = false">取 消</el-button>
				<el-button type="primary" @click="update">确 定</el-button>
			</div>
		</el-dialog>

    <!-- 分页栏 -->
    <div style="padding: 10px 0;">
      <el-pagination layout="total, sizes, prev, pager, next, jumper"
                     @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="currentPage"
                     :page-sizes="pageSizes"
                     :page-size="pageSize"
                     :total="total" >
      </el-pagination>
    </div>
	</div>
</template>

<script>
	import {
		getUsersByPage,
		expUserExcel,
		resetPassword,
		updateUser,
		delBatchUsers,
		delUserById
	} from '@/network/user.js'

	export default {
		name: 'User',
		data() {
			return {
				roleName: '',
				tableData: [],
				currentPage: 1,
				total: 0,
				pageSize: 10,
				pageSizes: [5, 10, 15, 20],
        searchConditions:[],
				user: {
					username: '',
					email: '',
					address: ''
				},
				dialogFormVisible: false, //是否显示对话框
				form: { //对话框表单
					username: '',
					realName: '',
					sex: '',
					age: '',
					email: '',
					address: '',
					phone: '',
					academy: '',
					rid: ''
				},
				academys: [{
					value: '信息与电子工程学院',
					label: '信息与电子工程学院'
				}, {
					value: '马克思主义学院',
					label: '马克思主义学院'
				}, {
					value: '土木工程学院',
					label: '土木工程学院'
				}, {
					value: '机械与电气工程学院',
					label: '机械与电气工程学院'
				}, {
					value: '艺术学院',
					label: '艺术学院'
				}, {
					value: '人文学院',
					label: '人文学院'
				}, {
					value: '材料与化学工程学院',
					label: '材料与化学工程学院'
				}, {
					value: '管理学院',
					label: '管理学院'
				}, {
					value: '体育学院',
					label: '体育学院'
				}],
        roles:{
          1: '管理员',
          2: '辅导员',
          3: '学生',
          4: '教职工'
        },
        roleType:{
          1: 'success',
          2: 'danger',
          3: 'warning',
          4: 'info'
        },
				formLabelWidth: '100px', //对话框表单宽度
				multipleSelection: [] //多选框选中对象
			}
		},
		methods: {
      errorHandler(){
        return true
      },
      //重置密码
			resetPasswordBtn(id){
				resetPassword(id).then(res => {
					if(res.code !== 200){
						return this.$message.error('重置密码失败')
					}
          this.$message.success('重置密码成功')
        })
			},
			exp(){//导出功能
				window.open('http://localhost:8888/api/user/export');
			},
      //批量删除
			delBatch() {
				let ids = this.multipleSelection.map(obj => obj.id);
				if(ids.length === 0){
					return this.$message.info("请选择要删除的用户");
				}
        delBatchUsers(ids).then(res => {
          if(res.code !== 200){
            return this.$message.error('批量删除失败')
          }
          this.$message.success('批量删除成功')
          this.load()//重新加载
        })
			},
			handleSelectionChange(objArray) {
        // 将选中的一些对象赋值给multipleSelection
				this.multipleSelection = objArray;
			},
			handleDel(id) {
				delUserById(id).then(res=>{
					if(res.code !== 200){
            return this.$message.error('删除失败')
					}
          // this.currentPage = 1
          this.$message.success('删除成功')
          this.load()
				})
			},
			handleEdit(row) { //填写编辑表单
				this.dialogFormVisible = !this.dialogFormVisible
				this.form = row
        this.roleName = this.roles[row.rid]
			},
			update() { //更新数据
				this.dialogFormVisible = false;
				if(this.roleName === '辅导员'){
					this.form.rid = 2
				}else if(this.roleName === '学生'){
					this.form.rid = 3
				}else if(this.roleName === '教职工'){
					this.form.rid = 4
				}
				updateUser(this.form).then(res=>{
					if(res.code !== 200){
            return this.$message.error('更新失败')
					}
          this.$message.success('更新成功')
        })
			},
			handleAdd() { //填写新增表单
				this.dialogFormVisible = !this.dialogFormVisible
			},
			reset() { //重置搜索栏
				this.user = {
					username: '',
					email: '',
					address: ''
				};
				this.load(); //重新加载数据
			},
			search() { //条件搜索
				this.load();
			},
			handleSizeChange(pageSize) {
				this.pageSize = pageSize
				this.load() //重新加载数据
			},
			handleCurrentChange(currentPage) {
				this.currentPage = currentPage
				this.load() //重新加载数据
			},
			load() {
        if(this.user.username.trim() !== ''){
          this.searchConditions.push({
            column: "username",
            value: this.user.username.trim(),
            type: "like",
          })
        }else if(this.user.email.trim() !== ''){
          this.searchConditions.push({
            column: "email",
            value: this.user.email.trim(),
            type: "like",
          })
        }else if(this.user.address.trim() !== ''){
          this.searchConditions.push({
            column: "address",
            value: this.user.address.trim(),
            type: "like",
          })
        }
        // 分页查询
				getUsersByPage(this.currentPage, this.pageSize, this.searchConditions).then(res => {
					this.total = res.data.totalCount
					this.tableData = res.data.dataList;
          this.searchConditions = []
        })
			}
		},
		created() {
			//请求分页数据
			this.load()
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
