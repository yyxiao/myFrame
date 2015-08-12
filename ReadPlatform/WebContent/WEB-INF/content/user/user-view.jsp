<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>个人信息</title>
<%@ include file="/include/meta.jsp"%>
<link href="${ctx}/style/css/master.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/style/js/index/hoverIntent.js"></script>
<script type="text/javascript" src="${ctx}/style/js/index/superfish.js"></script>
<script type="text/javascript">
	$(function() {
		$("body").ezBgResize({
			img : "../style/image/index/bg31.png", // Relative path example.  You could also use an absolute url (http://...).
			opacity : 1, // Opacity. 1 = 100%.  This is optional.
			center : true
		});
	});
	function changeFace(userId){
		$.dialog({
		    id: 'a15',
		    width: '300px',
		    height: '120px',
		    title: '更换头像',
		    lock: true,
		    content: '<form action="${ctx}/upload.do" id="formC" enctype="multipart/form-data" method="post">头像:<input type="file" name="image"><input type="hidden" name="userId" value="'+userId+'"></form>',
		    padding: 0,
		    ok: function () {
		    	formC.submit();
		        return false;
		    },
		    cancel: true
		});
	}
</script>
<link href="${ctx}/style/css/index/focuscarousel.css" rel="stylesheet" />
<link rel="stylesheet" media="screen" href="${ctx}/style/css/index/superfish.css" />
</head>
<body>
<%@ include file="/include/head.jsp"%>
<div id="wrapper" style="background: #fff; padding: 10px; margin-top:20px;">     
  <div id="content">
	  <div class="grid-16-8 clearfix">
		<div class="article">
		  <div class="user-profile-nav" id="db-usr-profile">
	  <h2>${user.name}</h2>
		<div class="pic">
		<!-- changeFace(${user.userId}); -->
			<a onclick="">
			  <img height="96" width="96"
				alt="${user.name}" src="${user.face}" />
			</a>
		</div>
		<div class="info">
		  <p>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;校:<span class="color-gray">${user.school}</span></p>
		  <p>阅&nbsp;&nbsp;读&nbsp;&nbsp;量:<span class="color-gray">10000字</span></p>
		  <p>班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级:<span class="color-gray">10001分</span></p>
		  <p>阅读书籍:<span class="color-gray">21本</span></p>
		</div>
	  </div> 
   	<div style="clear:both;"></div>
	 <!--section popular-authors-->
	<div class="popular-authors">
		<div class="hd"> 
		  <h2 class='' style="border-bottom:1px solid #DDD;">
			<span class="">阅读记录</span>
		  </h2>
	</div>
		
	<div class="bd">
		<h2>
			<span class="">在读书单</span>· · ·
			<span class="pl">(<a>${bookBsize}本</a>)</span>
	   </h2>
      <ul class="entry-list-col2s s "data-dstat-areaid="55" data-dstat-mode="click,expose">    
      	<c:if test="${bookBsize=='0'}">
			<p class="h65">暂无在读书单。</p>
      	</c:if>
       	<c:forEach items="${bookB}" var="bookB">
       		<li class="noline">
				<a href="${ctx}/book/book!view.do?bookId=${bookB.bookId}">
				  <img class="userface" src="${bookB.smallImages}" alt="${bookB.bookName}"/>
				</a>
				<div class="fleft w180">
					<p>书&nbsp;&nbsp;&nbsp;&nbsp;名：<span>${bookB.bookName}</span></p>
					<p>作&nbsp;&nbsp;&nbsp;&nbsp;者：<span>${bookB.author}</span></p>
					<p>出版社者：<span>${bookB.publisher}</span></p>
				</div>
			</li>
       	</c:forEach>
      </ul>
    </div>
		
   	<div class="bd">
		<h2>
			<span class="">必读书单</span>· · ·
			<span class="pl">(<a>${bcPage.totalCount}本</a>)</span>
	   </h2>
      <ul class="entry-list-col2s s "data-dstat-areaid="55" data-dstat-mode="click,expose"> 
	      <c:if test="${bcPage.totalCount=='0'}">
			<p class="h65">暂无必读书单。</p>
	      </c:if>
	      <c:forEach items="${bcPage.result}" var="bc">
	      	<li class="noline">
				<a href="${ctx}/book/book!view.do?bookId=${bc.bookId}">
				  <img class="userface" src="${bc.images}" alt="${bc.bookName}"/>
				</a>
				<div class="fleft w180">
					<p>书&nbsp;&nbsp;&nbsp;&nbsp;名：<span>${bc.bookName}</span></p>
					<p>作&nbsp;&nbsp;&nbsp;&nbsp;者：<span>${bc.author}</span></p>
					<p>出版社：<span>${bc.publisher}</span></p>
				</div>
			</li>
	      </c:forEach>
      </ul>
    </div>
	
	<div class="bd">
		<h2>
			<span class="">想读书单</span>· · ·
			<span class="pl">(<a>${bookAsize}本</a>)</span>
	   </h2>
      <ul class="entry-list-col2s s "data-dstat-areaid="55" data-dstat-mode="click,expose">    
      	<c:if test="${bookAsize=='0'}">
			<p class="h65">暂无想读书单。</p>
      	</c:if> 
      	<c:forEach items="${bookA}" var="bookA">
       		<li class="noline">
				<a href="${ctx}/book/book!view.do?bookId=${bookA.bookId}">
				  <img class="userface" src="${bookA.smallImages}" alt="${bookA.bookName}"/>
				</a>
				<div class="fleft w180">
					<p>书&nbsp;&nbsp;&nbsp;&nbsp;名：<span>${bookA.bookName}</span></p>
					<p>作&nbsp;&nbsp;&nbsp;&nbsp;者：<span>${bookA.author}</span></p>
					<p>出版社：<span>${bookA.publisher}</span></p>
				</div>
			</li>
       	</c:forEach>
      </ul>
    </div>
	<div class="bd">
	   <h2>
			<span class="">已读书单</span>· · ·
			<span class="pl">(<a>${bookCsize}本</a>)</span>
	   </h2>
      <ul class="entry-list-col2s s "data-dstat-areaid="55" data-dstat-mode="click,expose"> 
      	<c:if test="${bookCsize=='0'}">
			<p class="h65">暂无已读书单。</p>
      	</c:if>
      	<c:forEach items="${bookC}" var="bookC">
       		<li class="noline">
				<a href="${ctx}/book/book!view.do?bookId=${bookC.bookId}">
				  <img class="userface" src="${bookC.smallImages}" alt="${bookC.bookName}"/>
				</a>
				<div class="fleft w180">
					<p>书&nbsp;&nbsp;&nbsp;&nbsp;名：<span>${bookC.bookName}</span></p>
					<p>作&nbsp;&nbsp;&nbsp;&nbsp;者：<span>${bookC.author}</span></p>
					<p>出版社者：<span>${bookC.publisher}</span></p>
				</div>
			</li>
       	</c:forEach>    
      </ul>
    </div>
  </div>
