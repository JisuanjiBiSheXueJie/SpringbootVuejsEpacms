<template>
	<div>
		<!-- 搜索栏 -->
		<div style="margin-bottom: 10px;display: flex;">
			<el-input size="mini" style="width: 200px;" v-model="newsTitle" placeholder="请输入名称" />
			<el-button size="mini" class="ml-5" icon="el-icon-search" type="primary" @click="search" style="margin-left: 5px;">搜索</el-button>
			<el-button size="mini" class="ml-5" icon="el-icon-refresh" type="primary" @click="reset" style="margin-left: 5px;">重置</el-button>
		</div>

    <!-- 操作 -->
    <div style="margin: 10px 0;">
      <el-button size="mini" icon="el-icon-circle-plus-outline" type="success" @click="handleAdd">添加新闻</el-button>
      <el-button size="mini" icon="el-icon-circle-plus-outline" type="success" @click="getNewsByBatchHandle">爬取新闻</el-button>
      <el-popconfirm confirm-button-text='确定' cancel-button-text='再想想' icon="el-icon-info" icon-color="red"
                     title="确定删除吗？" @confirm="delBatch">
        <el-button size="mini" icon="el-icon-remove-outline" class="ml-10" type="danger" slot="reference">批量删除</el-button>
      </el-popconfirm>
    </div>
		
		<el-table :data="tableData" border stripe size="mini" :header-cell-style="{background:'#d5ebe1',color:'#555'}"
			@selection-change="handleSelectionChange">
			<!-- 多选框 -->
			<el-table-column type="selection" align="center" width="40"></el-table-column>
			<el-table-column prop="id" label="ID" width="80"></el-table-column>
			<el-table-column prop="title" label="新闻标题" show-overflow-tooltip></el-table-column>
			<el-table-column prop="url" :show-overflow-tooltip="true" label="新闻URL" >
				  <template v-slot="scope">
            <el-link size="mini" :href="scope.row.url" target="_blank">{{ scope.row.url}}</el-link>
					</template>
			</el-table-column>
			<el-table-column prop="createTime" label="摘录日期" width="150"></el-table-column>
			
			<el-table-column label="操作" align="center" width="80">
				<template v-slot="scope">
					<el-popconfirm confirm-button-text='确定' cancel-button-text='再想想' icon="el-icon-info"
						icon-color="red" title="确定删除吗？" @confirm="delNews(scope.row.id)">
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
		
		<!-- 对话框表单 @@ 添加单条新闻 -->
		<el-dialog title="添加新闻" :visible.sync="dialogFormVisible1" append-to-body>
			<el-form :model="form" :rules="rules" ref="form">
				<el-form-item label="新闻标题" :label-width="formLabelWidth" prop="newsTitle">
						<el-input prefix-icon="el-icon-connection" v-model="form.newsTitle"></el-input>
				</el-form-item>
				<el-form-item label="新闻链接" :label-width="formLabelWidth" prop="newsURL">
					 <el-input prefix-icon="el-icon-link" v-model="form.newsURL"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="cancel">取 消</el-button>
				<el-button type="primary" @click="addNewsBtn('form')">确 定</el-button>
			</div>
		</el-dialog>
		
		<!-- 爬取新闻对话框表单 -->
		<el-dialog title="新闻列表" :visible.sync="dialogFormVisible2" width="70%" append-to-body>
			<el-form label-width="80px">
			  <el-form-item label="新闻链接(URL):" label-width="120px">
			    <el-input v-model="url"></el-input>
			  </el-form-item>
			  <el-form-item label="CSS选择器:" label-width="120px">
			    <el-input v-model="cssQuery"></el-input>
			  </el-form-item>
				<el-button  type="primary" size="mini" @click="spiderNews">爬取新闻</el-button>
			</el-form>
			
			<el-table :data="newsTableData" border stripe @selection-change="handleSelectionChange" style="margin-top: 5px;">
				<el-table-column type="selection" align="center" width="40"></el-table-column>
				<el-table-column property="title" label="标题" show-overflow-tooltip ></el-table-column>
				<el-table-column property="url" label="url" >
          <template v-slot="scope">
            <el-link size="mini" :href="scope.row.url" target="_blank">{{ scope.row.url}}</el-link>
          </template>
				</el-table-column>
			</el-table>
			
			<!-- 对话框分页栏 -->
			<div style="padding: 10px 0;">
				<el-pagination @size-change="dialogHandleSizeChange" @current-change="dialogHandleCurrentChange"
					:current-page="dialogCurrentPage" :page-sizes="dialogPageSizes" :page-size="dialogPageSize" :total="dialogTotal"
					layout="total, sizes, prev, pager, next, jumper">
				</el-pagination>
			</div>
			
			<div slot="footer" class="dialog-footer">
				<el-button size="mini" @click="dialogCancel">取 消</el-button>
				<el-button size="mini" type="primary" @click="addNewsBatchBtn">确定添加</el-button>
			</div>
		</el-dialog>
		
	</div>
