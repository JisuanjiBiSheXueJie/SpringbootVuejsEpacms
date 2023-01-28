<template>
	<div style="width: 100%;height: 100%;">
		<!-- 数据概览 -->
		<el-row style="margin-bottom: 60px;" :gutter="10">
			<el-col :span="4">
				<el-card class="gntotalCard" style="border-radius: 15px;">
					<div class="showData">{{epidemicData.gntotal}}</div>
					<div class="showText">累计确诊</div>
				</el-card>
			</el-col>
			<el-col :span="4">
				<el-card  class="econCard" style="border-radius: 15px;">
					<div class="showData">{{epidemicData.econNum}}</div>
					<div class="showText">现存确诊</div>
				</el-card>
			</el-col>
			<el-col :span="4">
				<el-card  class="jwsrCard" style="border-radius: 15px;">
					<div class="showData">{{epidemicData.jwsrNum}}</div>
					<div class="showText">境外输入</div>
				</el-card>
			</el-col>
			<el-col :span="4">
				<el-card class="sustotalCard" style="border-radius: 15px;">
					<div class="showData">{{epidemicData.sustotal}}</div>
					<div class="showText">现存疑似</div>
				</el-card>
			</el-col>
			<el-col :span="4">
				<el-card class="curetotalCard" style="border-radius: 15px;">
					<div class="showData">{{epidemicData.curetotal}}</div>
					<div class="showText">累计治愈</div>
				</el-card>
			</el-col>
			<el-col :span="4">
				<el-card class="deathtotalCard" style="border-radius: 15px;">
					<div class="showData">{{epidemicData.deathtotal}}</div>
					<div class="showText">累计死亡</div>
				</el-card>
			</el-col>
		</el-row>
		<el-row >
			<el-col :span="12">
				<div align="center" ref="mapbox" style="width: 550px;height: 450px;"></div>
			</el-col>
			<el-col :span="8">
				<div align="center" ref="jwsrTop" style="width: 550px;height: 450px;"></div>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import echarts from 'echarts';
	//使用地图 需要先注册地图
	import 'echarts/map/js/china.js'
	import jsonp from 'jsonp'
	
	const chinaMapOptions = {
		title: {
			text: '各省疫情数据',
			subtext: ''
		},
		series:[
			{
				type: 'map', //类型：地图
				name: '确诊人数',
				map: 'china', //渲染中国地图
				label: {
					show: true, 
					color: '#fff',
					fontSize: 10 //字体大小
				},
				roam: true,
				zoom: 1.2,//地图大小
				emphasis: {
					//鼠标停留处的颜色
					label:{
						color: '#000',//字体颜色
						fontSize: 12
					},
					itemStyle: {
						areaColor: 'lightblue',//板块颜色
					}
				},
				//数据
				data:[]
			}
		],
		visualMap: [{
			type: 'piecewise',
			show: true,
			// splitNumber: 5,
			pieces:[//分段
				{min: 5000, color: '#95002f'},
				{min: 1000, max: 4999, color: '#bd003b'},
				{min: 500, max: 999, color: '#e03355'},
				{min: 100, max: 499, color: '#ff6f6f'},
				{min: 10, max: 99, color: '#ffbaba'},
				{min: 1, max: 9, color: '#ffe8e8'}
			],
			// align: 'right',//默认左边
		}],
		tooltip:{
			trigger: 'item'
		},
		toolbox: {
			show: true,
			orient: 'vertical',
			left: 'right',
			feature: {
				dataView: {readOnly: false},
				saveAsImage: {}
			}
		}
	}
	const jwsrTopOptions = {
		series: [
		  {
		    type: 'bar',
		    data: []
		  }
		],
	  title: {
	    text: '境外输入确诊市TOP10'
	  },
	  tooltip: {
	    trigger: 'axis',
	    axisPointer: {
	      type: 'shadow'
	    }
	  },
	  legend: {},
	  grid: {
	    left: '3%',
	    right: '4%',
	    bottom: '3%',
	    containLabel: true
	  },
	  xAxis: {
	    type: 'value',
	    boundaryGap: [0, 0.01]
	  },
	  yAxis: {
	    type: 'category',
	    data: []
	  }
	};

	export default {
		name: 'Epidemic',
		data(){
			return {
				epidemicData:{
					gntotal: 0, //累计确诊
					curetotal: 0, //累计治愈
					sustotal: 0, //现存疑似
					deathtotal: 0, //累计死亡
					econNum: 0, //现存确诊
					jwsrNum: 0, //境外输入
				}
			}
		},
		methods:{
			getData(){
				jsonp('https://interface.sina.cn/news/wap/fymap2020_data.d.json',{},(err,data)=>{
					if(!err){
						this.epidemicData.gntotal = data.data.gntotal
						this.epidemicData.curetotal = data.data.curetotal
						this.epidemicData.sustotal = data.data.sustotal
						this.epidemicData.deathtotal = data.data.deathtotal
						this.epidemicData.econNum = data.data.econNum
						this.epidemicData.jwsrNum = data.data.jwsrNum
						chinaMapOptions.title.subtext = data.data.times
						//代表请求成功
						let list = data.data.list.map(item=>({name:item.name,value:item.value}))
						chinaMapOptions.series[0].data = list;
						this.chinaMapCharts.setOption(chinaMapOptions)
						
						let jwsrNumList = data.data.jwsrTop.map(item => item.jwsrNum)
						jwsrTopOptions.series[0].data = jwsrNumList
						let jwsrNameList = data.data.jwsrTop.map(item => item.name)
						jwsrTopOptions.yAxis.data = jwsrNameList
						this.jwsrTopCharts.setOption(jwsrTopOptions)
					}
				})
			}
		},
		mounted() {
			this.getData()//获取数据
			this.chinaMapCharts = echarts.init(this.$refs.mapbox)
			this.chinaMapCharts.setOption(chinaMapOptions)
			
			this.jwsrTopCharts = echarts.init(this.$refs.jwsrTop)
			this.jwsrTopCharts.setOption(jwsrTopOptions)
		}
	}
