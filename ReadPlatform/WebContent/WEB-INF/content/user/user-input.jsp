<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>添加用户基本信息</title>
	<%@ include file="/include/meta.jsp"%>
	<script type="text/javascript">
	$(function(){
	});
	</script>
</head> 

<body>
	<div >
		<strong>用户&gt;
			<c:if test="${users.userId == null}">新增用户</c:if>
			<c:if test="${users.userId != null}">编辑用户</c:if>
		</strong>
	</div>
	<div class="clear"></div>
	<div id="tablelist"  style="margin-right: 0px;">
	<input type="hidden" id="userId" value="${users.userId}"/>
	<s:form action="users!save.do" id="form1" name="form1" >
		<s:token name="token"></s:token>
		<table class="table-responsive">
		<tr>
			<td width="10%"><label style="float: right;">用户名：</label></td>
			<td width="40%">
				<input type='text' id="name" name="users.name" class="inputs" value="${users.name}" maxlength = "20"/>
				<b style="color: red">*&nbsp;</b><span style="color:#ccc;font-size:12px;">&nbsp;填写用户名</span>
			</td>
			<td width="10%"><label style="float: right;">已读书籍数量：</label></td>
			<td width="40%">
				<input type='text' id="readNum" name="users.readNum" class="inputs" value="${users.readNum}" maxlength = "20"/>
				<b style="color: red">*&nbsp;</b><span style="color:#ccc;font-size:12px;">&nbsp;填写已读书籍数量</span>
			</td>
		</tr>
		<tr>
			<td colspan="4" class="btn_right">
              	<input type="button" value="返&nbsp;回" class="btn btn-sm btn-info" onclick="location.href='${ctx}/user/users.do'"/>
        		<input type="submit" value="保&nbsp;存"  class="btn btn-sm btn-info" id="submit"/>
        	</td>
    	</tr>
	</table>
	</s:form>
	</div>
</body>
</html>