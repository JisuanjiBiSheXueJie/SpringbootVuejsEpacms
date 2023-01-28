<template>
	<div class="wrapper">
		<el-form :model="loginForm" ref="loginForm" status-icon :rules="rules" class="login-container"
			label-position="left" label-width="0px" v-loading="loading">
			<h3 class="login_title">系统登录</h3>

			<el-form-item prop="username">
				<div style="display:flex;">
					<el-input type="text" prefix-icon="el-icon-user" v-model="loginForm.username" auto-complete="off"
						placeholder="账号"></el-input>
				</div>
			</el-form-item>

			<el-form-item prop="password">
				<el-input type="password" show-password prefix-icon="el-icon-lock" v-model="loginForm.password"
					auto-complete="off" placeholder="密码"></el-input>
			</el-form-item>

			<div style="display: flex;justify-content: space-between;">
				<el-checkbox class="login_remember" v-model="checked" label-position="left">
					<span style="color: #505458;">记住我</span>
				</el-checkbox>
				<router-link to="/register">
					<span class="registerBtn">注册用户<i class="el-icon-d-arrow-right"></i> </span>
				</router-link>
			</div>

			<el-form-item class="loginForm">
				<el-button type="primary" style="width: 20em;background: #505458;border: none"
					@click="loginBtn('loginForm')">登录</el-button>
			</el-form-item>

			<!--滑块验证码部分-->
			<Vcode :imgs="[img1,img2,img3,img4,img5,img6,img7,img8,img9,img10]" :show="isShow" @success="onSuccess" />

		</el-form>
	</div>
</template>
<script>

	import Vcode from "vue-puzzle-vcode"; //页面内引用插件

	//自定义的时候使用了10张图片（我将图片下载到了本地项目内容，所以使用的是路径引用）
	import img1 from '@/assets/img/validateImg/1.jpg';
	import img2 from '@/assets/img/validateImg/2.jpg';
	import img3 from '@/assets/img/validateImg/3.jpg';
	import img4 from '@/assets/img/validateImg/4.jpg';
	import img5 from '@/assets/img/validateImg/5.jpg';
	import img6 from '@/assets/img/validateImg/6.jpg';
	import img7 from '@/assets/img/validateImg/7.jpg';
	import img8 from '@/assets/img/validateImg/8.jpg';
	import img9 from '@/assets/img/validateImg/9.jpg';
	import img10 from '@/assets/img/validateImg/10.jpg';
  import router from "@/router";

	export default {
		name: 'Login',
		//挂载组件
		components: {
			Vcode
		},
		data() {
			//用户名正则，4到16位（字母，数字，下划线）
			var uPattern = /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{3,16}$/;
			//用户名验证
			var usernameValidator = (rule, username, callback) => {
				if (!uPattern.test(username)) {
					return callback(new Error('用户名不符合要求'))
				}
        callback()
      }

			//密码验证
			var pwdValidator = (rule, password, callback) => {
				if (!password.trim()) {
					return callback(new Error('请输入密码'))
				}
        callback()
      }

			return {
				img1, img2, img3, img4, img5,
				img6, img7, img8, img9, img10,
				isShow: false, // 用来控制显示是否显示图片滑动验证框
				rules: {
					username: [{
						validator: usernameValidator,
						trigger: 'blur'
					}],
					password: [{
						validator: pwdValidator,
						trigger: 'blur'
					}]
				},
				checked: true,
				loginForm: {
					username: '',
					password: '',
          email: '',
          code: ''
				},
				loading: false,
			}
		},
		methods: {
			onSuccess() { //用户通过了验证
        // 通过验证后，手动隐藏模态框
				this.isShow = false;
        this.$store.dispatch('Login', this.loginForm).then(res => {
          if(res.code === 200){
            this.$message.success(res.message)
            // 跳转到首页
            let path = this.$route.query.redirect;
            // 跳转页面
            router.replace({
              path: path === '/' || path === undefined ? '/' : path
            }).then()
          }else{
            this.$message.error(res.message)
          }
        })
			},
			loginBtn(formName) {
				this.$refs[formName].validate((valid) => {
					if (!valid) {
						return false;
					}
          // 通过验证后，手动隐藏模态框
          this.isShow = true
        });
			}
		}
	}
</script>
<style>
	.wrapper {
		background: url("../../assets/img/bg/yq.jpg") no-repeat;
		background-position: center;
		height: 100%;
		width: 100%;
		background-size: cover;
		position: fixed;
	}

	.login-container {
		border-radius: 15px;
		background-clip: padding-box;
		margin: 90px auto;
		width: 400px;
		padding: 35px 35px 15px 35px;
		background: #fff;
		border: 1px solid #eaeaea;
		box-shadow: 0 0 20px #959393;
	}

	.login_title {
		margin: 0px auto 40px auto;
		text-align: center;
		color: #505458;
	}

	.login_remember {
		margin: 0px 0px 35px 0px;
		text-align: left;
	}

	.el-form-item a {
		margin-left: 20px;
	}

	.loginForm {
		display: flex;
		align-items: center;
		justify-content: center;
	}

	a {
		text-decoration: none;
		cursor: auto;
	}

	.registerBtn {
		color: #505458;
		cursor: pointer;
	}

	.registerBtn:hover {
		color: lightsalmon;
	}
</style>
