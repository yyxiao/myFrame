<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>首页</title>
<%@ include file="/include/meta.jsp"%>
<link href="${ctx}/style/css/master.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/style/css/index/focuscarousel.css" rel="stylesheet" />
<link rel="stylesheet" media="screen" href="${ctx}/style/css/index/superfish.css" />
<script src="${ctx}/style/js/index/focuscarousel.js"></script>
<script type="text/javascript" src="${ctx}/style/js/index/hoverIntent.js"></script>
<script type="text/javascript" src="${ctx}/style/js/index/superfish.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("body").ezBgResize({
			img : "style/image/index/bg31.png", // Relative path example.  You could also use an absolute url (http://...).
			opacity : 1, // Opacity. 1 = 100%.  This is optional.
			center : true
		// Boolean (true or false). This is optional. Default is true.
		});
	});
</script>
</head>
<body>
<%@ include file="/include/head.jsp"%>
	<div style="clear: both;"></div>
	<div id="wrapper" style="background: #fff; padding: 10px; margin-top: 20px;">
		<div id="content">
			<div class="grid-16-8 clearfix">
				<div class="article">
					<div class="container">
						<div class="list1" style="left: -600px;">
							<img src="${ctx}/style/image/index/5.jpg" alt="1" /> 
							<img src="${ctx}/style/image/index/1.jpg" alt="1" />
							<img src="${ctx}/style/image/index/2.jpg" alt="2" /> 
							<img src="${ctx}/style/image/index/3.jpg" alt="3" />
							<img src="${ctx}/style/image/index/4.jpg" alt="4" /> 
							<img src="${ctx}/style/image/index/5.jpg" alt="5" />
							<img src="${ctx}/style/image/index/1.jpg" alt="5" />
						</div>
						<div class="buttons">
							<span index="1" class="on">1</span> 
							<span index="2">2</span> 
							<span index="3">3</span> 
							<span index="4">4</span> 
							<span index="5">5</span>
						</div>
						<a href="javascript:;" class="prev arrow">&lt;</a> 
						<a href="javascript:;" class="next arrow">&gt;</a>
					</div>
					<!-- 学校推荐 begin -->
					<div class="section books-express ">
						<div class="hd">
							<h2 class=''>
								<span class="">学校推荐</span> 
								<span class="link-more"> 
									<a class="" href="#">更多»</a>
								</span>
							</h2>
						</div>
						<div class="bd">
							<div class="carousel">
								<div class="slide-list">
									<ul class="list-col list-col5 list-express slide-item">
										<c:forEach items="${booklist}" var="book">
											<li>
												<div class="cover">
													<a href="${ctx}/book/book!view.do?bookId=${book.book_id}" title="${book.book_name}"> 
													<img src="${book.images}" class=""
														width="106px" height="158px" alt="${book.book_name}"/>
													</a>
												</div>
												<div class="intervenor-info">
													<img
														src="http://img3.douban.com/f/book/ef040178fab1770d60e3f2f12ba4c7aa70714396/pics/book/partner/jd_recommend.png"
														class='jd-icon' width="16" height="16" /> <span>学校推荐</span>
												</div>
												<div class="info">
													<div class="title">
														<a class="" href="${ctx}/book/book!view.do?bookId=${book.book_id}" title="${book.book_name}">${book.book_name}</a>
													</div>
													<div class="author">${book.author}</div>
													<!--浮层-->
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- 学校推荐 end -->

					
					<!-- 阅读排行 begin -->
					<div class="section books-express ">
						<div class="hd">
							<h2 class=''>
								<span class="">阅读排行榜</span> 
							</h2>
						</div>
						<div class="bd">
							<div class="carousel">
								<div class="slide-list">
									<ul class="list-col list-col5 list-express slide-item">
										<c:forEach items="${readList}" var="readbook">
											<li class="">
												<div class="cover">
													<a href="${ctx}/book/book!view.do?bookId=${readbook.id}" title="${readbook.bookname}"> <img
														src="${readbook.images}" class=""
														width="106px" height="158px" alt="${readbook.bookname}"/>
													</a>
												</div>
												<div class="intervenor-info">
													<img
														src="http://img3.douban.com/f/book/ef040178fab1770d60e3f2f12ba4c7aa70714396/pics/book/partner/jd_recommend.png"
														class='jd-icon' width="16" height="16" /> <span>${readbook.time}</span>
												</div>
												<div class="info">
													<div class="title">
														<a class="" href="${ctx}/book/book!view.do?bookId=${readbook.id}" title="${readbook.bookname}">${readbook.bookname}</a>
													</div>
													<div class="author">${readbook.author}</div>
													<!--浮层-->
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- 阅读排行 end -->
					
					<!-- 评分排行 begin -->
					<div class="section books-express ">
						<div class="hd">
							<h2 class=''>
								<span class="">评分排行榜</span> 
							</h2>
						</div>
						<div class="bd">
							<div class="carousel">
								<div class="slide-list">
									<ul class="list-col list-col5 list-express slide-item">
										<c:forEach items="${scoreList}" var="scorebook">
											<li class="">
												<div class="cover">
													<a href="${ctx}/book/book!view.do?bookId=${scorebook.id}" title="${scorebook.bookname}"> <img
														src="${scorebook.images}" class=""
														width="106px" height="158px" alt="${scorebook.bookname}"/>
													</a>
												</div>
												<div class="intervenor-info">
													<img
														src="http://img3.douban.com/f/book/ef040178fab1770d60e3f2f12ba4c7aa70714396/pics/book/partner/jd_recommend.png"
														class='jd-icon' width="16" height="16" /> <span>${scorebook.score}</span>
												</div>
												<div class="info">
													<div class="title">
														<a class="" href="${ctx}/book/book!view.do?bookId=${scorebook.id}" title="${scorebook.bookname}">${scorebook.bookname}</a>
													</div>
													<div class="author">${scorebook.author}</div>
													<!--浮层-->
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- 评分排行 end -->

					<!-- 推荐出版社 begin -->
					<div class="section ebook-area">
						<div class="hd">
							<h2 class=''>
								<span class="">推荐出版社作品</span>
							</h2>
						</div>
						<div class="bd">
							<div class="carousel">
								<div class="slide-list">
									<ul class="list-col list-col5">
										<c:forEach items="${publishList}" var="publish">
											<li class="">
												<div class="cover">
													<a href="${ctx}/book/book!view.do?bookId=${publish.book_id}" title="${publish.book_name}"> 
														<img src="${publish.images}" alt="${publish.book_name}" width="106px" height="158px"/>
													</a>
												</div>
												<div class="info">
													<div class="title">
														<a href="${ctx}/book/book!view.do?bookId=${publish.book_id}" title="${publish.book_name}" target="_blank">
															${publish.book_name} </a>
													</div>
													<div class="price">${publish.author}</div>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- 推荐出版社 end -->
				</div>
				<div class="aside">
					<!-- douban ad begin -->
					<div id="dale_book_home_top_right" class="s ad-placeholder"
						data-dstat-areaid="51" data-dstat-mode="click,expose"
						style="margin-top: 30px;"></div>
					<!-- douban ad end -->

					<!-- douban ad begin -->
					<div id="dale_book_home_top_right2" class="ad-placeholder"></div>
					<!-- douban ad end -->

					<h2 class=''>
						<span class="">热门标签</span> 
						<span class="link-more"> 
							<a class="" href="/tag/?view=type&amp;icn=index-sorttags-all">所有热门标签»</a>
						</span>
					</h2>

					<ul class="hot-tags-col5 s" data-dstat-areaid="54"
						data-dstat-mode="click,expose">
						<li>
							<ul class="clearfix">
								<li class="tag_title">类别</li>
								<li><a href="#" class="tag">哲学</a></li>
								<li><a href="#" class="tag">社会</a></li>
								<li><a href="#" class="tag">政治</a></li>
								<li class="last"><a href="#" class="tag">文学</a></li>
								<li><a href="#" class="tag">艺术</a></li>
								<li><a href="#" class="tag">科幻</a></li>
								<li><a href="#" class="tag">画本</a></li>
								<li><a href="#" class="tag">画本</a></li>
								<li><a href="#" class="tag">散文</a></li>
								<li><a href="#" class="tag">小说</a></li>
								<li class="last"><a href="#" class="tag">诗歌</a></li>
								<li class="last"><a href="#" class="tag more_tag">更多》</a></li>
							</ul>
						</li>

						<li>
							<ul class="clearfix">
								<li class="tag_title">推荐</li>
								<li><a href="#" class="tag">学校</a></li>
								<li><a href="#" class="tag">教师</a></li>
								<li><a href="#" class="tag">学生</a></li>
								<li><a href="#" class="tag">读书活动</a></li>
								<li class="last"><a href="#" class="tag more_tag">更多》</a></li>
							</ul>
						</li>

						<li>
							<ul class="clearfix">
								<li class="tag_title">年级</li>
								<li><a href="#" class="tag">一年级</a></li>
								<li><a href="#" class="tag">二年级</a></li>
								<li><a href="#" class="tag">三年级</a></li>
								<li><a href="#" class="tag">四年级</a></li>
								<li><a href="#" class="tag">五年级</a></li>
								<li><a href="#" class="tag">六年级</a></li>
								<li class="last"><a href="#" class="tag more_tag">更多》</a></li>
							</ul>
						</li>

						<li>
							<ul class="clearfix">
								<li class="tag_title">学科</li>
								<li><a href="#" class="tag">语文</a></li>
								<li><a href="#" class="tag">数学</a></li>
								<li><a href="#" class="tag">外语</a></li>
								<li><a href="#" class="tag">美术</a></li>
								<li><a href="#" class="tag">科学</a></li>
								<li><a href="#" class="tag">体育</a></li>
								<li><a href="#" class="tag">计算机</a></li>
								<li class="last"><a href="#" class="tag more_tag">更多》</a></li>
							</ul>
						</li>
					</ul>
					<!-- douban ad begin -->
					<div id="dale_book_homepage_right_bottom" class="ad-placeholder"></div>
					<!-- douban ad end -->
					<div class="block5">
						<h2 class=''>
							<span class="">阅读达人</span>
						</h2>
						<ul class="entry-list-col2s s " style="padding-bottom: 1px;">
							<c:forEach items="${userList}" var="userlist">
								<li class="noline" style="border-bottom: 1px dashed #dddddd;padding-top: 5px;">
									<img class="userface" alt="${userlist.name}"
									src="http://img3.douban.com/icon/u4050587-5.jpg"></img>
									<div class="fleft w210" style="width: 190px;">
										<p>
											<span>${userlist.name}</span>
										</p>
										<p class="entry-star-small clearfix">
											<span class="fleft"></span>
										</p>
										<p class="color-gray">
											<span class="">${userlist.school} </span>
										</p>
									</div> <span style="font-size: 12px; color: #37A;">${userlist.read_num}本</span>
								</li>
							</c:forEach>
						</ul>
					</div>
					<!-- 猜你喜欢 begin -->
					<div class="section weekly-top">
						<div class="hd">
							<h2>
								<span class="">猜你喜欢</span>
								<span class="link-more"> 
									<a class="" href="${ctx}/index!randomBook.do">换一批»</a>
								</span>
							</h2>
						</div>
						<div class="bd">
							<ul class="list list-ranking">
								<c:forEach items="${randList}" var="randbook" varStatus="status">
									<li class="item"><span class="rank-num">${status.count}.</span>
										<div class="book-info">
											<a href="${ctx}/book/book!view.do?bookId=${randbook.book_id}" class="name">${randbook.book_name}</a>
											<div class="author">${randbook.author}</div>
										</div> <a href="${ctx}/book/book!view.do?bookId=${randbook.book_id}"><span class="buy-button">去阅读</span></a>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<!-- 猜你喜欢 end -->
					<!-- douban ad begin -->
					<div id="dale_book_home_bottem_right" class="ad-placeholder"></div>
					<!-- douban ad end -->
				</div>
				<div class="extra"></div>
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
		</div>
	</div>
</body>
</html>
