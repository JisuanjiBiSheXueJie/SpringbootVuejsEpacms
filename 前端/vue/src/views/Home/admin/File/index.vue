<template>
	<div>
		<!-- 多搜索栏 -->
		<div style="margin-bottom: 10px;">
			<el-input size="mini" style="width: 200px;" v-model="fileName" placeholder="请输入名称"></el-input>
			<el-button @click="search" size="mini" icon="el-icon-search" class="ml-5" type="primary"  style="margin-left: 5px;">搜索</el-button>
			<el-button @click="reset" size="mini" icon="el-icon-refresh" class="ml-5" type="primary" style="margin-left: 5px;">重置</el-button>
		</div>
		
		<!-- 多操作 -->
		<div style="margin: 10px 0;display: flex;">
			
			<el-upload
				action="http://localhost:8888/api/upload"
				:show-file-list="false" 
				:on-success="handleFileUploadSuccess">
				<el-button size="mini" icon="el-icon-upload" class="mr-5" type="primary">文件上传</el-button>
			</el-upload>
			
			<el-popconfirm confirm-button-text='确定' cancel-button-text='再想想' icon="el-icon-info" icon-color="red"
				title="确定删除吗？" @confirm="delFileBatch">
				<el-button size="mini" icon="el-icon-remove-outline" class="ml-5" type="danger" slot="reference">批量删除</el-button>
			</el-popconfirm>
			
		</div>
		
		<el-table :data="tableData" border stripe size="mini"
              :header-cell-style="{background:'#f6bec8',color:'#555'}"
              @selection-change="handleSelectionChange">
			<!-- 多选框 -->
			<el-table-column type="selection" align="center" width="40"></el-table-column>
			<el-table-column prop="id" label="文件ID" width="80"></el-table-column>
			<el-table-column prop="name" label="文件名称" :show-overflow-tooltip='true' width="200"></el-table-column>
			<el-table-column  label="文件大小">
				<template slot-scope="scope">
					{{scope.row.size}}（KB）
				</template>
			</el-table-column>
			
			<el-table-column  label="文件链接" width="500">
				<template slot-scope="scope">
					<el-tag>{{scope.row.url}}</el-tag>
				</template>
			</el-table-column>
			
			<el-table-column label="下载" >
				<template slot-scope="scope">
					<el-button type="primary" size="mini" @click="download(scope.row)">下载</el-button>
				</template>
			</el-table-column>
			
			<el-table-column label="启用" >
				<template slot-scope="scope">
					<el-switch v-model="scope.row.enabled" active-color="#13ce66" inactive-color="#ccc" @change="changeEnabled(scope.row)"></el-switch>
				</template>
			</el-table-column>
			
			<el-table-column label="操作" align="center" width="80">
				<template slot-scope="scope">
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
		
	</div>
</template>

<script>
	import {
		getFilesByPage,
		updateEnabled,
		delFileById,
		delFileByBatch
	} from '@/network/file.js'
	
	export default {
		name: 'File',
		data(){
			return {
					tableData: [],
					currentPage: 1,
					total: 0,
					pageSize: 10,
					pageSizes: [5, 10, 15, 20],
					fileName: '',
					dialogFormVisible: false, //是否显示对话框
					formLabelWidth: '100px', //对话框表单宽度
					multipleSelection: [], //多选框选中对象
          searchConditions: []
			}
		},
		methods: {
			changeEnabled(row){
				updateEnabled(row).then(res => {
					if(res.code !== 200){
						return this.$message.error("操作失败");
					}
          this.$message.success("操作成功");
        })
			},
			download(row){
				if(!row.enabled){
          return this.$message.error("请先启用文件")
				}
        window.open(row.url);
      },
			handleFileUploadSuccess(res){
        if (res.code !== 200){
          return this.$message.error("文件上传失败")
        }
				this.$message.success("文件上传成功")
				this.load()
			},
			// 批量删除文件
			delFileBatch() {
				let ids = this.multipleSelection.map(obj => obj.id);
				if(ids.length === 0){
					return this.$message.warning("请选择要删除的文件");
				}
        let idsStr = ids.join(',');
        delFileByBatch(idsStr).then(res => {
          if(res.code !== 200){
            return this.$message.error("批量删除失败");
          }
          this.$message.success("批量删除成功");
          this.load()
        })
			},
			handleSelectionChange(objArray) {
        // 将选中的一些对象赋值给multipleSelection
				this.multipleSelection = objArray;
			},
			handleDel(id) {
        // 根据ID删除文件
				delFileById(id).then(res => {
					if(res.code !== 200){
						return this.$message.error("删除失败");
					}
          this.$message.success("删除成功");
          // this.currentPage = 1
          this.load();
				})
			},
			reset() { //重置搜索栏
				this.fileName = '';
				this.load();
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
        if(this.fileName.trim() !== ''){
          this.searchConditions.push({
            column: "name",
            value: this.fileName.trim(),
            type: "like",
          })
        }
				getFilesByPage(this.currentPage, this.pageSize, this.searchConditions).then(res => {
					this.total = res.data.totalCount
					this.tableData = res.data.dataList;
          this.searchConditions = []
        })
			}
		},
		created() {
      this.load();
		}
	}
</script>