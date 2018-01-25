<!-- 未使用 -->
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/css/front/gongsijianjie.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/myutil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/scroll.js"></script>


<body>
<div class="outter">
	<div class="wrap">
		<!-- 主体内容 -->
		<div class="lay-left">
			<div class="pos">
				<jsp:include page="../common/header.jsp"></jsp:include>
				<div class="f_inews">
					<div class="w_nav" id="show_nav">
						<a href="${pageContext.request.contextPath}/web/hireinfo">招聘</a>&gt;<a href="javascript:void(0);">${cityName}</a>
					</div>
					<ul class="f_inews_lis" id="f_inews_lis_ul">
						<c:forEach var="ar" items="${articles}">
							<li onclick="window.location.href='${pageContext.request.contextPath}/web/hireinfodetail?cityid=${cityid}&id=${ar.id}'">
								<a class="f_inewsimg" href="javascript:void(0);"><img src="${ar.titleImg}"/></a>
								<div class="f_inewscon">
									<h4><a href="javascript:void(0);">${ar.title }</a></h4>
									<p class="clears"><fmt:formatDate type="both" value="${ar.createTime }"/><span>></spain></p>
								</div>
							</li>
						</c:forEach>
					   				
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>