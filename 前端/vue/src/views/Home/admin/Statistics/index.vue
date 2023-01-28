<template>
	<div>
		<!-- 数据概览 -->
		<el-row :span="10" style="margin-bottom: 40px;" :gutter="10">
			<el-col :span="6">
				<el-card class="userCard">
					<div class="showTotal">
						<i class="el-icon-user"></i>
						用户总数
					</div>
					<div class="showTotalData">
						{{counts.userTotal}}
					</div>
				</el-card>
			</el-col>
			<el-col :span="6">
				<el-card class="recordCard">
					<div class="showTotal">
						<i class="el-icon-date"></i>
						打卡总数
					</div>
					<div class="showTotalData">
						{{counts.recordTotal}}
					</div>
				</el-card>
			</el-col>
			<el-col :span="6">
				<el-card class="fileCard">
					<div class="showTotal">
						<i class="el-icon-files"></i>
						文件总数
					</div>
					<div class="showTotalData">
						{{counts.fileTotal}}
					</div>
				</el-card>
			</el-col>
			<el-col :span="6">
				<el-card class="newsCard">
					<div class="showTotal">
						<i class="el-icon-news"></i>
						新闻总数
					</div>
					<div class="showTotalData">
						{{counts.newsTotal}}
					</div>
				</el-card>
			</el-col>
		</el-row>

		<el-row style="margin-top: 20px;">
			
			<el-col :span="8" class="charts">
				<div id="pie" style="width: 400px;height: 260px;"></div >
			</el-col>
			
			<el-col :span="8" class="charts" style="position: relative;">
				<div style="position: absolute;bottom: 10px;left: 40px;z-index: 100;display: flex;align-items: center;" >
					<span style="width: 80px;">地区：</span>
					<el-input v-model="positionName"></el-input>
					<el-button style="margin-left: 5px;" @click="positionBtn">确定</el-button>
				</div>
				<div>
					<div id="pie2" style="width: 400px;height: 260px;"></div>
				</div>
			</el-col>
			
			<el-col :span="8" class="charts">
				<div id="pie3" style="width: 400px;height: 260px;"></div>
			</el-col>
		</el-row>
		
		 <el-divider></el-divider>
	
		<el-table :data="StudentTable.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))"
			style="width: 100%">
			<el-table-column align="center" label="用户ID" prop="id" width="120"></el-table-column>
			<el-table-column align="center" label="学号" prop="sno"></el-table-column>
			<el-table-column align="center" label="用户名" prop="username"></el-table-column>
			<el-table-column align="center" label="真实姓名" prop="realName"></el-table-column>
			<el-table-column align="center" label="班级" prop="classNum"></el-table-column>
			<el-table-column align="center" label="电话号码" prop="phone"></el-table-column>
			<el-table-column align="center" label="电子邮箱" prop="email"></el-table-column>
			<el-table-column align="center">
				<template slot="header" slot-scope="scope">
					<el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
				</template>
				<template slot-scope="scope">
					<el-popconfirm confirm-button-text='确定' cancel-button-text='点错了' icon="el-icon-info"
						icon-color="red" title="确定发送邮件吗？" @confirm="noticeHe(scope.row.email)">
						<el-button size="mini" type="warning" slot="reference">
							提醒他
						</el-button>
					</el-popconfirm>
				</template>
			</el-table-column>
		</el-table>

	</div>
</template>

