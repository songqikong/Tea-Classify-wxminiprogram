<template>
	<view class="u-page">
		<view style="margin-top: 10px;margin-left: 150px;" v-show="show">
			<u--text text="没有识别记录哦~" type="info"></u--text>
			
		</view>
		<u-list
			@scrolltolower="scrolltolower"
		>
			<u-list-item
				v-for="(item, index) in recordlist"
				:key="index"
			>
				<u-cell
					:title="shibiejilu + item.id"
					@click="recordToResult(item.labels, item.img_src)"
				>
				</u-cell>
			</u-list-item>
		</u-list>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				recordlist:{},
				shibiejilu:"识别记录",
				show : false
			}
		},
		onLoad() {
			this.loadmore()
			if(getApp().globalData.openid === ""){
			this.show = true
			}
		},
		methods: {
			// scrolltolower() {
			// 	this.loadmore()
			// },
			loadmore() {
				uni.request({
					url: 'http://localhost:8080/record',
					method: "POST",
					data: {
						openid: getApp().globalData.openid,
					},
					success: (res) => {
						console.log(res.data);
						this.recordlist = res.data
					}
				});
			},
			recordToResult(labelstr, photourl){
				getApp().globalData.photourl = photourl;
				uni.request({
					url: 'http://localhost:8080/recordToResult',
					method: "POST",
					data: {
						labelstr: labelstr,
					},
					success: (res) => {
						getApp().globalData.info = res.data;
						uni.navigateTo({
							url: '/pages/result/result'
						});
					}
				});
			}
		}
	}
</script>

<style>

</style>
