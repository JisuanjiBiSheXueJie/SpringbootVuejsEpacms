<template>
	<div>
		<!-- 搜索栏 -->
		<div style="margin-bottom: 10px;">
			<el-select v-if="rid === 1 || rid === 2" v-model="classNum" placeholder="请选择班级" size="mini">
				<el-option v-for="clazz in classes" :key="clazz.id" :label="clazz.classNum" :value="clazz.classNum">
				</el-option>
			</el-select>
			
			<el-select v-model="form.cough" placeholder="咳嗽" class="ml-5" size="mini">
				<el-option v-for="item in items" :key="item.value" :label="item.label" :value="item.value">
				</el-option>
			</el-select>

			<el-select v-model="form.fever" placeholder="发烧" class="ml-5" size="mini">
				<el-option v-for="item in items" :key="item.value" :label="item.label" :value="item.value">
				</el-option>
			</el-select>

			<el-date-picker v-model="form.dateValue" value-format="yyyy-MM-dd" align="right" class="ml-5" type="date" size="mini" placeholder="选择日期"
				:picker-options="pickerOptions">
			</el-date-picker>

			<el-button size="mini" class="ml-10" icon="el-icon-search" type="primary" @click="search">搜索</el-button>
			<el-button size="mini" class="ml-10" icon="el-icon-refresh" type="primary" @click="reset">重置</el-button>
		</div>

    <div style="margin: 10px 0;">
      <!-- 批量删除按钮 -->
      <el-popconfirm v-if="rid === 1 || rid === 2" confirm-button-text='确定' cancel-button-text='再想想'
                     icon="el-icon-info" icon-color="red" title="确定删除吗？" @confirm="delBatch">
        <el-button size="mini" class="ml-10" icon="el-icon-remove-outline" type="danger" slot="reference">批量删除</el-button>
      </el-popconfirm>
    </div>

		<div style="display: flex;">

			<!-- 打卡按钮 -->
			<el-button v-if="rid === 3 || rid === 4" style="margin: 0 0 10px 0" size="mini" class="ml-10" :type="this.isClock ? 'success': 'danger'"
				@click="handleAdd" :disabled="this.isClock">
				{{this.isClock ? '已打卡': '未打卡'}} <i :class="this.isClock ? 'el-icon-circle-check': 'el-icon-circle-close'"></i>
			</el-button>

			<!-- 导出按钮 -->
		<!-- 	<el-button v-if="rid === 1 || rid === 2" size="mini" type="primary" @click="exp">
				导出Excel <i class="el-icon-top"></i>
			</el-button> -->
		</div>

		<!-- 表格 -->
		<el-table :data="tableData" border stripe size="mini" :header-cell-style="{background:'#f9d3e3',color:'#555'}"
			@selection-change="handleSelectionChange">
			<!-- 多选框 -->
			<el-table-column type="selection" width="40"></el-table-column>
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
						<el-form-item label="班级">
							<span>{{ props.row.user.classNum}}</span>
						</el-form-item>
					</el-form>
				</template>
			</el-table-column>
			<el-table-column align="center" prop="user.sno" label="学号" width="110"></el-table-column>
			<el-table-column align="center" prop="user.realName" label="真实姓名" width="120"></el-table-column>
			<el-table-column align="center" prop="user.phone" label="手机号码" width="120"></el-table-column>
			<el-table-column align="center" prop="user.email" label="用户邮箱" width="150"></el-table-column>
			<el-table-column align="center" prop="position" label="位置"></el-table-column>
			<el-table-column align="center" prop="time" label="打卡时间"></el-table-column>
			<el-table-column prop="temperature" label="体温" align="center" width="80">
				<template v-slot="scope">
					<el-tag effect="dark" :type="scope.row.temperature > 37.1?'danger':'success'">
						{{scope.row.temperature}}
					</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="cough" label="是否咳嗽" align="center" width="80">
				<template v-slot="scope">
					<el-tag effect="dark" :type="scope.row.cough ? 'danger':'success'">
						{{scope.row.cough?'有':'无'}}
					</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="fever" label="是否发烧" align="center" width="80">
				<template v-slot="scope">
					<el-tag effect="dark" :type="scope.row.fever ? 'danger':'success'">
						{{scope.row.fever?'有':'无'}}
					</el-tag>
				</template>
			</el-table-column>
			<el-table-column v-if="rid === 1 || rid === 2" label="操作" align="center" width="120px">
				<template v-slot="scope">

					<el-popconfirm confirm-button-text='确定' cancel-button-text='再想想' icon="el-icon-info"
						icon-color="red" title="确定删除吗？" @confirm="handleDel(scope.row.id)">
						<el-button style="margin-left: 10px;" circle size="mini" type="danger" slot="reference">
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
		<el-dialog title="打卡" :visible.sync="dialogFormVisible" width="700px" append-to-body>
			<el-form :model="form" :rules="rules" ref="form">
				<el-form-item label="位置信息" :label-width="formLabelWidth" prop="position">
					<div style="display: flex;">
						<el-tag :type="form.position === '' || form.position === undefined ? 'danger': 'success'">
							{{form.position === ''|| form.position === undefined ? '点击地图中的“定位按钮”获取位置信息': form.position}}
						</el-tag>
						<!-- <el-button style="margin-left: 10px;" size="mini" type="success" @click="handler" >定位</el-button> -->
					</div>
				</el-form-item>

				<!-- 显示地图信息 -->
				<div id="allmap"></div>
				<div>
					<!-- <baidu-map class="map" :center="map.center" :zoom="map.zoom" @ready="handler"> -->
					<baidu-map class="map" :center="map.center" :zoom="map.zoom">
						<!-- <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation> -->
						<bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" @locationSuccess="locationOK"
							:showAddressBar="true" :autoLocation="true"></bm-geolocation>
						<bm-marker :position="map.center" :dragging="map.dragging" animation="BMAP_ANIMATION_DROP">
						</bm-marker>
					</baidu-map>
				</div>

				<el-form-item style="margin-top: 20px;" label="当前体温" :label-width="formLabelWidth" prop="temperature">
					<el-input-number v-model="form.temperature" :precision="1" :step="0.1" :max="50"></el-input-number>
				</el-form-item>

				<el-form-item label="是否咳嗽" :label-width="formLabelWidth" prop="cough">
					<el-radio-group v-model="form.cough" size="mini">
						<el-radio label="0" border>无</el-radio>
						<el-radio label="1" border>有</el-radio>
					</el-radio-group>
				</el-form-item>

				<el-form-item label="是否发烧" :label-width="formLabelWidth" prop="fever">
					<el-radio-group v-model="form.fever" size="mini">
						<el-radio label="0" border>无</el-radio>
						<el-radio label="1" border>有</el-radio>
					</el-radio-group>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="cancel" size="mini">取 消</el-button>
				<el-button type="primary" size="mini" @click="save('form')">确 定</el-button>
			</div>
		</el-dialog>

	</div>
