<template>
	<div>
		<!-- 第一行 -->
		<el-row>
			<el-col :span="15">
				<el-card>
					<!-- 走马灯 -->
					<el-carousel :interval="8000" arrow="always" height="350px">
						<el-carousel-item v-for="(item,index) in imgWrap" :key="index">
						 <img :src="item.url" class="bannerimg"></img>
						</el-carousel-item>
					</el-carousel>
				</el-card>
			</el-col>
			<!-- 新闻板块 -->
			<el-col :span="8" style="margin-left: 20px;">
				<el-card>
					<span style="font-size: 20px;font-weight: bold;font-family: 华文楷体;color: lightseagreen;">国内近期疫情新闻</span>
					<el-divider></el-divider>
					<el-table
						:data="epidemicData"
						height="300"
						style="width: 100%">
						<el-table-column show-overflow-tooltip  label="标题">
							<template slot-scope="scope">
								<el-link :href="scope.row.url" target="_blank" :underline="false">{{scope.row.title}}</el-link>
							</template>
						</el-table-column>
						
						 <el-table-column align="center" label="跳转" width="80">
						      <template slot-scope="scope">
						        <el-button
						          size="mini"
											icon="el-icon-link"
						          @click="jumpPage(scope.row.url)"> </el-button>
						      </template>
						    </el-table-column>
					</el-table>
				</el-card>
			</el-col>
		</el-row>
		
		<!-- 第二行 -->
		<el-row style="margin-top: 20px;">
			<el-col :span="15">
				<el-card style="height: 390px;" ref="record" id="record">
					<!-- 图表 -->
				</el-card>
			</el-col>
			
			<el-col :span="8" style="margin-left: 20px;">
				<el-card>
					<span style="font-size: 20px;font-weight: bold;font-family: 华文楷体;color: lightseagreen;">校内近期发布通知</span>
					<el-divider></el-divider>
					<el-table
						:data="noticeData"
						height="300"
						style="width: 100%">
						<el-table-column show-overflow-tooltip prop="title" label="标题"></el-table-column>
						 <el-table-column align="center" label="跳转" width="80">
						      <template slot-scope="scope">
						        <el-button
						          size="mini"
											icon="el-icon-link"
						          @click="goNotice(scope.row.id)"> </el-button>
						      </template>
						    </el-table-column>
					</el-table>
				</el-card>
			</el-col>
		</el-row>
		
	</div>
</template>

<script>
	import echarts from 'echarts';
	import {getNewsByPage} from '@/network/news.js'
	import {getMonthRecordAndLeave} from '@/network/echarts.js'
	import {getAllDetail} from '@/network/notice.js'
	
	const colors = ['#5470C6', '#EE6666'];
	const option = {
	  color: colors,
	  tooltip: {
	    trigger: 'none',
	    axisPointer: {
	      type: 'cross'
	    }
	  },
	  legend: {},
	  grid: {
	    top: 70,
	    bottom: 50
	  },
	  xAxis: [
	    {
	      type: 'category',
	      axisTick: {
	        alignWithLabel: true
	      },
	      axisLine: {
	        onZero: false,
	        lineStyle: {
	          color: colors[1]
	        }
	      },
	      axisPointer: {
	        label: {
	          formatter: function (params) {
	            return (
	              '离校数  ' +
	              params.value +
	              (params.seriesData.length ? '：' + params.seriesData[0].data : '')
	            );
	          }
	        }
	      },
	      // prettier-ignore
	      data: ['2022-01','2022-02','2022-03','2022-04','2022-05','2022-06','2022-07','2022-08','2022-09','2022-10','2022-11','2022-12']
	    },
	    {
	      type: 'category',
	      axisTick: {
	        alignWithLabel: true
	      },
	      axisLine: {
	        onZero: false,
	        lineStyle: {
	          color: colors[0]
	        }
	      },
	      axisPointer: {
	        label: {
	          formatter: function (params) {
	            return (
	              '打卡数  ' +
	              params.value +
	              (params.seriesData.length ? '：' + params.seriesData[0].data : '')
	            );
	          }
	        }
	      },
	      // prettier-ignore
	      data: ['2022-01','2022-02','2022-03','2022-04','2022-05','2022-06','2022-07','2022-08','2022-09','2022-10','2022-11','2022-12']
	    }
	  ],
	  yAxis: [
	    {
	      type: 'value'
	    }
	  ],
	  series: [
	    {
	      name: '打卡趋势图(2022)',
	      type: 'line',
	      xAxisIndex: 1,
	      smooth: true,
	      emphasis: {
	        focus: 'series'
	      },
	      data: []
	    },
	    {
	      name: '离校趋势图(2022)',
	      type: 'line',
	      smooth: true,
	      emphasis: {
	        focus: 'series'
	      },
	      data: []
	    }
	  ]
	};
	
	export default {
		name: 'Home',
		components:{
		},
		data(){
			return {
				   epidemicData: [],
					 noticeData: [],
					 imgWrap: [
						 {url: require("@/assets/img/validateImg/1.jpg")},
						 {url: require("@/assets/img/validateImg/2.jpg")},
						 {url: require("@/assets/img/validateImg/3.jpg")},
						 {url: require("@/assets/img/validateImg/4.jpg")},
						 {url: require("@/assets/img/validateImg/5.jpg")},
						 {url: require("@/assets/img/validateImg/6.jpg")}
					 ]
			}
		},
    methods: {
			jumpPage(url){
				window.open(url)
			},
			goNotice(id){
				this.$router.push('/noticeDetail?id='+id)
			}
    },
		mounted() {
			let dom = document.querySelector('#record')
			let chart = echarts.init(dom)
      getMonthRecordAndLeave().then(res => {
        option.xAxis[0].data = res.data.records.map(item => item.date);
				// 每月的打卡数
				option.series[0].data = res.data.records.map(item => item.total)
        // 每月的离校数
        option.xAxis[1].data = res.data.leaves.map(item => item.date);
        option.series[1].data = res.data.leaves.map(item => item.total);
        option && chart.setOption(option);
      })
    },
		created() {
      // TODO 分页查询新闻
      getNewsByPage(1, 15, []).then(res => {
        this.epidemicData = res.data.dataList
      })
			getAllDetail().then(res=>{
        this.noticeData = res.data.dataList
			})
		}
	}
</script>

<style>
	   .el-carousel__item h3 {
	     color: #475669;
	     font-size: 14px;
	     opacity: 0.75;
	     line-height: 200px;
	     margin: 0;
	   }
	   
	   .el-carousel__item:nth-child(2n) {
	     background-color: #99a9bf;
	   }
	   
	   .el-carousel__item:nth-child(2n+1) {
	     background-color: #d3dce6;
	   }
		 
		 .el-divider--horizontal {
		   margin: 12px 0;
			 color: black;
		 }
		 
		 .bannerimg{
		   width: 100%;
		   height: inherit;
		 }
</style>