</script>

<style>
	.showData{
		text-align: center; 
		font-weight:
		bold;font-size: 38px;
	}
	.showText{
		font-size: 18px;
		font-weight: bold;
		font-family: '华文楷体';
		text-align: center;
	}
	
	.gntotalCard{
		background: lightcoral;
		background-image: repeating-linear-gradient(30deg, hsla(0, 0%, 100%, .1), hsla(0, 0%, 100%, .1) 15px, transparent 0, transparent 30px);
	}
	.curetotalCard{
		background: lightseagreen;
		background-image: repeating-linear-gradient(30deg, hsla(0, 0%, 100%, .1), hsla(0, 0%, 100%, .1) 15px, transparent 0, transparent 30px);
	}
	.sustotalCard{
		background: lightpink;
		background-image: repeating-linear-gradient(30deg, hsla(0, 0%, 100%, .1), hsla(0, 0%, 100%, .1) 15px, transparent 0, transparent 30px);
	}
	.deathtotalCard{
		background: lightslategray;
		background-image: repeating-linear-gradient(30deg, hsla(0, 0%, 100%, .1), hsla(0, 0%, 100%, .1) 15px, transparent 0, transparent 30px);
	}
	.econCard{
		background: lightsalmon ;
		background-image: repeating-linear-gradient(30deg, hsla(0, 0%, 100%, .1), hsla(0, 0%, 100%, .1) 15px, transparent 0, transparent 30px);
	}
	.jwsrCard{
		background: lightskyblue;
		background-image: repeating-linear-gradient(30deg, hsla(0, 0%, 100%, .1), hsla(0, 0%, 100%, .1) 15px, transparent 0, transparent 30px);
	}
</style>