</template>

<script>
	import {
    getRecordsPageByCondition,
		addRecord,
		delRecord,
		delRecordByBatch,
		isClock
	} from '@/network/health.js'
	
		import { getAllClass } from '@/network/user.js'
	
	// import {getAllNoClockStudent} from '@/network/user.js'

	export default {
		name: 'Health',
		data() {
			return {
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
				classes: [],
				classNum: '',
				//位置信息
				map: {
					center: {
						lng: 112.39594008198,
						lat: 28.540380828159
					},
					zoom: 10,
					show: true,
					dragging: true
				},
				rid: this.$store.getters.currentUser.rid,
				id: this.$store.getters.currentUser.id,
				isClock: this.$store.state.isClock,
				items: [{
					value: '1',
					label: '有'
				}, {
					value: '0',
					label: '无'
				}],
				// noClockStudentsTableData: [],
        searchConditions: [],
				tableData: [], //表格数据
				currentPage: 1,
				total: 0,
				pageSize: 10,
				pageSizes: [5, 10, 15, 20],
				dialogFormVisible: false, //是否显示对话框
				form: {
					uid: '',
					position: '',
					temperature: '36.8',
					cough: '',
					fever: '',
					dateValue: ''
				},
				formLabelWidth: '120px', //对话框表单宽度
				multipleSelection: [], //多选框选中对象
				rules: {
					position: [{
						required: true,
						message: '请获取位置信息',
						trigger: 'blur'
					}, ],
					fever: [{
						required: true,
						message: '是否咳嗽',
						trigger: 'blur'
					}, ],
					cough: [{
						required: true,
						message: '是否发烧',
						trigger: 'blur'
					}, ],
					temperature: [{
						required: true,
						message: '体温',
						trigger: 'blur'
					}, ],
				}
			}
		},
		methods: {
			//定位成功后的钩子
			locationOK(point) {
				//浏览器定位 			  Geolocation 	优先调用浏览器H5定位接口，如果失败会调用IP定位
				//IP定位 	   		    LocalCity 	  根据用户IP 返回城市级别的定位结果
				//定位SDK辅助定位 		Geolocation 	当您的APP中有内置的Web页面，同时在Web页面需要提供您的当前位置信息时，可调用集成在App中的百度地图定位SDK来获取更精准的位置信息 
				let position = point.addressComponent.province + point.addressComponent.city + point.addressComponent
					.district + point.addressComponent.street + point.addressComponent.streetNumber
				this.form.position = position
			},
			//批量删除
			delBatch() {
				let ids = this.multipleSelection.map(obj => obj.id);
				if (ids.length === 0) {
					return this.$message.info("请选择要删除的文件");
				}
        delRecordByBatch(ids.join(',')).then(res => {
          if (res.code !== 200) {
            return this.$message.error("批量删除失败");
          }
          this.$message.success("批量删除成功");
          this.load()
        })
			},
			//导出功能
			exp() {
				window.open('http://localhost:8888/api/record/export/' + this.form.uid);
			},
			handleSelectionChange(objArray) {
        //将选中的一些对象赋值给multipleSelection
				this.multipleSelection = objArray;
			},
			//删除单条记录
			handleDel(id) {
				delRecord(id).then(res => {
					if (res.code !== 200) {
						return this.$message.success('删除失败');
					}
          this.$message.success('删除成功');
          this.load();
				})
			},
			//保存用户
			save(form) {
				this.$refs[form].validate((valid) => {
					if (valid) {
						this.dialogFormVisible = false;
						this.form.cough = Number.parseInt(this.form.cough)
						this.form.fever = Number.parseInt(this.form.fever)
						addRecord(this.form).then(res => {
							if (res.code !== 200) {
								return this.$message.error(res.message)
							}
              this.isClock = true
              this.$notify({
                title: '打卡成功',
                type: 'success',
                duration: 3000,
                offset: 0,
                message: '今日完成打卡',
                position: 'top-left'
              });
              this.form.cough = ''
              this.form.fever = ''
              this.load() //重新加载数据
						})
					}
				})
			},
			handleAdd() { //打卡表单
				this.dialogFormVisible = !this.dialogFormVisible
			},
			cancel() { //取消打卡
				this.dialogFormVisible = false
				this.form.cough = ''
				this.form.fever = ''
			},
			reset() { //重置搜索栏
				this.form.cough = ''
				this.form.fever = ''
				this.form.dateValue = ''
				this.load();
			},
			search() {
        //回到第一页
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
        this.form.uid = this.$store.state.currentUser.id
        const rid = this.$store.state.currentUser.rid
        if(this.form.fever.trim() !== ''){
          this.searchConditions.push({
            column: "fever",
            value: this.form.fever.trim(),
            type: "eq",
          })
        }else if(this.form.cough.trim() !== ''){
          this.searchConditions.push({
            column: "cough",
            value: this.form.cough.trim(),
            type: "eq",
          })
        }else if(this.form.dateValue.trim() !== ''){
          this.searchConditions.push({
            column: "time",
            value: this.form.dateValue.trim(),
            type: "gt",
          })
        }

        // 管理员 -> 查询所有用户的打卡记录
        if(Object.is(rid, 1)){
          this.searchConditions = []
        }
        // 辅导员 -> 查询所管理的学生的打卡记录
        if(Object.is(rid, 2)){
          this.searchConditions = []
        }
        // 学生和教职工 -> 查询所管理的学生的打卡记录
        if(Object.is(rid, 3) || Object.is(rid, 4)){
          this.searchConditions.push({
            column: "uid",
            value: this.form.uid,
            type: "eq",
          })
        }
        getRecordsPageByCondition(this.currentPage, this.pageSize, this.searchConditions).then(res => {
					this.total = res.data.totalCount
					this.tableData = res.data.dataList;
          this.searchConditions = []
        })
			}
		},
		created() {
			//根据用户id查询所有
			this.load()
			
			isClock(this.id).then(res => {
				if (res.code === 200) {
					this.isClock = true
				}
			})

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

	.map {
		width: 100%;
		height: 180px;
	}
</style>
