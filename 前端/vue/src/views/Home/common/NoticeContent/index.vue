<template>
	<div class="detailBG">
		<!-- 左侧 -->
		<div class="detail_left">
		</div>
		<el-card class="detailContent">
			<div style="width: 100%;height: 55px;display: flex;flex-direction: column;align-items: center;">
				<span style="font-size: 28px;font-family: 华文楷体,sans-serif;"><strong>{{detail.title}}</strong></span>
				<span style="font-size: 12px;color: #999;margin-top: 10px;">时间：{{detail.createTime}}&nbsp;来源：{{detail.source}}&nbsp;访问数：{{detail.clickNum}}</span>
				<div style="width: 80%;border-top: 1px dotted #333333;margin-top: 10px;"></div>
			</div>
			<div class="markdown-body" style="margin-top: 20px;">
				<div class="js-toc-content" v-html="detail.detailHtml"></div>
			</div>
			<span class="detail_end">------------------------------------ END ------------------------------------</span>

		</el-card>
		<!-- 右侧 -->
		<div class="detail_right">
		</div>
	</div>
</template>

<script>
	import {
    getDetailIdByIdAndType
	} from '@/network/notice.js'

	export default {
		name: 'NoticeDetail',
		data() {
			return {
				detail: {}
			}
		},
		mounted() {
			let id = this.$route.query.id
      getDetailIdByIdAndType(id, 'html').then(res => {
					this.detail = res.data
			})
		}
	}
</script>

<style>
	@import url("../../../../assets/css/markdown.css");

	.detailBG {
		position: relative;
		display: flex;
		width: 100%;
		height: 100%;
		/* background-image: url(''); */
		background-position: center;
		background-size: cover;
		background-repeat: no-repeat;
		background-attachment: fixed
	}

	.detailContent {
		position: relative;
		text-align: left;
		width: 990px;
		margin: 0 auto 0 auto;
		padding: 0.5em 0em;
		color: var(--color-text);
		background-color: var(--bgColor);
	}

	.hljs {
		position: relative;
		display: block;
		overflow-x: auto;
		padding: 1em 2em;
		background: #191727;
		color: #d6deeb;
		cursor: unset;
	}

	.hljs:hover:before {
		position: absolute;
		content: '复制';
		color: black;
		right: 1em;
		top: 0.8em;
		text-align: center;
		line-height: 1.8em;
		border-radius: 5px;
		font-size: 14px;
		width: 3.2em;
		height: 1.8em;
		background-color: #e6e6e6;
		cursor: pointer;
	}

	.el-divider--horizontal {
		background-color: #505050;
	}

	.detail_left {
		flex: 1;
	}

	.detail_right {
		position: relative;
		flex: 1;
	}

	.detail_end {
		display: flex;
		align-content: center;
		justify-content: center;
		font-weight: bold;
		opacity: 0.6;
	}
</style>
