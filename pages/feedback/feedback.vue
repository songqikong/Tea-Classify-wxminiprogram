<template>
	<view>
		<view class="search_img">
			<u-row>
				<u-col span="12">
					<image :src="photourl" webp="true"></image>
					<!-- <u--image :src="photourl" mode="widthFix"></u--image> -->
				</u-col>
			</u-row>
		</view>

		<view class="content">
			<u--input placeholder="请输入内容" border="surround" v-model="teaname" @change="change"></u--input>

			<view style="margin-top: 10px;">
				<u-button type="primary" text="确定" @click="feedbackSubmit()" :disabled='disabled'></u-button>

			</view>
		</view>

	</view>

</template>

<script>
	export default {
		data() {
			return {
				photourl: "",
				teaname: "",
				openid: '',
				disabled: true
			}
		},
		onLoad() {
			this.photourl = getApp().globalData.photourl,
				this.openid = getApp().globalData.openid
		},

		methods: {
			change(e){
				if(e != ''){
					this.disabled = false
				}
			},
			feedbackSubmit() {
				uni.request({
					url: "http://localhost:8080/feedback",
					method: "POST",
					data: {
						openid : this.openid,
						img_src: this.photourl,
						label: this.teaname,
					},
					success: () => {
						uni.showToast({
							icon:"error",
							title: '反馈成功',
							duration: 3000
						});
						uni.navigateBack({

						})
					}
				})
			}
		}
	}
</script>

<style>
	.search_img {
		display: flex;
		align-items: center;
		justify-content: center;
		padding-top: 35px;
		background-color: #f3f4f6;
	}

	.content {
		margin-left: 15px;
		margin-right: 10px;
		padding-top: 15px;

	}

	.bg-set {
		position: fixed;
		width: 100%;
		height: 100%;
		top: 0;
		left: 0;
		background-color: #f3f4f6;
		z-index: -1;
	}

	.bottom_banner {
		margin: 0 auto;
		display: flex;
		align-items: center;
		justify-content: center;
		padding-top: 15px;
		margin-left: 140px;
		margin-bottom: 50px;
	}
</style>
