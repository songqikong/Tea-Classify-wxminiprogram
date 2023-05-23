<style lang="scss">
	/* 注意要写在第一行，同时给style标签加入lang="scss"属性 */
	@import "@/uni_modules/uview-ui/index.scss";
</style>

<script>
	export default {
		globalData: {
			// 用户标识符
		    openid: "",
			// 用户权限组
			group:"",
			// 识别信息
			info:{},
			// 图片地址
			photourl:"",
			// 识别图片是否是茶叶
			isTea:true,
			// 当前选择的模型
			module:0,
			// 审核页面茶叶名字
			teaname:'',
			// 审核页面id
			reviewid:0,
		},
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		},

	}
</script>

<style>
	/*每个页面公共css */
</style>