<!--section popular-authors--> 
	 <!-- 短评 begin-->
  <div class="mod comment-m">
	  <h2>
	    <span class="">短评</span>
	  </h2>
	  <c:forEach items="${readComment}" var="readComment">
	  	<div class="tlst" style="border-bottom: 1px dashed #DDD;">
			<div class="ilst">
			  <a href="${ctx}/book/book!view.do?bookId=${readComment.bookId}" title="${readComment.bookName}"><img src="${readComment.bookImage}" style="width: 40px;height: 50px;" alt="${readComment.bookName}"/></a>
			</div>
			<div class="clst">
				<p>书名:<span>${readComment.bookName}</span></p>
				<div id='review_7488981_short' class="review-short">
					<span class="">  ——评文/${readComment.content}</span>
				</div>
			 	<div id="review_7488981_full" style="display:none"></div>
			</div>
		</div>
		<div class="clear"></div>
	  </c:forEach>
  </div>
  <!-- 短评 end-->
</div>
  <div class="aside">
  <div class="mod">
	  <h2>
		<span class="">阅读历史</span>
	  </h2>
		<ul class="entry-list-col2s s">
			<c:forEach items="${hisRead}" var="hisRead">
				<li class="noline" style="border-bottom: 1px dashed #DDD;padding: 6px 0 10px;">
					<a href="${ctx}/book/book!view.do?bookId=${hisRead.bookId}">
					  <img class="userface" style="width: 40px;height: 50px;" src="${hisRead.bookImage}" alt="${hisRead.bookName}"/>
					</a>
					<div class="fleft">
						<span class="pl"> ${hisRead.createTime}</span>
						<p>${hisRead.bookName}加入
						<c:if test="${hisRead.type=='00A'}">
							想读
						</c:if>
						<c:if test="${hisRead.type=='00B'}">
							在读
						</c:if>
						<c:if test="${hisRead.type=='00C'}">
							读完
						</c:if>
						 书单</p>
					</div>
					
				</li>
			</c:forEach>
		</ul>
	  </div>
	   <h2 class=''>
		<span class="">我的标签</span>
		  <span class="link-more" style="float:right;">
			<a class="" href="#"> +添加标签</a>
		  </span>
	  </h2>
	  <ul class="hot-tags-col5 s" data-dstat-areaid="54" data-dstat-mode="click,expose">
			<li>
				<ul class="clearfix">
					<li class="tag_title"> 星座</li>
					<li><a href="#" class="tag">科技</a></li>
					<li><a href="#" class="tag">散文</a></li>
					<li><a href="#" class="tag">小说</a></li>
					<li class="last"><a href="#" class="tag">美食</a></li>
					<li><a href="#" class="tag">旅行</a></li>
					<li><a href="#" class="tag">设计</a></li>
					<li><a href="#" class="tag">文艺</a></li>
				</ul>
			</li>
	   </ul>
      </div> 
      <div class="extra"></div>
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
 </div>
</body>
</html>