</template>
<script>
	import {
		getNewsByPage,
		delNewsByBatch,
		addNews,
		getBatchNewsByUrl,
		addBatchNews,
    deleteNews
	} from '@/network/news.js'
	
	export default {
		name: 'News',
		data(){
			return {
					tableData: [],
					newsTableData: [],
          searchConditions:[],
          currentPage: 1,
					dialogCurrentPage: 1,
					total: 0,
					dialogTotal: 0,
					pageSize: 10,
					dialogPageSize: 10,
					dialogPageSizes: [10, 20, 40, 50],
					pageSizes: [10, 20, 30, 40],
					newsTitle: '',
					dialogFormVisible1: false, //是否显示对话框
					dialogFormVisible2: false, //是否显示对话框
					formLabelWidth: '100px', //对话框表单宽度
					multipleSelection: [] ,//多选框选中的对象
					form:{
						newsTitle: '',
						newsURL: ''
					},
					rules: {
						newsTitle:[
							{ required: true, message: '请输入新闻标题', trigger: 'blur' },
						],
						newsURL:[
							{ required: true, message: '请输入新闻链接', trigger: 'blur' },
						]
					},
					url: 'http://health.people.com.cn/GB/26466/431463/431538/index.html',
					cssQuery: 'body > div.ztbg.clearfix > div.p2_content.w980.mt30.p2_content_mt10.o_h > div.p2_right.fr'
			}
		},
		methods: {
			addNewsBtn(form){//添加新闻
				this.$refs[form].validate((valid)=>{
					if(!valid){
						return false
					}
          addNews(this.form).then(res => {
            if(res.code !== 200){
              return this.$message.error('新闻添加失败!');
            }
            this.dialogFormVisible1 = !this.dialogFormVisible1
            this.form = {
              newsTitle: '',
              newsURL: ''
            }
            this.$message.success('新闻添加成功!')
            this.load();
          })
				})
			},
			cancel(){
				this.dialogFormVisible1 = !this.dialogFormVisible1
				this.form = {
					newsTitle: '',
					newsURL: ''
				}
			},
      // 打开添加新闻标摊
			handleAdd(){
				this.dialogFormVisible1 = !this.dialogFormVisible1
			},
			getNewsByBatchHandle(){
				this.dialogFormVisible2 = !this.dialogFormVisible2
				// this.dialogLoad();
			},
      // 批量删除和单条删除
      delBatch() {
				let ids = this.multipleSelection.map(obj => obj.id);
				if(ids.length === 0) {
					return this.$message.info("请选择要删除的新闻")
				}
        delNewsByBatch(ids.join(',')).then(res => {
          if(res.code !== 200){
            return this.$message.error(res.message)
          }
          this.$message.success(res.message)
          this.load()
        })
			},
      delNews(id){
        deleteNews(id).then(res => {
          if(res.code !== 200){
            return this.$message.error("删除失败")
          }
          this.$message.success("删除成功")
          this.load()
        })
      },
			handleSelectionChange(objArray) {
        // 将选中的一些对象赋值给multipleSelection
				this.multipleSelection = objArray
			},
      //重置搜索栏
			reset() {
				this.newsTitle = ''
				this.load();
			},
      //条件搜索
			search() {
				this.currentPage = 1
				this.load()
			},
			handleSizeChange(pageSize) {
				this.pageSize = pageSize
				this.load()
			},
			handleCurrentChange(currentPage) {
				this.currentPage = currentPage
				this.load()
			},
			//------------------------爬取新闻事件-------------------------
			dialogHandleSizeChange(pageSize){
				this.dialogPageSize = pageSize
				this.dialogLoad();
			},
			dialogHandleCurrentChange(currentPage){
				this.dialogCurrentPage = currentPage;
				this.dialogLoad();
			},
			dialogCancel(){
				this.dialogFormVisible2 = false
				this.newsTableData = []
			},
      // 有选择的添加爬取的新闻
			addNewsBatchBtn(){
				if(this.multipleSelection.length === 0){
					this.dialogFormVisible2 = !this.dialogFormVisible2
					return;
				}
        addBatchNews(this.multipleSelection).then(res => {
          if(res.code !== 200){
            this.dialogFormVisible2 = !this.dialogFormVisible2
            return;
          }
          this.$message.success('添加成功')
          this.load()
          this.dialogFormVisible2 = !this.dialogFormVisible2
        })
			},			
			spiderNews(){
        //爬取新闻
				this.dialogLoad()
			},
			dialogLoad(){
				getBatchNewsByUrl(this.dialogCurrentPage, this.dialogPageSize, this.url, this.cssQuery,).then(res => {
          if(res.code !== 200){
            return this.$message.error('数据请求失败，请重试!')
          }
          this.dialogTotal = res.data.total
          this.newsTableData = res.data.data
          this.dialogFormVisible2 = true
				})
			},
      load() {
        if(this.newsTitle.trim() !== ''){
          this.searchConditions.push({
            column: "title",
            value: this.newsTitle.trim(),
            type: "like",
          })
        }
        getNewsByPage(this.currentPage, this.pageSize, this.searchConditions).then(res => {
          this.total = res.data.totalCount
          this.tableData = res.data.dataList;
          this.searchConditions = []
        })
      }
		},
		created() {
			this.load()
		}
	}
</script>

