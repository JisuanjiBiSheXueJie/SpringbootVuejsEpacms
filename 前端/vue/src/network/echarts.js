import request from './request.js'

export function getData(){
	return request({
		url: '/echarts/members',
		method: 'GET'
	})
}

// 统计数据
export function getCounts(){
	return request({
		url: '/statistics',
		method: 'GET'
	})
}

// 查询每月的打卡数和离校数
export function getMonthRecordAndLeave(){
	return request({
		url: '/statistics/month/recordAndLeave',
		method: 'GET'
	})
}

//今日打卡情况
export function getRecordToday(){
	return request({
		url: '/today/record',
		method: 'GET'
	})
}