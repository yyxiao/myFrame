<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${book.bookName}</title>
<%@ include file="/include/meta.jsp"%>
<link href="${ctx}/style/css/master.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/style/css/raty.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/style/js/index/hoverIntent.js"></script>
<script type="text/javascript" src="${ctx}/style/js/index/superfish.js"></script>
<script type="text/javascript" src="${ctx}/style/js/raty.js"></script>
<script type="text/javascript">
	$(function() {
		//var bg = ${book.background};
		if ($.browser.msie && $.browser.version == 6.0) {
			var maxWidth = parseInt($('#info').css('max-width'));
			if ($('#info').width() > maxWidth)
				$('#info').width(maxWidth)
		}
		$("body").ezBgResize({
			img : "../style/image/index/bg31.png", // Relative path example.  You could also use an absolute url (http://...).
			opacity : 1, // Opacity. 1 = 100%.  This is optional.
			center : true
		});
		$("#star").Score({
			value:"${rand.score}",
			onselect: function (index) {
				var bookId = "${book.bookId}";
				var userId = "${user.userId}";
				if(userId==null||userId==''){
					$.dialog.alert('用户未登录!');
					return false;
				}
				$.ajax({
				  url: "${ctx}/book/book!record.do",
				  type: "post",
				  data: "userId="+userId+"&bookId="+bookId+"&score="+index+"&type=00D",
				  cache: false,
				  success: function(msg){
				  }
				});
			}
    	})
	});
	function pinglun(userId){
		if(userId==null||userId==''){
			$.dialog.alert('用户未登录!');
			return false;
		}
		$.dialog({
		    id: 'a15',
		    width: '580px',
		    height: '210px',
		    title: '写评论',
		    data:{"userId":'${user.userId}',"face":'${user.face}',"bookId":"${book.bookId}"},
		    lock: true,
		    content: 'url:../include/pinglun.jsp',
		    padding: 0,
		    ok: function () {
		    	var plinfo = $.dialog.list['a15'].content.document.getElementById('plinfo').value;
		    	bookPinglun(plinfo);
		        return false;
		    },
		    cancel: true
		});
	}
