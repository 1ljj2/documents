<template>
	<div class="layout" id="layout-header">
		<layout>
			<i-header>
				<i-menu mode="horizontal" theme="dark" active-name="1">
					<!--<div class="layout-logo-img"><img src="/layout/img/jitrjgc.png"  /></div>-->
					<div class="layout-logo">&nbsp;&nbsp;<span>{{projectName}}</span></div>
					<div class="layout-nav">

						<span style="font-size:16px;">欢迎您</span>
						<a style="font-size:16px;" id="user_name" href="javascript:void(0);">{{userName}}</a>
						<!--<span>未读消息<a id="todo_affair_count" @click="">{{todoAffairCount}}10</a>条</span>-->
						<!--<el-badge :value="10" :max="99" class="item">
						  <el-button size="mini" type="danger" plain @click="jumpTodoAffair('/affair/todoAffair.html')">待办事务</el-button>
						</el-badge>-->
						<span style="font-size:16px;" class="nowTime">{{realTime}}</span>
						<span><a style="font-size:16px;" @click=quitLogin()>退出</a></span>
					</div>
				</i-menu>
			</i-header>
		</layout>
	</div>
</template>
	<script type="text/javascript" src="/templates/layout/js/layout-header.js"></script>