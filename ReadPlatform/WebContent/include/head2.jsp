<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/ReadPlatform/style/css/header.css">
<div id="Header">
 <div class="LiquidContainer clearfix">
 	<a class="logo"><img src="/ReadPlatform/style/image/index/logo.png" /></a>
	 	<div id="main_menu">
			<div class="topnav">
				<div class="nav"><a class="link" href="${ctx}/index!view.do">首页</a></div>
				<div class="nav">
					<a class="link" href="#">分类</a>
					<ul class="menu">
						<li><a href="#">学校推荐<em></em></a></li>
						<li>
						  <a href="#">按分数<em></em></a>
							<ul>
								<li><a href="#">10-8分</a></li>
								<li><a href="#">7-5分</a></li>
								<li><a href="#">5分以下</a></li>
							</ul>
						
						</li>
						<li>
						 <a href="#">按年级<em></em></a>
							<ul>
								<li><a href="#">一、二年级</a></li>
								<li><a href="#">三、四年级</a></li>
								<li><a href="#">五、六年级</a></li>
							</ul>
						 </li>
						<li>
						  <a href="#">按学科<em></em></a>
							<ul>
								<li><a href="#">数学</a></li>
								<li><a href="#">历史</a></li>
								<li><a href="#">科学</a></li>
							</ul>
						</li>
						<li>
						  <a href="#">按标签<em></em></a>
							<ul>
								<li><a href="#">推理</a></li>
								<li><a href="#">经典</a></li>
								<li><a href="#">等等</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<c:if test="${user!=''&&user!=null}">
					<div class="nav"><a class="link" href="${ctx}/user/users!view.do">个人主页</a></div>
				</c:if>
			</div>

		<div class="nav_user">
			<a rel="nofollow" href="" class="nav tools">
				<c:if test="${user!=''&&user!=null}">
					<em><img src="${user.face}" width="38" height="38" /></em>
				</c:if>
				<c:if test="${user==null}">
					<em><img src="/ReadPlatform/style/image/index/xt.jpg" width="38" height="38" /></em>
				</c:if>
				
			</a>
			<ul class="menu">
				<li class="about">
					<a rel="nofollow" class="notlogin" href="/ReadPlatform/login-qt.jsp">登录</a>
					<a rel="nofollow" onclick="login_off();">退出</a><em></em>
				</li>
			</ul>
		</div>
        		
		<div id="Search">
			<form action="${ctx}/book/book!searchKey.do" method="get" class="text">
				<input type="text" id="s" name="s" size="27" value="" placeholder="书名、书号、作者、出版社" maxlength="12" />
				<a id="query_button" onclick="search();" class="lg"><img src="http://www.17sucai.com/static/images/search.gif" width="12" height="12" alt="搜索" /></a>
			</form>
		</div><!--Search end-->
		
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

