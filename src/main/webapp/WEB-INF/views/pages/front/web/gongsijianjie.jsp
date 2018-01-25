<!DOCTYPE html>
<html>
<head>
<meta charset="utf8"/>
<title></title>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/web/gongsijianjie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/web/scroll.js"></script>
<script type="text/javascript">
/**
 * 全局变量
 */
var _global_var={
	_curpage:2, //当前页码+1	
	_picdomain:"http://img10.fblife.com/", //图片域名
	_wapdomain:"./", //wap域名
	_staticdomain:"../static.fblife.com/", //静态资源域名
	_channelid:0, //频道id
	_default_headpic:function(){return _global_var._staticdomain+'/static/wapnews/images/tag.png';}
};
</script>
<script type="text/javascript">
$(document).ready(function(){
	//wap首页焦点图片滚动
	if(document.getElementById("slide_01")){
		var slide_01 = new ScrollPic();
		slide_01.scrollContId   = "slide_01"; //内容容器ID
		slide_01.dotListId      = "slide_01_dot";//点列表ID
		slide_01.dotOnClassName = "f_ifocus_cur";
		slide_01.arrLeftId      = "sl_left"; //左箭头ID
		slide_01.arrRightId     = "sl_right";//右箭头ID
		slide_01.frameWidth     = 320;
		slide_01.pageWidth      = 320;
		slide_01.upright        = false;
		slide_01.speed          = 10;
		slide_01.space          = 30; 
		slide_01.initialize(); //初始化
	}
});
</script>
</head>
<body>
<!-----最外层---->
<div class="outter">
	<div class="wrap">
		<!-- 主体内容 -->
		<div class="lay-left">
			<div class="pos">
				<jsp:include page="../common/header_noback.jsp"></jsp:include>
				<div class="m320 f_ifocus">
					<div id="slide_01" class="f_ifocus_lis">
							<div class="f_ifocus_con">
								<a href="javascript:void(0);"><img src="http://www.vtepai.com/images/banner/banner_pic1.jpg"/></a>
								<p><!-- <a href="news/show_112688.html">带屁股的小钢炮 试驾进口奥迪A3 40TFSI</a> --></p>
							</div>
							<div class="f_ifocus_con">
								<a href="javascript:void(0);"><img src="http://www.vtepai.com/images/banner/banner_pic2.jpg"/></a>
								<p><!-- <a href="../www.fb.cn/daloorv">大陆房车</a> --></p>
							</div>
							<div class="f_ifocus_con">
								<a href="javascript:void(0);"><img src="http://www.vtepai.com/images/banner/banner_pic3.jpg"/></a>
								<p><!-- <a href="news/show_90393.html">合纵连横 话说发动机纵横时代（历史篇）</a> --></p>
							</div>
							<div class="f_ifocus_con">
								<a href="javascript:void(0);"><img src="http://www.vtepai.com/images/banner/banner_pic4.jpg"/></a>
								<p><!-- <a href="news/show_113021.html">对比试驾：路虎极光vs奔驰GLA</a> --></p>
							</div>
							<div class="f_ifocus_con">
								<a href="javascript:void(0);"><img src="http://www.vtepai.com/images/banner/banner_pic5.jpg"/></a>
								<p><!-- <a href="news/show_113021.html">对比试驾：路虎极光vs奔驰GLA</a> --></p>
							</div>
							<div class="f_ifocus_con">
								<a href="javascript:void(0);"><img src="http://www.vtepai.com/images/banner/banner_pic6.jpg"/></a>
								<p><!-- <a href="news/show_113021.html">对比试驾：路虎极光vs奔驰GLA</a> --></p>
							</div>
					</div>
					<div id="slide_01_dot" class="f_ifocus_btn">
						
					</div>
				</div>
				
				<div class="m320 f_ifocus" id="page_content">
					${content}
				</div>
			</div>
		</div>
	</div>
</div>
</body>