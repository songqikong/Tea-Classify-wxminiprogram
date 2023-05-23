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
			<u--text text="搜索结果" bold='true' size='20px' color="#3a3a3a"></u--text>
			<u-divider></u-divider>
			<view style="margin-top: 10px;margin-left: 30px;margin-right: 30px;" v-show="!isTea">
				<u--text text="未识别到任何茶叶信息!" type="error" align="center"></u--text>
				<u--text text="请确定拍摄物为茶叶,或选择合适拍摄角度。" type="error" align="center"></u--text>
			</view>
			<view v-for="(item, i) in info" v-show="isTea">
				<u-row align="top">
					<u-col span="4">
						<view style="margin-top: 15px;">
							<u--image :src="item.url" mode="aspectFill" width="95px" height="95px" radius="10px"></u--image>
						</view>
						<u--text text="相似度" size='11px' color="#767676" style="margin-top: 5px;"></u--text>
						<u--text :text="100-i*20" bold='true' size='20px' color="#0099e6"></u--text>
					</u-col>
					
					<u-col span="8">
						<view style="margin-top: 10px;margin-bottom: 5px;">
							<u--text :text="item.name" bold='true' size='19px' color="#3a3a3a"></u--text>
							
						</view>
						<view style="margin-bottom: 15px;">
							<u--text :text="item.description" size='12px' color="#868686"></u--text>
						</view>
					</u-col>
	<!-- 				<u-col span="1">
						<u--text text="相似度" size='11px' color="#767676"></u--text>
						<u--text text="95%" bold='true' size='20px' color="#0099e6"></u--text>
					</u-col> -->
				</u-row>
				<u-divider></u-divider>
			</view>

		</view>
				
		<view class="bottom_banner" v-show="isTea">
			<u--text text="都不是想要的结果？" color="#0078b4" size="10px" @click="router2feedback()"></u--text>
		</view>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				info:{},
				photourl:"",
				more:"....",
				isTea:true
			}
		},
		onLoad() {
			this.info = getApp().globalData.info
			this.photourl = getApp().globalData.photourl
			this.isTea = getApp().globalData.isTea
		},
		onUnload() {  
		    getApp().globalData.isTea = true
		},
		
		
		methods: {
			// getInfo(){
			// 	uni.$once('result',function(data){
			// 		console.log('监听到事件来自 camera result');
			// 		this.info = data
			// 		console.log(this.info)
			// 	});
			// 	uni.$once('photourl',function(data){
			// 		console.log('监听到事件来自 camera result');
			// 		this.photourl = data
			// 		console.log(this.info)
			// 	});
				
			// 	console.log("当前photourl = " + this.photourl)
			// }
			router2feedback() {
				if(getApp().globalData.openid == ''){
					uni.showToast({
						icon:"error",
						title: '未登录无法反馈',
						duration: 3000
					});
					
					return "未登录无法反馈";
				}
				uni.navigateTo({
					url: '/pages/feedback/feedback',
					fail(error) {
						console.log(error)
					}
				});
			},
		}
	}
</script>

<style>
.search_img{
	display: flex;
	align-items: center;
	justify-content: center;
	padding-top: 35px;
	background-color: #f3f4f6;
}
.content{
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
.bottom_banner{
	margin: 0 auto;
	display: flex;
	align-items: center;
	justify-content: center;
	padding-top: 15px;
	margin-left: 140px;
	margin-bottom: 50px;
}
</style>
