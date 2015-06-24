<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加</title>
<%@ include file="/include/meta.jsp"%>
<script type="text/javascript">
$(function(){
});
</script>
<link href="${ctx}/style/css/bootstrap/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${ctx}/style/css/bootstrap/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<p>
		<button class="btn btn-lg btn-info" onclick="add();" type="button">添加</button>
	</p>
	<table class="table table-striped">
		<thead>
			<tr>
				<th width="5"><input type="checkbox" class="check_all" /></th>
				<th>序号</th>
				<th>书名</th>
				<th>作者</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.result}" var="book">
				<tr>
					<td align="center"><input type="checkbox" class="checkboxs" value='${book.bookId}'/></td>
					<td>${book.bookId}</td>
					<td>${book.bookName}</td>
					<td>${book.author}</td>
					<td>
						<button class="btn btn-sm btn-info" type="button">编辑</button>
						<button class="btn btn-sm btn-danger"
							onclick="del('${book.bookId}')" type="button">删除</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<pg:simplePageView url="book/book.do"></pg:simplePageView>
</body>
</html>