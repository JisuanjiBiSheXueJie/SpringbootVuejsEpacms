//修改默认配置
module.exports = {
	lintOnSave: false, //关闭语法检查
	devServer: {
		host: 'localhost',
		port: 8080,
		https: false,
		proxy: {
			'/api':{
				target: 'http://localhost:8888/api',// 代理目标的基础路径
				changeOrigin: true,
				pathRewrite:{
					'^/api':''
				}
			}
		}
	}
}
