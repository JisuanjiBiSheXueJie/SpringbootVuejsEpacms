import request  from "./request.js"

/* 分页查询文件 */
export function getFilesByPage(currentPage, pageSize, searchConditions){
	return request({
		url: '/upload/page',
		method: 'POST',
		params:{
			currentPage,
			pageSize
		},
		data: {
			conditions: searchConditions
		}
	})
}

export function updateEnabled(files){
	return request({
		url: '/upload/update',
		method: 'POST',
		data:{
			id: files.id,
			enabled: files.enabled
		}
	})
}

// 删除文件
export function delFileById(id){
	return request({
		url: '/upload',
		method: 'DELETE',
		params: {
			id
		}
	})
}

//批量删除文件
export function delFileByBatch(idsStr){
	return request({
		url: '/upload/batch',
		method: 'DELETE',
		params: {
			idsStr
		}
	})
}