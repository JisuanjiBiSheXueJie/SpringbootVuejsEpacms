import request from './request.js'

// 获取所有新闻
export function getAllNews(){
	return request({
		url: '/news',
		method: 'GET'
	})
}

// 分页查询
export function getNewsByPage(currentPage, pageSize, searchConditions){
	return request({
		url: '/news/page',
		method: 'POST',
		params: {
			currentPage,
			pageSize
		},
		data:{
			conditions: searchConditions
		}
	})
}

// 批量删除新闻
export function delNewsByBatch(idsStr){
	return request({
		url: '/news/batch',
		method: 'DELETE',
		params: { idsStr }
	})
}

// 删除新闻
export function deleteNews(id){
	return request({
		url: '/news',
		method: 'DELETE',
		params: { id }
	})
}

// 添加新闻
export function addNews(form){
	return request({
		url: '/news',
		method: 'POST',
		data:{
			title: form.newsTitle,
			url: form.newsURL
		}
	})
}

// 爬取新闻，批量添加新闻
export function getBatchNewsByUrl(currentPage, pageSize, url, cssQuery){
	return request({
		url: '/news/crawl',
		method: 'GET',
		params: { currentPage, pageSize, url, cssQuery }
	})
}

export function addBatchNews(newsList){
	return request({
		url: '/news/batch',
		method: 'POST',
		data: { newsList }
	})
}