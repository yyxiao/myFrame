<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>阅读平台</title>
<%@ include file="/include/meta.jsp"%>
<link href="${ctx}/style/css/master.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/style/js/index/hoverIntent.js"></script>
<script type="text/javascript" src="${ctx}/style/js/index/superfish.js"></script>
<script type="text/javascript">
	$(function() {
		//var bg = ${book.background};
		$("body").ezBgResize({
			img : "../style/image/index/bg31.png", // Relative path example.  You could also use an absolute url (http://...).
			opacity : 1, // Opacity. 1 = 100%.  This is optional.
			center : true
		});
	});
</script>
<link href="${ctx}/style/css/index/focuscarousel.css" rel="stylesheet" />
<link rel="stylesheet" media="screen" href="${ctx}/style/css/index/superfish.css" />
</head>
<body>
<%@ include file="/include/head.jsp"%>
	<div id="wrapper" style="background: #fff; padding: 10px; margin-top: 40px;">
		<div id="content">
			<div class="grid-16-8 clearfix">
					<!-- 查询list begin -->
					<div class="mod-hd">
						<h2>
							<span class="">查询结果</span>
						</h2>
					</div>
					<c:forEach items="${pager.result}" var="book">
						<div class="tlst" style="border-bottom: 1px dashed #DDD;margin-bottom:10px;padding-bottom: 25px;" >
							<div class="ilst">
								<a href="${ctx}/book/book!view.do?bookId=${book.bookId}" title="${book.bookName}"><img
									src="${book.smallImages}" alt="${book.bookName}" /></a>
							</div>
							<div class="clst">
								<h2><a href="${ctx}/book/book!view.do?bookId=${book.bookId}">${book.bookName}</a></h2>
								<div>作者：<span style="color: #a6a6bf;">${book.author}</span></div>
								<div>出版社：<span style="color: #a6a6bf;">${book.publisher}</span></div>
								<div>出版日期：<span style="color: #a6a6bf;">${book.pubdate}</span></div>
								<div style="display: none"></div>
							</div>
						</div>
					</c:forEach>
					<pg:simplePageView url="book/book!searchKey.do"></pg:simplePageView>
					<!-- 查询list end -->
			</div>

		</div>
		<div id="footer">
			<span id="icp" class="fleft gray-link">
				&copy; 2005－2015 psylife, all rights reserved
			</span>
			
			<a href="#" style="display: none;"></a>
			<span class="fright">
				<a href="#">联系我们</a>
				· <a href="#">免责声明</a>    
				· <a href="#">帮助中心</a>
				· <a href="#">图书馆合作</a>
				· <a href="#">移动应用</a>
			</span>
		  </div>
	</div>
</body>
</html>

