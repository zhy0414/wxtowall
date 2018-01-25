<!DOCTYPE html>
<html>
<head>
<meta charset="utf8"/>
<title>微特派-招聘信息</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<meta name="generator" content=""/>
<meta name="author" content="zsoft"/>
<meta content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/css/front/web/detail.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/web/min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/myutil.js"></script>
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/news-detail.js"></script> -->
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/web/scroll.js"></script>


<body>
<div class="outter">
	<div class="wrap">
		<!-- 主体内容 -->
		<div class="lay-left">
			<div class="pos">
				<jsp:include page="../common/header.jsp"></jsp:include>
				<div class="w_rizhut" id="zhtushow">
					<div class="w_nav" id="show_nav">
						<a href="${pageContext.request.contextPath}/web/newslist">动态</a>&gt;<a href="javascript:void(0);">${title}</a>
					</div>
					
					<div class="w_h">
						<h1 id="show_title">${title}</h1>
						<div class="w_newsti" id="show_overview">${overview}</div>
					</div>
					<div class="w_con clearfix" id="show_content">
						${content}
					</div>
				</div>						
			</div>
		</div>
	</div>
</div>
</body>
</html>