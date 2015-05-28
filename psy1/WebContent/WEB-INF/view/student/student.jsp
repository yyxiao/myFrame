<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加</title>
<link href="/psy1/style/css/bootstrap/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="/psy1/style/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<script language="javascript"
	src="<%=request.getContextPath()%>/style/js/jquery.min.js"></script>
<script type="text/javascript">
function add(){
	<%-- alert("<%=request.getContextPath() %>"); --%>
	window.location.href="<%=request.getContextPath()%>/student.do?method=add";
}

function del(id){
	$.ajax( {
		type : "POST",
		url : "<%=request.getContextPath()%>/student.do?method=del&id=" + id,
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
	<p>
		<button class="btn btn-lg btn-info" onclick="add();" type="button">添加</button>
	</p>
	<table class="table table-striped">
		<thead>
			<tr>
				<td>序号</td>
				<td>姓名</td>
				<td>密码</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="student">
				<tr id="<c:out value="${student.id}"/>">
					<td>${student.id}</td>
					<td>${student.user}</td>
					<td>${student.psw}</td>
					<td>
						<button class="btn btn-sm btn-info" type="button">编辑</button>
						<button class="btn btn-sm btn-danger"
							onclick="del('${student.id}')" type="button">删除</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>