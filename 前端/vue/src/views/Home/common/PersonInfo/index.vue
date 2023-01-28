<template>
	<div>
		<el-row>
			<!-- 第一列 -->
			<el-col :span="12">
				<!-- 表单 -->
				<el-card style="border-radius: 10px;">
					<span style="font-size: 18px;font-weight: bold;font-family: 宋体;">修改个人信息</span>
					<el-divider></el-divider>
					<el-form style="font-weight: bold;" size="mini" label-position="left" label-width="80px" :model="form">

						<!-- 头像上传 -->
						<el-upload align="center" class="avatar-uploader" action="http://localhost:8888/api/upload"
							:show-file-list="false" :on-success="handleAvatarSuccess"
							:before-upload="beforeAvatarUpload">
							<img v-if="form.avatarUrl" :src="form.avatarUrl" class="avatar">
							<i v-else class="el-icon-plus avatar-uploader-icon"></i>
						</el-upload>
						<el-form-item v-if="rid === 3" label="学号">
							<el-input v-model.number="form.sno"></el-input>
						</el-form-item>
						<el-form-item label="用户名">
							<el-input v-model="form.username" disabled></el-input>
						</el-form-item>
						<el-form-item label="角色">
							<el-input :value="roleName" disabled></el-input>
						</el-form-item>
						<el-form-item label="学院" v-if="rid === 3">
							<el-select v-model="form.academy" placeholder="请选择">
								<el-option v-for="academy in academies" :key="academy" :label="academy" :value="academy">
								</el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="真实姓名">
							<el-input v-model="form.realName"></el-input>
						</el-form-item>
						<el-form-item label="性别">
							<el-radio v-model="form.sex" label="男">男</el-radio>
							<el-radio v-model="form.sex" label="女">女</el-radio>
						</el-form-item>
						<el-form-item label="年龄">
							<el-input-number v-model="form.age" :min="1" :max="120"></el-input-number>
						</el-form-item>
						<el-form-item label="民族">
							<el-input v-model="form.nation"></el-input>
						</el-form-item>
						<el-form-item label="班级" v-if="rid === 3">
							<el-select v-model="form.classNum" placeholder="请选择">
								<el-option v-for="clazz in classes" :key="clazz.id" :label="clazz.classNum" :value="clazz.classNum">
								</el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="宿舍" v-if="rid === 3">
							<el-input v-model="form.dormitoryNum"></el-input>
						</el-form-item>
						<el-form-item label="邮箱">
							<el-input disabled v-model="form.email"></el-input>
						</el-form-item>
						<el-form-item label="联系电话">
							<el-input v-model="form.phone"></el-input>
						</el-form-item>
						<el-form-item label="详细地址">
							<el-input type="textarea" placeholder="请输入详细地址" v-model="form.address" maxlength="120"
								show-word-limit>
							</el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="onSubmit" class="updateBtn">更新信息</el-button>
						</el-form-item>
					</el-form>
				</el-card>
			</el-col>

			<!-- 第二列 -->
			<el-col :span="10" :push="1" style="border-radius: 10px;">
				<!-- 基本信息预览 -->
				<el-card class="baseInfoCard">
					<span style="font-size: 18px;font-weight: bold;font-family: 宋体;">个人信息预览</span>
					<el-divider></el-divider>

					<!-- 描述信息 -->
					<el-descriptions size="mini" class="margin-top" :column="1" border :contentStyle="classObj">
						<el-descriptions-item v-if="rid === 3" >
							<template slot="label"> <i class="el-icon-key"></i> 学号 </template>
							{{form.sno}}
						</el-descriptions-item>
						<el-descriptions-item>
							<template slot="label"> <i class="el-icon-c-scale-to-original"></i> 用户名 </template>
							{{form.username}}
						</el-descriptions-item>
						<el-descriptions-item>
							<template slot="label"> <i class="el-icon-user"></i> 真实姓名 </template>
							{{form.realName}}
						</el-descriptions-item>
						<el-descriptions-item>
							<template slot="label"> <i class="el-icon-c-scale-to-original"></i> 角色 </template>
							{{roleName}}
						</el-descriptions-item>
						<el-descriptions-item>
							<template slot="label"><i class="el-icon-male"></i> 性别 </template>
							{{form.sex}}
						</el-descriptions-item>
						<el-descriptions-item>
							<template slot="label"> <i class="el-icon-orange"></i> 年龄 </template>
							{{form.age}}
						</el-descriptions-item>

						<el-descriptions-item>
							<template slot="label"> <i class="el-icon-medal"></i> 民族 </template>
							{{form.nation}}
						</el-descriptions-item>

						<el-descriptions-item v-if="rid === 3">
							<template slot="label"> <i class="el-icon-goblet-square"></i> 学院 </template>
							{{form.academy}}
						</el-descriptions-item>

						<el-descriptions-item v-if="rid === 3">
							<template slot="label"> <i class="el-icon-wind-power"></i> 班级 </template>
							{{form.classNum}}
						</el-descriptions-item>

						<el-descriptions-item v-if="rid === 3">
							<template slot="label"> <i class="el-icon-house"></i> 宿舍 </template>
							{{form.dormitoryNum}}
						</el-descriptions-item>

						<el-descriptions-item>
							<template slot="label"> <i class="el-icon-chat-dot-square"></i> 邮箱 </template>
							{{form.email}}
						</el-descriptions-item>

						<el-descriptions-item>
							<template slot="label"> <i class="el-icon-mobile-phone"></i> 手机号 </template>
							{{form.phone}}
						</el-descriptions-item>

						<el-descriptions-item>
							<template slot="label"> <i class="el-icon-map-location"></i> 详细地址 </template>
							{{form.address}}
						</el-descriptions-item>
					</el-descriptions>

				</el-card>
        
				<!-- 选择辅导员 -->
				<!-- <el-card style="margin-top: 20px;" v-if="rid === 3">
					<span style="font-size: 18px;font-weight: bold;font-family: 宋体;">选择辅导员</span>
					<el-divider></el-divider>
					<el-form status-icon label-width="100px" :disabled="requestInstructorId !== ''">
						<el-form-item label="辅导员" prop="repassword">
							<el-select v-model="instructorId" placeholder="请选择" >
								<el-option v-for="instructor in allInstructor" :key="instructor.id" :label="instructor.username"
									:value="instructor.id">
								</el-option>
							</el-select>
							<el-button type="primary" size="medium" style="margin-left: 10px;" @click="confirmInstructor()">确定</el-button>
						</el-form-item>
					</el-form>
				</el-card> -->
				
				<!-- 修改密码 -->
				<el-card style="margin-top: 38px;">
					<span style="font-size: 18px;font-weight: bold;font-family: 宋体;">修改密码</span>
					<el-divider></el-divider>
					<el-form :model="ruleForm" size="mini" status-icon :rules="rules" ref="ruleForm" label-width="100px"
						class="demo-ruleForm">
						<el-form-item label="密码" prop="pass">
						 <el-input type="password" prefix-icon="el-icon-lock" v-model="ruleForm.pass"
								autocomplete="off" placeholder="密码 (6-16位)"></el-input>
						</el-form-item>
						<el-form-item label="确认密码" prop="repassword">
							<el-input type="password" prefix-icon="el-icon-lock" v-model="ruleForm.repassword"
								autocomplete="off" placeholder="密码 (6-16位)"></el-input>
						</el-form-item>
						<el-form-item>
							<el-button size="mini" type="primary" @click="submitForm('ruleForm')">提交</el-button>
							<el-button size="mini" @click="resetForm('ruleForm')">重置</el-button>
						</el-form-item>
					</el-form>
				</el-card>
			</el-col>

		</el-row>
	</div>
