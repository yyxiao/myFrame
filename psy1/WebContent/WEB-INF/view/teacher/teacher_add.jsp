<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生添加</title>
<script type="text/javascript">
function turnback(){
	window.location.href="<%=request.getContextPath() %>/teacher.do";
}
</script>
</head>
<body>
<form method="post" action="<%=request.getContextPath() %>/teacher.do?method=save">
<div><c:out value="${addstate}"></c:out></div>
<table>
	<tr><td>姓名</td><td><input id="name" name="name" type="text" /></td></tr>
	<tr><td>密码</td><td><input id="password" name="password"  type="text" /></td></tr>
	<tr><td>性别</td><td><input id="sex" name="sex"  type="text" /></td></tr>
	<tr><td colSpan="2" align="center"><input type="submit" value="提交"/><input type="button" onclick="turnback()" value="返回" /> </td></tr>
</table>

</form>
</body>
</html>