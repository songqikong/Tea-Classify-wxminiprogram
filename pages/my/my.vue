<template>
	<view class="container">
		<image class="bg-set"></image>
		<view class="profile">
			<u-cell-group>
				<u-cell size="large" :title="nickname" titleStyle="font-size: 0.5cm;font-weight: bold;margin-left:15px"
					v-on:click="getWXUserInfo()" isLink>
					<image slot="icon" :src="ava_src" class="profile_png"></image>
				</u-cell>
			</u-cell-group>
		</view>
		<view class="profile">
			<u-cell-group class="profile">
				<u-cell title="搜索记录" @click="router2record()" isLink></u-cell>
				<u-cell title="设置" @click="router2setting()" isLink></u-cell>
<!-- 				<u-cell title="识别结果调试" @click="router2result()" isLink></u-cell>
				<u-cell title="反馈调试" @click="router2feedback()" isLink></u-cell> -->
				<u-cell title="管理员审核" @click="router2review()" v-if="isAdmin" isLink ></u-cell>
			</u-cell-group>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				ava_src: "../../static/profile.png",
				nickname: "登陆",
				isLogined: false,
				isAdmin:false,
				group:0,
			}
		},
		onInit() {
			uni.getStorage({
				key: 'openid',
				success: res => {
					this.isLogined = true;
		
					uni.getStorage({
						key: 'ava_src',
						success: res => {
							ava_src = res
						}
					});
					uni.getStorage({
						key: 'nickname',
						success: res => {
							nickname = res
						},
					});
		
				}
			})
		},
		onLoad() {
			
		},
		methods: {
			getWXUserInfo() {
				let that=this
				if (!this.isLogined) {
					wx.login({
						success(res) {
							// console.log(res.code)
							if (res.code) {
								//发起网络请求
								uni.request({
									url: 'http://127.0.0.1:8080/login',
									data: {
										code: res.code,
									},
									success: res => {
										console.log(res)
										getApp().globalData.openid = res.data[0]
										getApp().globalData.group = res.data[1]
										
										if(getApp().globalData.group === 1){
											that.isAdmin = true
											console.log("管理员")
										}
										
										uni.setStorage({
											key: 'openid',
											data: res,
											success:function(){
												console.log("openid storage success")
											}
										})
										
									},
									fail() {
										console.log("登陆失败")
									}
								});
							} else {
								console.log('登录失败！' + res.errMsg)
							}
						}
					});
					

										
					uni.getUserInfo({
						provider: "weixin",
						timeout: 5000,
						success: res => {
							console.log(res.userInfo)
							this.nickname = res.userInfo.nickName
							this.ava_src = res.userInfo.avatarUrl
							uni.setStorage({
									key: 'ava_src',
									data: res.userInfo.avatarUrl
								}),
								uni.setStorage({
									key: 'nickname',
									data: res.userInfo.nickName
								})
						}
					});
				}
			},


			router2setting() {
				uni.$emit('msg',{msg:"窗口通信"});
				uni.navigateTo({
					url: '/pages/setting/setting',
					fail(error) {
						console.log(error)
					}
				});
			},
			
			router2result() {
				uni.navigateTo({
					url: '/pages/result/result',
					fail(error) {
						console.log(error)
					}
				});
			},
			
			router2record() {
				uni.navigateTo({
					url: '/pages/record/record',
					fail(error) {
						console.log(error)
					}
				});
			},
			
			router2feedback() {
				uni.navigateTo({
					url: '/pages/feedback/feedback',
					fail(error) {
						console.log(error)
					}
				});
			},
			
			router2review() {
				uni.navigateTo({
					url: '/pages/review/review',
					fail(error) {
						console.log(error)
					}
				});
			},
		}
	}
</script>

<style lang="scss">
	.container {
		padding-bottom: 20px;
	}

	.profile {
		margin-top: 15px;
		margin-bottom: 15px;
		background-color: #fff;
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

	.profile_png {
		width: 50px;
		height: 50px;
	}
</style>
