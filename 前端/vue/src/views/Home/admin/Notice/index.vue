<template>
	<div>
		<!-- 搜索栏 -->
		<div style="margin-bottom: 10px;display: flex;">
			<el-input size="mini" style="width: 200px;" v-model="noticeTitle" placeholder="请输入通知标题"></el-input>
			<el-button size="mini" class="ml-5" icon="el-icon-search" type="primary" @click="search" style="margin-left: 5px;">搜索</el-button>
			<el-button size="mini" class="ml-5" icon="el-icon-refresh" type="primary" @click="reset" style="margin-left: 5px;">重置</el-button>
		</div>

    <!-- 操作 -->
    <div style="margin: 10px 0;">
      <el-button size="mini" icon="el-icon-circle-plus-outline" type="success" @click="handleAdd">添加通知</el-button>
      <el-popconfirm confirm-button-text='确定' cancel-button-text='再想想' icon="el-icon-info" icon-color="red"
                     title="确定删除吗？" @confirm="delPatch">
        <el-button size="mini" class="ml-10" icon="el-icon-remove-outline" type="danger" slot="reference">批量删除</el-button>
      </el-popconfirm>
    </div>
		
		<el-table :data="tableData" border stripe size="mini" :header-cell-style="{background:'#d5e3d4',color:'#555'}"
			@selection-change="handleSelectionChange">
			<!-- 多选框 -->
			<el-table-column type="selection" align="center" width="40"></el-table-column>
			<el-table-column prop="id" label="ID" width="80"></el-table-column>
			<el-table-column prop="title" label="通知标题" show-overflow-tooltip ></el-table-column>
			<el-table-column prop="source" label="通知来源"></el-table-column>
      <el-table-column prop="updateTime" :show-overflow-tooltip="true" label="更新时间"></el-table-column>
      <el-table-column prop="createTime" :show-overflow-tooltip="true" label="通知时间"></el-table-column>

			<el-table-column label="操作" align="center">
				<template v-slot="scope">
					<!-- 编辑 -->
					<el-button type="primary" circle size="mini" @click="handleEdit(scope.row.id)">
						<i class="el-icon-edit"></i>
					</el-button>
					
					<el-popconfirm confirm-button-text='确定' cancel-button-text='再想想' icon="el-icon-info"
						icon-color="red" title="确定删除吗？" @confirm="handleDel(scope.row.id)">
						<el-button style="margin-left: 5px;" size="mini" circle type="danger" slot="reference"><i
								class="el-icon-delete"></i></el-button>
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
		
		<!-- 对话框表单 @@ 添加通知 -->
		<el-dialog title="添加通知" :visible.sync="dialogFormVisible1" append-to-body>
			<el-form :model="form" :rules="rules" ref="form">
				<el-form-item label="通知标题" :label-width="formLabelWidth" prop="title">
						<el-input v-model="form.title"></el-input>
				</el-form-item>
				<el-form-item label="通知来源" :label-width="formLabelWidth" prop="source">
					 <el-input v-model="form.source"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="cancel">取 消</el-button>
				<el-button type="primary" @click="addNoticeBtn('form')">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>


<script>
	import { getNoticesByCondition,addNotice,delNotice,delNoticeByBatch} from '@/network/notice.js'
	export default {
		name: 'Notice',
		data(){
			return {
          searchConditions: [],
					uid: this.$store.state.currentUser.id,
					rid: this.$store.state.currentUser.rid,
					tableData: [],
					currentPage: 1,
					dialogCurrentPage: 1,
					total: 0,
					dialogTotal: 0,
					pageSize: 10,
					dialogPageSize: 10,
					dialogPageSizes: [10, 20, 40, 50],
					pageSizes: [10, 20, 30, 40],
					noticeTitle: '',
					dialogFormVisible1: false, //是否显示对话框
					dialogFormVisible2: false, //是否显示对话框
					formLabelWidth: '100px', //对话框表单宽度
					multipleSelection: [] ,//多选框选中的对象
					form:{
					  uid: '',//通知者
						title: '',//通知标题
						source: ''//来源
					},
					rules: {
						title:[
							{ required: true, message: '请输入通知标题', trigger: 'blur' },
						],
						source:[
							{ required: true, message: '请输入通知来源', trigger: 'blur' },
						]
					}
			}
		},
		methods: {
			handleEdit(id){
				//编辑通知详情
				this.$router.push('editor?id='+id)
			},
			addNoticeBtn(form){//添加通知
				this.$refs[form].validate((valid)=>{
					if(valid){
						this.form.uid = this.uid
						addNotice(this.form).then(res => {
							if(res.code === 200){
								this.dialogFormVisible1 = !this.dialogFormVisible1
								//清空表单数据
								this.form = {
									uid: '',
									title: '',
									source: ''
								}
								this.$message.success('通知添加成功!');
								this.load();
							}else{
								this.$message.error('通知添加失败!');
							}
						})
					}else{
						return false
					}
				})
			},
			cancel(){
				this.dialogFormVisible1 = !this.dialogFormVisible1
				this.form = {
					title: '',//通知标题
					source: ''//来源
				}
			},
			handleAdd(){//打开添加新闻标摊
				this.dialogFormVisible1 = !this.dialogFormVisible1
			},
			delPatch() {//批量删除
				let ids = this.multipleSelection.map(obj => obj.id);
				if(ids.length === 0){
					return this.$message.info("请选择要删除的通知");
				}
        delNoticeByBatch(ids.join(',')).then(res => {
          if(res.code !== 200){
            this.$message.error("批量删除失败");
          }
          this.$message.success(res.message);
          this.load()
        })
			},
			handleSelectionChange(objArray) {
				this.multipleSelection = objArray; //将选中的一些对象赋值给multipleSelection
			},
			handleDel(id) { //删除
				delNotice(id).then(res => {
					if(res.code !== 200){
						this.$message.error("删除失败");
					}
          return this.$message.success("删除成功");
          this.load();

        })
			},
			reset() { //重置搜索栏
				this.noticeTitle = '';
				this.load(); //重新加载数据
			},
			search() { //条件搜索
				this.currentPage = 1;
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
        // 如果是管理员查询全部的通知
				/*if(this.rid !== 1){
          this.searchConditions.push({
            column: "uid",
            value: this.uid,
            type: "eq",
          })
				}else{
          this.searchConditions = []
        }*/
        if(this.noticeTitle.trim() !== ''){
          this.searchConditions.push({
            column: "title",
            value: this.noticeTitle.trim(),
            type: "like",
          })
        }
        getNoticesByCondition(this.currentPage, this.pageSize, this.searchConditions).then(res => {
          this.total = res.data.totalCount
          this.tableData = res.data.dataList
          this.searchConditions = []
        })
			},
		},
		created() {
			this.load()
		}
	}
</script>

<style>
</style>
