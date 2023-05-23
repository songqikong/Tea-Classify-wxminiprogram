<template class="bg">
	<view class="container"> 
	      <image class="bg-set" src="../../static/bg_.jpg"></image>
	      <view class="content">
			  	<image class="search_ico" src="../../static/search_transparent.png" @click="route2camera()"></image>
		  </view>
	</view> 
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello'
			}
		},

		methods: {
			route2camera(){
				// console.log(getApp().globalData.openid)

				uni.navigateTo({
				    url: '/pages/camera/camera',
					fail(error) {
						console.log(error)
					}
				});
			},
		}
	}
</script>

<style>
	.container {
		display: flex;
		/* flex-direction:initial; */
		align-items: center;
 		justify-content: center;
		padding-top: 200px;
		
		margin-bottom: auto;
		/* background-color: #000000; */
	}
	.search_ico{
		width: 180px;
		height: 180px;
	}
	.bg-set{
	    position: fixed;
	    width: 100%;
	    height: 100%;
	    top: 0;
	    left: 0;
	    background-color: #f4f4f4;
	    z-index: -1;
	}


</style>
