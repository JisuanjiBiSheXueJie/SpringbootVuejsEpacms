<template>
	<body id="paper">
		<el-form :model="registerForm" ref="registerForm" status-icon :rules="rules" class="login-container"
			label-position="left" label-width="0px" v-loading="loading">
			<h3 class="register_title">用户注册</h3>
			<el-form-item prop="username">
				<el-input type="text" prefix-icon="el-icon-user" v-model="registerForm.username"
					auto-complete="off" placeholder="用户名 (3到16位 字母, 中文, 数字, 下划线)"></el-input>
			</el-form-item>
			<el-form-item prop="password">
				<el-input type="password" prefix-icon="el-icon-lock" v-model="registerForm.password" auto-complete="off"
					placeholder="密码 (6-16位)"></el-input>
			</el-form-item>
			<el-form-item prop="repassword">
				<el-input type="password"  prefix-icon="el-icon-lock" v-model="registerForm.repassword" auto-complete="off"
					placeholder="确认密码"></el-input>
			</el-form-item>
			<el-form-item prop="email">
				<el-input type="text" prefix-icon="el-icon-postcard" v-model="registerForm.email" auto-complete="off"
					placeholder="请输入邮箱">
				</el-input>
			</el-form-item>
			<el-form-item style="display: flex;" prop="code" >
				<el-input type="text" style="width: 150px;" v-model="registerForm.code" auto-complete="off" placeholder="邮箱验证码">
				</el-input>
				<el-button @click="getEmailCode()" :disabled="!show || !readySend" style="margin-left: 10px;">
					<span v-show="show">发送验证码</span>
					<span v-show="!show">{{count}}s 后可点击重发</span>
				</el-button>
			</el-form-item>
			<span class="back" @click="goLogin()"><i class="el-icon-d-arrow-left"> 返回</i> </span>

			<el-form-item class="registerForm">
				<el-button type="primary" style="width: 20em;background: #505458;border: none;margin-top: 1em;"
					@click="submitForm('registerForm')">注册
				</el-button>
			</el-form-item>
		</el-form>
	</body>
</template>
<script>
	import {register, isExistUser, sendEmail, isExistEmail} from '@/network/login.js'
	
	const TIME_COUNT = 60 // 设置一个全局的倒计时的时间
	//用户名正则，4到16位（字母，数字，下划线）
	var uPattern = /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{3,16}$/;
	//密码正则
	var pwdPattern = /^[\w_-]{6,16}$/;
	//学号正则
	var posPattern = /^\d{10}$/;
	//手机号正则
	// var mPattern = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8}$/;
	//Email正则
	var emailPattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	
	export default {
		data() {
			//用户名验证
			var usernameValidator = (rule, username, callback) => {
				if (!uPattern.test(username)) {
					return callback(new Error('用户名不符合要求'))
				}
        //判断用户名是否已经注册
        isExistUser(username).then(res => {
          if(res.code !== 200){
            return callback(new Error(res.message));
          }
          callback()
        })
			}
			
			//学号验证
			/* var snoValidator = (rule, sno, callback) => {
				if (posPattern.test(sno)) {
					isExistSno(sno).then(res => {
						if (res.code === 400) {
							return callback(new Error('学号已被存在'))
						} else {
							callback()
						}
					})
				} else {
					return callback(new Error('学号不符合要求'))
				}
			} */
			
			// 邮箱验证
			var emailValidator = (rule, email, callback) => {
				if (!emailPattern.test(email)) {
					return callback(new Error('邮箱不符合要求'))
				}
        isExistEmail(email).then(res => {
          if (res.code !== 200) {
            this.readySend = false
            return callback(new Error(res.message))
          }
          //可以发送邮箱了
          this.isSend = true
          this.readySend = true
          callback()
        })
			}
			
			// 验证码校验
			var validateCheckCode = (rule, value, callback) => {
				if (!value) {
					callback(new Error('验证码不可为空'))
				} else {
					if (value !== '') {
						let reg = /^[0-9]{6}$/
						if (!reg.test(value)) {
							callback(new Error('请输入6位随机验证码'))
						}
					}
					callback()
				}
			}
			
			//密码验证
			let pwdValidator = (rule, password, callback) => {
				if (!pwdPattern.test(password)) {
					return callback(new Error('密码不符合要求'))
				}
        callback()
      }
			
			//重复密码验证
      let rePwdValidator = (rule, value, callback) => {
				if (!Object.is(this.registerForm.password, this.registerForm.repassword)) {
          return callback(new Error('两次密码不一致'))
				}
        callback()
      }

			return {
				count: 60,
				show: true,
				isSend: false,
        readySend: false,
				rules: {
					username: [{
						validator: usernameValidator,
						trigger: 'blur'
					}],
					password: [{
						validator: pwdValidator,
						trigger: 'blur'
					}],
					email: [{
						validator: emailValidator,
						trigger: 'blur'
					}],
					repassword:[{
						validator: rePwdValidator,
						trigger: 'blur'
					}],
					 code: [{ 
						validator: validateCheckCode, 
						trigger: 'blur',
					}]
				},
				checked: true,
				registerForm: {
					username: '',
					password: '',
          repassword: '',
					email: '',
					code: '',
				},
				loading: false
			}
		},
		methods: {
			 getEmailCode () {
					if (!emailPattern.test(this.registerForm.email)) {
            return this.$message({showClose: true, message: '请检查邮箱格式!', type: 'error'})
					}
         // 验证码倒计时
         if (!this.timer) {
           this.count = TIME_COUNT
           this.show = false
           this.timer = setInterval(() => {
             if (this.count > 0 && this.count <= TIME_COUNT) {
               this.count--
             } else {
               this.show = true
               clearInterval(this.timer)
               this.timer = null
             }
           }, 1000)
         }

         //发送email
         sendEmail(this.registerForm.email).then(res => {
           if(res.code !== 200){
             return this.$message.error(res.message)
           }
           this.$message.success('邮箱发送成功')
         })
			},
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (!valid) {
						return false;
					}
          // 验证成功
          register(this.registerForm).then(res => {
            if (res.code !== 200) {
              return this.$message.error(res.message)
            }
            this.$message.success("注册成功!")
            this.$router.replace('/login')
          })
				});
			},
			goLogin() {
				this.$router.replace('/login')
			}
		}
	}
</script>

<style>
	#paper {
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

	.register_title {
		margin: 0px auto 40px auto;
		text-align: center;
		color: #505458;
	}

	.login_remember {
		margin: 0px 0px 35px 0px;
		text-align: left;
	}

	.registerForm {
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.back {
		color: #505458;
		cursor: pointer;
	}

	.back:hover {
		color: lightsalmon;
	}
</style>
