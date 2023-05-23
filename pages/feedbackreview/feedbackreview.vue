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
		
		<view>
			<u--text :text="teaname" align="center" size="30"></u--text>
		</view>

		<view class="content">
			<u-button type="primary" text="通过" @click="updatePermission(1)"></u-button>
			<view style="margin-top: 10px;">
				<u-button type="warning" text="不通过" @click="updatePermission(-1)"></u-button>
				
			</view>
		</view>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				photourl:"",
				teaname:'',
				reviewid:0,
			}
		},
		onLoad() {
			this.reviewid = getApp().globalData.reviewid
			this.photourl = getApp().globalData.photourl
			this.teaname = getApp().globalData.teaname
		},
		
		methods: {
			updatePermission(Permission){
				uni.request({
					url:"http://127.0.0.1:8080/updatePermission",
					method:"POST",
					data:{
						reviewid: this.reviewid,
						permission: Permission,
					},
					success: () => {
						console.log("成功"),
						uni.redirectTo({
							url:"/pages/review/review"
						})
					}
				})
			}
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
