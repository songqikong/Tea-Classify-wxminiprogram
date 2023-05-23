<template>
	<view class="cameraBg">
		<camera device-position="back" flash="auto" style="width: 100%; height: 100vh">
			<cover-image src="../../static/camera/cameraBg.png" class="scan-img"> </cover-image>
			<cover-view class="scanBtn" v-if="scanShow">
				<cover-view class="beat" @click="scan()">
					<cover-image class="beatImg" src="../../static/camera/album.png"></cover-image>
					<cover-view> 相册 </cover-view>
				</cover-view>
				<cover-view class="album" @click="takePhoto()">
					<cover-image class="albumImg" src="../../static/camera/beat.png"></cover-image>
					<cover-view> 拍照 </cover-view>
				</cover-view>
			</cover-view>
		</camera>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				scanShow: true,
				identifyresult: "",
				tempFilePath: "",
				openid: "",
				photourl:"",
				isTea:true
			}
		},
		onLoad() {
			if(getApp().globalData.openid === ''){
				uni.showToast({
					icon:"error",
					title: '未登录无法记录识别历史',
					duration: 3000
				});
			};
		},
		onUnload() {

			getApp().globalData.info = this.identifyresult;
			getApp().globalData.photourl = this.photourl;
			if(this.photourl === undefined){
				getApp().globalData.photourl = this.tempFilePath;
			}
			getApp().globalData.isTea = this.isTea;
		},
		methods: {
			// 相册
			scan() {
				// 选择图片
				uni.chooseImage({
					count: 1,
					sizeType: ['original', 'compressed'],
					sourceType: ['album'],
					success: (res) => {
						this.compress(res.tempFilePaths[0])
						this.tempFilePath = res.tempFilePaths[0]
					}
				})
			},
			// 启动图片压缩
			compress(tempFilePaths) {
				const vm = this
				vm.scanShow = false
				uni.showLoading({
					title: '智能识别中...'
				})
				uni.compressImage({
					src: tempFilePaths,
					quality: 80,
					success: (imageRes) => {

						// 获取类型
						uni.getImageInfo({
							src: imageRes.tempFilePath,
							success(imageInfo) {
								// 转base64
								uni.getFileSystemManager().readFile({
									filePath: imageRes.tempFilePath,
									encoding: 'base64',
									success: (base) => {
										// 返回base64格式
										const base64Str = 'data:image/' + imageInfo.type +
											';base64,' + base.data
										vm.camera64(base64Str)
									},
									fail: (err) => {
										console.log(err)
									}
								})
							}
						})
					}
				})
			},
			// 拿到图片开始进行识别,并进行网络通信获取茶叶信息
			camera64(base64Str) {
				// 拿到base64,不需要base64  就把上层的转换去掉
				this.scanShow = true
				uni.hideLoading()
				// uni.showToast({
				// 	title: '识别成功',
				// 	duration: 2000
				// });
				// console.log(base64Str,'base64Str图片')
				
				let openid="";
				uni.getStorageSync({
					key: "openid",
					success:function(res){
						openid = res.data.data
						// console.log("前面" + openid)
					},
					fail:function(){
						openid = "notlogin"
					}
				})

				console.log(getApp().globalData.openid)
				uni.request({
					url: 'http://localhost:8080/identify',
					method: "POST",
					data: {
						photo: base64Str,
						openid: getApp().globalData.openid,
						module: getApp().globalData.module,
					},
					success: (res) => {
						console.log(res.data);
						if(res.data === -1){
							this.isTea = false
							this.photourl = this.tempFilePaths
							uni.redirectTo({
								url: '/pages/result/result'
							});
						}else{
							this.isTea = true
							this.identifyresult = res.data[0]
							this.photourl = res.data[1]
							console.log(this.photourl)
							
							uni.redirectTo({
								url: '/pages/result/result'
							});
						}
					},
				});						

			},
			
			changeOpenId(openid){
				this.openid = openid
			},
			// 拍照
			takePhoto() {
				const ctx = uni.createCameraContext()
				ctx.takePhoto({
					quality: 'high',
					success: (res) => {
						this.compress(res.tempImagePath)
					}
				})
			},
			error(e) {
				console.log(e.detail)
			}
		}
	}
</script>

<style lang="scss" scoped>
	.cameraBg {
		width: 100%;
		height: 100vh;
		position: fixed;

		.scan-img {
			width: 100%;
			height: 1624rpx;
			z-index: 1;
		}

		.scanBtn {
			width: 100%;
			z-index: 99999;
			position: fixed;
			bottom: 100rpx;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;

			.beat {
				position: absolute;
				bottom: 0rpx;
				left: 100rpx;
				display: flex;
				flex-direction: column;
				justify-content: center;
				align-items: center;
				font-size: 24rpx;
				font-weight: 400;
				color: #ffffff;

				.beatImg {
					width: 88rpx;
					height: 88rpx;
					margin-bottom: 30rpx;
				}
			}

			.album {
				display: flex;
				flex-direction: column;
				justify-content: center;
				align-items: center;
				font-size: 24rpx;
				font-weight: 400;
				color: #ffffff;

				.albumImg {
					width: 120rpx;
					height: 120rpx;
					margin-bottom: 30rpx;
				}
			}
		}
	}
</style>
