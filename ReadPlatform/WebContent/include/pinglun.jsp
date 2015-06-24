<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/include/meta.jsp"%>
<style type="text/css" media="screen">
a, img {
	border: 0;
}

a {
	color: #5e5e5e;
	text-decoration: none;
}

a:hover {
	color: #5e5e5e;
	text-decoration: underline;
}
/* box */
.box {
	position: absolute;
	width: 600px;
	left: 50%;
	height: auto;
	z-index: 100;
	background-color: #fff;
	border: 1px #ccc solid;
	padding: 1px;
}

.box h2 {
	height: 25px;
	font-size: 14px;
	background-color: #EBF5EA;
	position: relative;
	padding-left: 10px;
	line-height: 25px;
	color: #072;
}

.box h2 a {
	position: absolute;
	right: 5px;
	font-size: 12px;
	color: #072;
}

#TB_overlayBG {
	background-color: #666;
	position: absolute;
	z-index: 99;
	left: 0;
	top: 0;
	display: none;
	width: 100%;
	height: 100%;
	opacity: 0.5;
	filter: alpha(opacity = 50);
	-moz-opacity: 0.5;
}

table {
	border-collapse: collapse;
	border: 0px none;
}

.input_basic {
	border: 1px solid #CCC;
	padding: 2px;
	font-size: 12px;
	width: 450px;
	height: 30px;
}

.basic {
	border: 1px solid #CCC;
	padding: 2px;
	font-size: 12px;
	width: 448px;
	height: 150px;
}

.ff {
	text-align: center;
	margin-top: 15px;
	margin-bottom: 20px;
}
</style>
<script type="text/javascript">
	$(function() {
		var api = frameElement.api, W = api.opener;
		window.onload = function() {
			var userId = api.data.userId;
			var bookId = api.data.bookId;
			var face = api.data.face;
			$("#userFace").attr("src", face);
			$("#bookId").attr("value", bookId);
		}
	});
</script>
</head>
<body>
	<form id="formB" action="${ctx}/book/book!comment.do"
		style="margin-left: 10px;">
		<input type="hidden" id="bookId" name="bookId" />
		<table>
			<!-- <tr style="height: 80px;">
				<td width="75">标题</td>
				<td><input id="pltitle" type="text" class="input_basic" /></td>
			</tr> -->
			<tr>
				<td valign="top"><img id="userFace" width="45" height="45" /></td>
				<td><textarea id="plinfo" class="basic"></textarea></td>
			</tr>
		</table>
	</form>
</body>