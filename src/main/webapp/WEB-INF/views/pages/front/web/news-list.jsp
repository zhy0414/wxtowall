<!DOCTYPE html>
<html>
<head>
<meta charset="utf8"/>
<title>微特派-公司动态</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<meta name="generator" content=""/>
<meta name="author" content="zsoft"/>
<meta content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/css/front/web/gongsijianjie.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/web/min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/web/global.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/myutil.js"></script>
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/news-list.js"></script> -->
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/web/scroll.js"></script>


<body>
<div class="outter">
	<div class="wrap">
		<!-- 主体内容 -->
		<div class="lay-left">
			<div class="pos">
				<jsp:include page="../common/header_noback.jsp"></jsp:include>
				<div class="f_inews">
					<div class="w_nav" id="show_nav">
						<span>动态</span>
					</div>
					<ul class="f_inews_lis" id="f_inews_lis_ul">
						<c:forEach var="ar" items="${articles}">
						   	<li onclick="window.location.href='${pageContext.request.contextPath}/web/newsdetail?id=${ar.id}'">
								<a class="f_inewsimg" href="javascript:void(0);"><img src="${ar.titleImg}"/></a>
								<div class="f_inewscon">
									<h4><a href="javascript:void(0);">${ar.title}</a></h4>
									<p class="clears">${ar.overview}<span>></spain></p>
								</div>
							</li>
						</c:forEach>		
					</ul>
				</div>
				<!-- <div class="f_loadimg" id="f_loadimg">
					<img src="${pageContext.request.contextPath}/views/images/loading.gif"/>
				</div>
				<div class="f_ionload" id="div_getmorenews">
					<span style="color:red;font-size:14px;display:none;" id="span_timeout">加载超时</span>
					<a href="javascript:void(0)" id="a_getmorenews">点击加载更多</a>
				</div> -->
			</div>
		</div>
	</div>
</div>
</body>
</html>