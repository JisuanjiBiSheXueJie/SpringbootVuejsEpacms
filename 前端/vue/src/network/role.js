import request  from "./request.js"

/* 分页查询用户 */
export function getRoles(){
	return request({
		url: '/role',
		method: 'GET',
	})
}

//通过rid查询角色
export function getRoleByUid(rid){
	return request({
		url: '/role/'+rid,
		method: 'GET'
	})
}

//通过rid查询角色
export function getMenuByRid(roleId){
	return request({
		url: '/permission',
		method: 'GET',
		params: {
			roleId
		}
	})
}
