<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="header" style="font-size: 16px;">
 <div class="header_container">
	 <div class="logo">
	   <a href="#"><img src="${ctx}/style/image/index/logo.png" /></a>
	 </div>
	 <ul id="sample-menu-1" class="sf-menu">
	   <li><a href="${ctx}/index!view.do">首页</a></li>
		<li class="current">
			<a href="#a">分类</a>
			<ul>
				<li><a href="#aa">学校推荐</a></li>
				<li><a href="#">按分数</a>
					<ul>
						<li><a href="#">5-4分</a></li>
						<li><a href="#">3-2分</a></li>
						<li><a href="#">1分以下</a></li>
					</ul>
				</li>
				
				<li><a href="#">按年级</a>
					<ul>
						<li><a href="#">一、二年级</a></li>
						<li><a href="#">三、四年级</a></li>
						<li><a href="#">五、六年级</a></li>
					</ul>
				</li>
				
				<li><a href="#">按学科</a>
					<ul>
						<li><a href="#">数学</a></li>
						<li><a href="#">历史</a></li>
						<li><a href="#">科学</a></li>
					</ul>
				</li>
				
				<li><a href="#">按标签</a>
					<ul>
						<li><a href="#">推理</a></li>
						<li><a href="#">经典</a></li>
						<li><a href="#">等等</a></li>
					</ul>
				</li>
			</ul>
		</li>
		<li><a href="#">测评</a></li>
		<c:if test="${user!=''&&user!=null}">
			<li><a href="${ctx}/user/users!view.do">个人主页</a></li>
			<li><a onclick="login_off();">退出</a></li>
		</c:if>
		<c:if test="${user==null}">
			<li><a href="/ReadPlatform/login-qt.jsp">个人登录</a></li>
		</c:if>
		
	</ul>

   <div id="search_box"> 
	<form id="search_form" method="post" action="${ctx}/book/book!searchKey.do"> 
		<input type="text" name="s" id="s" value="书名、作者、出版社" class="swap_value"/> 
		<img alt="搜索" onclick="search();" src="${ctx}/style/image/index/btn_search_box.gif" width="27" height="24" id="go">
	</form> 
</div> 
<script type="text/javascript">				
$(document).ready(function(){
	$("ul.sf-menu").superfish();
	$("#s").focus(function() {
		var username = $(this).val();
		if (username == '书名、作者、出版社') {
			$(this).val('');
		}
	});
	$("#s").focusout(function() {
		var username = $(this).val();
		if (username == '') {
			$(this).val('书名、作者、出版社');
		}
	});
});
function search() {
	var s = $("#s").val();
	if (!s || s == '' || s == '书名、作者、出版社') {
		$.dialog.alert('搜索内容不能为空!');
		return false;
	}
	$("#search_form").submit();
}
</script>
 </div>
</div>

