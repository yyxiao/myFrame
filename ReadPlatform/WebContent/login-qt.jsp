<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>阅读平台</title>
<%@ include file="/include/meta.jsp"%>
<link rel="stylesheet" href="${ctx}/style/css/login-qt.css" />
<script type="text/javascript" src="${ctx}/style/js/login/login.js"></script>
<script type="text/javascript">
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if(e && e.keyCode==13){ // enter 键
    	var u = $("#userName").val();
    	var p = $("#pwd").val();
    	if (!u || u == '' || u == '输入用户名') {
    		$.dialog.alert('用户名不能为空!');
    		return false;
    	}
    	if (!p || p == '' || p == '输入密码') {
    		$.dialog.alert('密码不能为空!');
    		return false;
    	}
    	$("#form1").submit();
    }
}; 
</script>
</head>
<body>
	<div id="tab">
		<div class="tab_box">
			<!--登录开始 -->
			<div>
				<div class="stu_error_box"></div>
				<p><h2 style="border-bottom:0;font-size:26px;">思来氏阅读平台登录</h2></p>
				<form action="login!login.do" id="form1" method="post" class="stu_login_error">
					<div id="username">
						<label>用户名：</label> <input type="text" id="userName"
							name="userName" value="输入用户名" />
						<!--ajaxurl="demo/valid.jsp"-->
					</div>
					<div id="password">
						<label>密&nbsp;&nbsp;&nbsp;码：</label> <input type="password"
							id="pwd" name="pwd" value="输入密码" />
					</div>
					<div id="login">
						<button onclick="but();" type="button">登录</button>
					</div>
				</form>
			</div>
			<!--登录结束-->
		</div>
	</div>
	<div class="screenbg">
		<ul>
			<li><a href="javascript:;"><img
					src="style/image/login/bg.jpg"></a></li>
		</ul>
	</div>
</body>
</html>
