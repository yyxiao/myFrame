<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加</title>
<script language="javascript" src="<%=request.getContextPath()%>/style/js/jquery.min.js"></script>
<style>
table{  border-collapse:collapse;  }
td{  border:1px solid #f00;  }
</style>
<script type="text/javascript">
function add(){
	<%-- alert("<%=request.getContextPath() %>"); --%>
	window.location.href="<%=request.getContextPath() %>/teacher.do?method=add";
}

function del(id){
$.ajax( {
	type : "POST",
	url : "<%=request.getContextPath()%>/teacher.do?method=del&id=" + id,
	dataType: "json",
	success : function(data) {
		if(data.del == "true"){
			alert("删除成功！");
			$("#" + id).remove();
		}
		else{
			alert("删除失败！");
		}
	},
	error :function(){
		alert("网络连接出错！");
	}
});
}
</script>
</head>
<body>

<input id="add" type="button" onclick="add()" value="添加"/>
<table >
	<tr>
		<td>序号</td>
		<td>姓名</td>
		<td>密码</td>
		<td>性别</td>
	</tr>
	<c:forEach items="${list}" var="teacher">
	<tr id="<c:out value="${teacher.teachId}"/>">
		<td><c:out value="${teacher.teachId}"/></td>
		<td><c:out value="${teacher.name}"/></td>
		<td><c:out value="${teacher.password}"/></td>
		<td><c:out value="${teacher.sex}"/></td>
		<td>
			<input type="button" value="编辑"/> &nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" onclick="del('${teacher.teachId}')" value="删除"/>
		</td>
	</tr>
	</c:forEach>
	
</table>
</body>
</html>