<template>
	<view class="u-page">
		<view style="margin-top: 10px;margin-left: 150px;" v-show="show">
			<u--text text="没有反馈记录哦~" type="info"></u--text>
			
		</view>
		<u-list
			@scrolltolower="scrolltolower"
		>
			<u-list-item
				v-for="(item, index) in recordlist"
				:key="index"
			>
				<u-cell
					:title="item.label"
					@click="router2fbreview(item.label, item.img_src, item.id)"
				>
				
				<u-avatar
					slot="icon"
					shape="square"
					size="100"
					:src="item.img_src"
					customStyle="margin: -3px 5px -3px 0"
				></u-avatar>
				
				<!-- <u-button type="primary" text="通过"></u-button> -->
				<!-- <u-button type="warning" text="不通过"></u-button> -->
				
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
				show : false
			}
		},
		onLoad() {
			this.loadmore()
			if(this.recordlist.length === "0"){
				this.show = true
			}
		},
		methods: {
			loadmore() {
				uni.request({
					url: 'http://localhost:8080/getfeedback',
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
			router2fbreview(labelstr, photourl, id){
				getApp().globalData.photourl = photourl;
				getApp().globalData.teaname = labelstr;
				getApp().globalData.reviewid = id;
				uni.redirectTo({
					url:"/pages/feedbackreview/feedbackreview"
				})
			}
		}
	}
</script>

<style>

</style>
