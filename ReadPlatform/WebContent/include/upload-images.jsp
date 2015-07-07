<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/include/meta.jsp"%>
<style type="text/css" media="screen">

</style>
<script type="text/javascript">
	$(function() {
		
	});
</script>
</head>
<body>
	<form action="${ctx}/upload.do" enctype="multipart/form-data" method="post">
            头像:<input type="file" name="image">
        <input type="submit" value="上传" />
	</form>
	<br/>
    <s:fielderror />
</body>

</html>