</template>

<script>
	import {
    getCurrentUserByUsername,
		updateUser,
		changePassword,
		getAllInstructor,
		getInstructorByStudentId,
		addInstructorIdAndStudentId,
		getAllClass
	} from '@/network/user.js'

	export default {
		name: 'PersonInfo',
		data() {
			//密码正则
			let pwdPattern = /^[\w_-]{6,16}$/;
			//密码验证
      let pwdValidator = (rule, password, callback) => {
				if (!pwdPattern.test(password)) {
					return callback(new Error('密码不符合要求'))
				}
        callback()
      }

			//重复密码验证
      let rePwdValidator = (rule, value, callback) => {
				if (!Object.is(this.ruleForm.pass, this.ruleForm.repassword)) {
					return callback(new Error('两次密码不一致'))
				}
        callback()
      }

			return {
				rid: this.$store.getters.currentUser.rid,
				ruleForm: {
					pass: '',
					repassword: ''
				},
				rules: {
					pass: [{
						validator: pwdValidator,
						trigger: 'blur'
					}, ],
					repassword: [{
						validator: rePwdValidator,
						trigger: 'blur'
					}, ]
				},
				classObj: {
					color: 'black'
				},
				allInstructor: [],
				classes: [],
				academies: [
					'信息与电子工程学院',
					'马克思主义学院',
					'土木工程学院',
					'机械与电气工程学院',
					'艺术学院',
					'人文学院',
					'材料与化学工程学院',
					'管理学院',
					'体育学院'
					],
				form: {
					id: '',
					sno: '',
					username: '',
					realName: '',
					sex: '',
					age: '',
					phone: '',
					email: '',
					address: '',
					avatarUrl: '',
					classNum: '',
					academy: '',
					dormitoryNum: '',
					nation: '',
				},
        roles:{
          1: '管理员',
          2: '辅导员',
          3: '学生',
          4: '教职工'
        },
				instructorId: '',
				requestInstructorId: '',
				id: this.$store.getters.currentUser.id,
			}
		},
		computed: {
			roleName() {
				let roleId = this.$store.state.currentUser.rid
				return this.roles[roleId]
			}
		},
		methods: {
			//确定辅导员
			confirmInstructor(){
				addInstructorIdAndStudentId(this.instructorId, this.id).then(res=>{
					if (res.code !== 200) {
            this.$message.error('操作失败')
					}
          this.$message.success('操作成功')
          this.load()
				})
			},
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (!valid) {
						return false;
					}
          changePassword(this.ruleForm.pass).then(res => {
            if (res.code !== 200) {
              return this.$message.error(res.message)
            }
            this.$message.success('修改成功')
          })
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			onSubmit() { //更新
				updateUser(this.form).then(res => {
					if (res.code !== 200) {
						return this.$message.error("更新失败");
					}
          this.$message.success("更新成功");
          this.load()
        })
			},
			/* 上传成功 */
			handleSuccess(response) {
				this.form.cover = response
				// this.$emit('onUpload')
				this.$message.success('上传成功')
			},
      // 上传成功的钩子函数
			handleAvatarSuccess(res, file) {
				this.form.avatarUrl = res.data
				// this.imageUrl = URL.createObjectURL(file.raw);
			},
			beforeAvatarUpload(file) { //上传前的钩子
				const isJPG = file.type === 'image/jpeg';
				const isPNG = file.type === 'image/png';
				const isLt2M = file.size / 1024 / 1024 < 2;
				if (!isJPG && !isPNG) {
					return this.$message.error('上传头像图片只能是 JPG 格式!');
				}
				if (!isLt2M) {
          return this.$message.error('上传头像图片大小不能超过 2MB!');
				}
				return (isJPG || isPNG) && isLt2M;
			},
		load(){
      getCurrentUserByUsername(this.$store.getters.currentUser.username).then(res => {
        this.form = res
        localStorage.setItem('currentUser', JSON.stringify(res))
        this.$store.commit('SET_CURRENT_USER', JSON.stringify(res))
      })

      //根据用户名查询所属辅导员
			getInstructorByStudentId(this.id).then(res=>{
				if(res.code === 200){
					this.requestInstructorId = res.data;
					this.instructorId = res.data;
				}else{
					this.requestInstructorId = '';
				}

        //查询所有辅导员
        getAllInstructor().then(res => {
          this.allInstructor = res.data
        })

        // 获取所有班级
        getAllClass().then(res => {
          this.classes = res.data
        })
			})
		}
		},
		created() {
			this.load()
		}
	}
</script>

<style>
	.text {
		font-size: 14px;
	}

	.item {
		margin-bottom: 18px;
	}

	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: lightsalmon;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 88px;
		height: 88px;
		display: block;
	}

	.updateBtn {
		width: 100%;
	}
  .el-divider--horizontal{
    margin: 10px !important;
  }
</style>
