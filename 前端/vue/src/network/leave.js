import request from './request.js'

export function getLeavesByCondition(currentPage, pageSize, searchConditions){
	return request({
		url: '/leave/page' ,
		method: 'POST',
		params:{
			currentPage, pageSize,
		},
		data:{
			conditions: searchConditions
		}
	})
}

// 添加离校申请
export function addOrUpdateLeave(form){
	return request({
		url: '/leave',
		method: 'POST',
		data:{ ...form }
	})
}

// 添加离校申请
export function updateStatus(id,status){
	return request({
		url: '/leave',
		method: 'POST',
		data:{
			id: id,
			status: status
		}
	})
}

// 删除离校申请
export function delLeave(id){
	return request({
		url: '/leave/'+id,
		method: 'DELETE',
	})
}


//批量删除离校申请
export function delLeaveByBatch(ids){
	return request({
		url: '/file/del/batch',
		method: 'DELETE',
		data:{
			ids: ids
		}
	})
}