<script>
	import * as echarts from 'echarts';
	import {
		getCounts,
		getRecordToday
	} from '@/network/echarts.js'
	import {sendEmail} from '@/network/health.js'
	import {
		getAllNoClockStudent,
		getPositionToday,
		getHealthStudentToday,
		getAllNoHealthStudentToday
	} from '@/network/user.js'

	// 时间格式化
	let getToday = function() {
				let today = '';
				let date = new Date()
				today = date.getFullYear() + '-' + (date.getMonth()+1) + '-' + date.getDate()
				return today;
			}

			
	export default {
		name: 'Statistics',
		data() {
			return {
				positionName: '益阳市',
				search: '',
				// 所有未打卡学生数组
				StudentTable: [],
				counts: {
					userTotal: 0,
					recordTotal: 0,
					fileTotal: 0,
					newsTotal: 0
				}
			}
		},
		methods: {
			//------------------------------ 图一 ------------------------------
			positionBtn(){
				var pieChart = echarts.init(document.getElementById('pie2'));
							var pieOption;
							
							pieOption = {
								color: ['#ffa07a', '#20b2aa'],
								title: {
									text: '今日打卡位置情况',
									subtext: '时间：' + getToday(),
									left: 'center'
								},
								tooltip: {
									trigger: 'item',
									formatter: '{a} <br/>{b} : {c} ({d}%)'
								},
								legend: {
									orient: 'vertical',
									left: 'left'
								},
								series: [{
									name: '人数',
									type: 'pie',
									radius: '50%',
									data: [],
									emphasis: {
										itemStyle: {
											shadowBlur: 10,
											shadowOffsetX: 0,
											shadowColor: 'rgba(0, 0, 0, 0.5)'
										}
									}
								}]
							};
				// 今日已打卡学生位置情况
				getPositionToday(this.positionName).then(res => {
					if (res.code === 200) {
						pieOption.series[0].data = [{
								name: this.positionName+"内",
								value: res.result[0]
							},
							{
								name: this.positionName+"外",
								value: res.result[1]
							},
						]
						pieOption && pieChart.setOption(pieOption);
					}
				})
			},
			noticeHe(email) {
				sendEmail(email).then(res => {
					this.$message.success('通知成功')
				})
			}
		},
		mounted() {
			//------------------------------ 图一 ------------------------------
						this.positionBtn();
			//------------------------------ 图二 ------------------------------

			var pie2Chart = echarts.init(document.getElementById('pie'));
			var pie2Option;

			pie2Option = {
				color: ['#5faee6', '#f08080'],
				title: {
					text: '今日打卡情况',
					subtext: '时间：' + getToday(),
					left: 'center'
				},
				tooltip: {
					trigger: 'item',
					formatter: '{a} <br/>{b} : {c} ({d}%)'
				},
				legend: {
					orient: 'vertical',
					left: 'left'
				},
				series: [{
					name: '人数',
					type: 'pie',
					radius: '50%',
					data: [],
					emphasis: {
						itemStyle: {
							shadowBlur: 10,
							shadowOffsetX: 0,
							shadowColor: 'rgba(0, 0, 0, 0.5)'
						}
					}
				}]
			};

			//今日打卡记录
			getRecordToday().then(res => {
				if (res.code === 200) {
					pie2Option.series[0].data = [{
							name: "已打卡",
							value: res.result[0]
						},
						{
							name: "未打卡",
							value: res.result[1]
						},
					]
					pie2Option && pie2Chart.setOption(pie2Option);
				}
			})

			// 点击事件
			pie2Chart.on('click', (params) => {
				if (params.name == '未打卡') {
					getAllNoClockStudent().then(res => {
						if (res.code === 200) {
							this.StudentTable = res.result
						}
					})
				}
			})
			
			
			//------------------------------ 图三 ------------------------------
			
			var pie3Chart = echarts.init(document.getElementById('pie3'));
			var pie3Option;
			
			pie3Option = {
				color: ['#3ba272', '#ffb6c1'],
				title: {
					text: '今日身体健康情况',
					subtext: '时间：' + getToday(),
					left: 'center'
				},
				tooltip: {
					trigger: 'item',
					formatter: '{a} <br/>{b} : {c} ({d}%)'
				},
				legend: {
					orient: 'vertical',
					left: 'left'
				},
				series: [{
					name: '人数',
					type: 'pie',
					radius: '50%',
					data: [],
					emphasis: {
						itemStyle: {
							shadowBlur: 10,
							shadowOffsetX: 0,
							shadowColor: 'rgba(0, 0, 0, 0.5)'
						}
					}
				}]
			};
			
			//今日打卡记录
			getHealthStudentToday().then(res => {
				if (res.code === 200) {
					pie3Option.series[0].data = [{
							name: "正常",
							value: res.result[0]
						},
						{
							name: "异常",
							value: res.result[1]
						},
					]
					pie3Option && pie3Chart.setOption(pie3Option);
				}
			})
			
			// 点击事件
			pie3Chart.on('click', (params) => {
				if (params.name == '异常') {
					getAllNoHealthStudentToday().then(res => {
						if (res.code === 200) {
							this.StudentTable = res.result
						}
					})
				}
			})
			
			//总数信息
			getCounts().then(res => {
        Object.assign(this.counts, res.data)
			})
		}
	}
</script>

<style>
	.showTotal {
		color: black;
		font-size: 24px;
		font-weight: bold;
		font-family: 华文楷体;
	}

	.showTotalData {
		font-size: 30px;
		padding: 10px 0;
		text-align: center;
		font-weight: bold;
	}

	.userCard {
		background: lightcoral;
		background-image: repeating-linear-gradient(30deg, hsla(0, 0%, 100%, .1), hsla(0, 0%, 100%, .1) 15px, transparent 0, transparent 30px);
	}

	.recordCard {
		background: lightsalmon;
		background-image: repeating-linear-gradient(30deg, hsla(0, 0%, 100%, .1), hsla(0, 0%, 100%, .1) 15px, transparent 0, transparent 30px);
	}

	.fileCard {
		background: lightpink;
		background-image: repeating-linear-gradient(30deg, hsla(0, 0%, 100%, .1), hsla(0, 0%, 100%, .1) 15px, transparent 0, transparent 30px);
	}

	.newsCard {
		background: lightseagreen;
		background-image: repeating-linear-gradient(30deg, hsla(0, 0%, 100%, .1), hsla(0, 0%, 100%, .1) 15px, transparent 0, transparent 30px);
	}

	.charts {
		display: flex;
		align-items: center;
		justify-content: center;
		box-shadow: 0 0 15px 1px rgba(0, 0, 0, 0.1);
		padding: 20px;
	}
</style>
