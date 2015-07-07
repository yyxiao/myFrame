<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", -1);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/tag-page" prefix="pg" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />
<meta name="renderer" content="webkit"/>
<!-- css -->
<link rel="stylesheet" href="${ctx}/style/css/index/47783e39ae67de90.css"/>
<!-- js -->
<script type="text/javascript" src="${ctx}/style/js/jquery/jquery-1.7.2.min.js"></script>
<script src="${ctx}/style/js/lhgdialog/lhgdialog.min.js?self=true&skin=mac" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/style/js/jquery.validate/jquery.metadata.js"></script>
<script type="text/javascript" src="${ctx}/style/js/jquery.validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/style/js/jquery.validate/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/style/js/jquery.validate/extension.js"></script>
<script type="text/javascript" src="${ctx}/style/js/index/jquery.ez-bg-resize.js"></script>
<script type="text/javascript" src="${ctx}/style/js/public.js"></script>