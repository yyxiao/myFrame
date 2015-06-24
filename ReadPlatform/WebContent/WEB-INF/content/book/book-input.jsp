<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>添加图书基本信息</title>
	<%@ include file="/include/meta.jsp"%>
	<script type="text/javascript">
	$(function(){
	});
	</script>
</head> 

<body>
	<div >
		<strong>图书&gt;
			<c:if test="${book.bookId == null}">新增图书</c:if>
			<c:if test="${book.bookId != null}">编辑图书</c:if>
		</strong>
	</div>
	<div class="clear"></div>
	<div id="tablelist"  style="margin-right: 0px;">
	<input type="hidden" id="bookId" value="${book.bookId}"/>
	<s:form action="book!save.do" id="form1" name="form1" >
		<s:token name="token"></s:token>
		<table class="table-responsive">
		<tr>
			<td width="10%"><label style="float: right;">书名：</label></td>
			<td width="40%">
				<input type='text' id="bookName" name="book.bookName" class="inputs" value="${book.bookName}" maxlength = "20"/>
				<b style="color: red">*&nbsp;</b><span style="color:#ccc;font-size:12px;">&nbsp;填写书名</span>
			</td>
			<td width="10%"><label style="float: right;">作者：</label></td>
			<td width="40%">
				<input type='text' id="author" name="book.author" class="inputs" value="${book.author}" maxlength = "20"/>
				<b style="color: red">*&nbsp;</b><span style="color:#ccc;font-size:12px;">&nbsp;填写作者</span>
			</td>
		</tr>
		<tr>
			<td width="10%"><label style="float: right;">isbn：</label></td>
			<td colspan="3" width="90%">
				<input type='text' id="isbn" name="isbn" class="inputs" value="" maxlength = "20"/>
				<b style="color: red">*&nbsp;</b><span style="color:#ccc;font-size:12px;">&nbsp;填写isbn</span>
			</td>
		</tr>
		<tr>
			<td colspan="4" class="btn_right">
              	<input type="button" value="返&nbsp;回" class="btn btn-sm btn-info" onclick="location.href='${ctx}/book/book.do'"/>
        		<input type="submit" value="保&nbsp;存"  class="btn btn-sm btn-info" id="submit"/>
        	</td>
    	</tr>
	</table>
	</s:form>
	</div>
</body>
</html>