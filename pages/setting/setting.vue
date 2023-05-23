<template>
	<view>
		<view class="profile">
			<u-picker :show="show" :columns="columns" @confirm="confirm" @cancel="cancel" keyName="label" :defaultIndex="[module]"></u-picker>
			<u-cell-group class="profile">
				<u-cell title="识别模型选择" @click="show=true" isLink></u-cell>
			</u-cell-group>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				module:0,
				show: false,
				loading: false,
				columns: [
					[{
						label: "ResNet34",
						id: '0'
					}, {
						label: "Vgg16",
						id: '1'
					}],
				],
			}
		},
		onLoad() {
			uni.$once('msg', function(data) {
				console.log('监听到事件来自 camera' + data.msg);
			});
			this.module = getApp().globalData.module
		},
		methods: {
			confirm(e) {
				console.log('confirm', e)
				getApp().globalData.module = e.value[0].id
				this.module = getApp().globalData.module
				this.show = false
			},
			cancel() {
				this.show = false
			}
		}
	}
</script>

<style>

</style>