</script>
<link href="${ctx}/style/css/index/focuscarousel.css" rel="stylesheet" />
<link rel="stylesheet" media="screen" href="${ctx}/style/css/index/superfish.css" />
<style type="text/css" media="screen">
.intro p {
	text-indent: 2em;
}
</style>
</head>
<body>
<%@ include file="/include/head.jsp"%>
	<div id="wrapper" style="background: #fff; padding: 10px; margin-top: 20px;">
		<h1>
			<span property="v:itemreviewed">${book.bookName}</span> <span
				class="clear"></span>
		</h1>
		<div id="content">
			<div class="grid-16-8 clearfix">
				<div class="article">
					<div class="indent">
						<div class="subjectwrap clearfix">

							<div class="subject clearfix">
								<div id="mainpic" class="">
									<a class="nbg" href="${book.images}" title="${book.bookName}">
										<img src="${book.images}" title="点击看大图" alt="${book.bookName}"
										rel="v:photo">
									</a>
								</div>
								<div id="info" class="">
									<span> <span class="pl"> 作者:</span> <span class="">${book.author}</span></span><br />
									<span class="pl">出版社:</span> ${book.publisher}<br /> <span
										class="pl"> 译者:</span> ${book.translator}<br /> <span
										class="pl">出版年:</span> ${book.pubdate}<br /> <span class="pl">页数:</span>
									${book.pages}<br /> <span class="pl">定价:</span> ${book.price}<br />
									<span class="pl">ISBN:</span> ${book.isbn13}<br />
								</div>
							</div>
							<div id="interest_sectl" class="">
								<div class="rating_wrap" rel="v:rating">
									<p class="rating_self clearfix" typeof="v:Rating">
										<span class="ll bigstar00"></span> <strong
											class="ll rating_num " property="v:average"></strong> <span
											property="v:best" content="10.0"></span>
									</p>
									<p class="rating_self font_normal clearbox">
										( <span class=""> <a href="collections">少于10人评价</a>
										</span> )
									</p>
									<span class="stars5 starstop" title="力荐"></span>
									<div class="power" style="width: 30px"></div>
									100.0%<br> 
									<span class="stars4 starstop" title="推荐"></span>
									<div class="power" style="width: 0px"></div>
									0.0%<br> 
									<span class="stars3 starstop" title="还行"></span>
									<div class="power" style="width: 0px"></div>
									0.0%<br> 
									<span class="stars2 starstop" title="较差"></span>
									<div class="power" style="width: 0px"></div>
									0.0%<br> 
									<span class="stars1 starstop" title="很差"></span>
									<div class="power" style="width: 0px"></div>
									0.0%<br>
								</div>
							</div>
						</div>
						<div class="clearfix">
							<a onclick="bookOperate('${user.userId}','${book.bookId}','00A');" rel="nofollow" class="j a_show_login colbutt ll"	name="pbtn-26363733-wish"> 
								<span>
									<input type="button" class="miniform minisubmit j " onclick="bookOperate('${user.userId}','${book.bookId}','00A');" value="想读"/>
								</span>
							</a> 
							<a onclick="bookOperate('${user.userId}','${book.bookId}','00B');" rel="nofollow" class="j a_show_login colbutt ll"	name="pbtn-26363733-do"> 
								<span>
									<input type="button" class="miniform minisubmit j " onclick="bookOperate('${user.userId}','${book.bookId}','00B');" value="在读"/>
								</span>
							</a> 
							<a onclick="bookOperate('${user.userId}','${book.bookId}','00C');" rel="nofollow" class="j a_show_login colbutt ll" name="pbtn-26363733-collect"> 
								<span>
									<input type="button" class="miniform minisubmit j " onclick="bookOperate('${user.userId}','${book.bookId}','00C');" value="读完"/>
								</span>
							</a>
							<div class="ll j a_stars">
								<span id="star"></span>
							</div>
						</div>
					</div>

					<br clear="all">
					<div id="collect_form_26363733"></div>
					<div class="related_info">
						<h2>
							<span class="">内容简介</span>
							&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;
						</h2>
						<div class="indent" id="link-report">
							<span class="short">
								<div class="intro">
									<p>${book.summary}</p>
								</div>
							</span>

						</div>

						<h2>
							<span class="">作者简介</span>
							&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;
						</h2>

						<div class="indent ">
							<div class="">

								<div class="intro">
									<p>${book.authorIntro}</p>
								</div>
							</div>
						</div>
						<div id="db-tags-section" class="blank20">
							<h2>
								<span class="">常用的标签</span>
							</h2>
							<div class="indent">
								<c:forTokens items="${book.commonTags}" delims="," var="nameTag">
									<span class=""> <a class="  tag">${nameTag}</a> &nbsp;
									</span>
								</c:forTokens>
							</div>
						</div>
					</div>
					<!-- 读书笔记 begin -->
					<div>
						<div class="mod-hd">
							<a class="redbutt j a_show_login rr" onclick="pinglun('${user.userId}');" rel="nofollow"><span>
									我来说两句 </span></a>
							<h2>
								<span class="">短评</span>
							</h2>
						</div>
						<c:if test="${readCommentSize=='0'}">
							<div class="h65">
								本书暂无短评。
							</div>
						</c:if>
						<c:forEach items="${readComment}" var="readComment">
							<div class="tlst" style="border-bottom: 1px dashed #DDD;margin-bottom: 15px;" >
								<div class="ilst">
									<a title="${book.bookName}"><img
										src="${readComment.userFace}" style="width: 40px;height: 50px;" alt="${readComment.userName}" /></a>
								</div>
								<div class="clst">
									<span class="ll user"> 
										<span class="starb">
											<a class=" ">${readComment.userName}</a>&nbsp;评论: 
											<a>${book.bookName}</a>
										</span>
									</span> <br /> <br />
									<div id='review_7490684_short' class="review-short">
										<span class="">${readComment.content}</span>
									</div>
									<div id="review_7490684_full" style="display: none"></div>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- 读书笔记 end -->

					<!-- 也喜欢读 begin -->
					<div class="clear"></div>
					<div class="section books-express " style="margin-top: 10px;">
						<div class="hd">
							<h2 class='' style="margin-bottom: 10px;">
								<span class="">喜欢读“${book.bookName}”的也喜欢读...</span>
							</h2>
						</div>
						<div class="bd">
							<div class="carousel">
								<div class="slide-list">
									<ul class="list-col list-col5 list-express slide-item">
										<c:forEach items="${randList}" var="randbook">
											<li class="">
												<div class="cover">
													<a href="${ctx}/book/book!view.do?bookId=${randbook.book_id}" title="${randbook.book_name}"> <img
														src="${randbook.images}" class="" width="106px"
														height="158px" alt="${randbook.book_name}">
													</a>
												</div>
												<div class="info">
													<div class="title">
														<a class="" href="${ctx}/book/book!view.do?bookId=${randbook.book_id}" title="${randbook.book_name}">${randbook.book_name}</a>
													</div>
													<div class="author">${randbook.author}</div>
													<!--浮层-->
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- 也喜欢读 begin -->
				</div>

				<div class="aside">

					<h2>谁读这本书?</h2>
					<div class="indent" id="collector">
						<c:forEach items="${readList}" var="readList">
							<div class="">
								<div class="ll">
									<a><img
										src="${readList.userFace}" class="pil"
										alt="${readList.userName}" /></a>
								</div>
								<div style="padding-left: 60px">
									<a class="">${readList.userName}</a><br />
									<div class="pl ll">
									${readList.createTime}&nbsp;&nbsp;&nbsp;
										<c:if test="${readList.type=='00A'}">
											想读
										</c:if>
										<c:if test="${readList.type=='00B'}">
											在读
										</c:if>
										<c:if test="${readList.type=='00C'}">
											读完
										</c:if>
									</div>
									<br />
								</div>
								<div class="clear"></div>
								<br />
								<div class="ul" style="margin-bottom: 12px;"></div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>

		</div>
		<div id="footer">
			<span id="icp" class="fleft gray-link">
				&copy; 2005－2015 douban.com, all rights reserved
			</span>
			
			<a href="#" style="display: none;"></a>
			<span class="fright">
				<a href="#">关于豆瓣</a>
				· <a href="#">在豆瓣工作</a>
				· <a href="#">联系我们</a>
				· <a href="#">免责声明</a>    
				· <a href="#">帮助中心</a>
				· <a href="#" target="_blank">开发者</a>
				· <a href="#">图书馆合作</a>
				· <a href="#">移动应用</a>
				· <a href="#">豆瓣广告</a>
			</span>
		  </div>
		  <div style="display: none;">
		  	<form method="POST" id="formA" action="${ctx}/book/book!record.do" class="miniform">
				<input type="hidden" id="bookId" name="bookId" value="${book.bookId}"/>
				<input type="hidden" id="userId" name="userId" value="${user.userId}"/>
				<input type="hidden" id="type" name="type" />
			</form>
		  </div>
		  <div style="display: none;">
		  	<form method="POST" id="formB" action="${ctx}/book/book!comment.do" class="miniform">
				<input type="hidden" id="bookIdB" name="bookIdB" value="${book.bookId}"/>
				<input type="hidden" id="userIdB" name="userIdB" value="${user.userId}"/>
				<input type="hidden" id="plinfoB" name="plinfoB"/>
				<input type="hidden" id="typeB" name="typeB" />
			</form>
		  </div>
	</div>
</body>
</html>

