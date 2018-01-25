<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>照片墙</title>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<link href="${pageContext.request.contextPath}/views/css/front/towall/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/views/css/front/towall/nian.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/views/css/front/towall/wall.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/jquery-1.11.2.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/towall/binaryajax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/towall/exif.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/towall/rotate.js"></script> --%>
<style>
.l2 .img1{
}
.l2{
	text-align:center;
	background:url(${pageContext.request.contextPath}/views/images/front/towall/wall_bg.png) repeat;
}
.content0{
	text-align:left;
}

</style>
</head>

<!-- <body onLoad="ajaxData();"  sroll="no"> -->
<body  sroll="no">
<img src="${pageContext.request.contextPath}/views/images/front/towall/monkey_ground.png" class="qiang_bg">

<div class="cont_bot" id="cont_bot">
	<div class="cont_in" id="cont_in">
		<ul class="chat-thread" id="chat-thread">
			<!--
			<li>Are we meeting today?</li>
			<li>yes, what time suits you?</li>
			<li>I was thinking after lunch, I have a meeting in the morning1111111111111111111 11111111111111221111111 1331111111441111551666666 666666666666666666667</li>
		  	<li>Are we meeting today?</li>
			<li>yes, what time suits you?</li>
			<li>I was thinking after lunch, I have a meeting in the morning1111111111111111111 11111111111111221111111 1331111111441111551666666 666666666666666666667</li>
		  	<li>Are we meeting today?</li>
			<li>yes, what time suits you?</li>
			<li>I was thinking after lunch, I have a meeting in the morning</li>
		  	<li><img style='width:300px;height:150px;' src="${pageContext.request.contextPath}/views/images/towall/monkey_ground.png"/></li>
		  	
			<li href="url(../../images/towall/face1.png)">yes, what time suits you?</li>
			<li href="../../images/towall/face1.png">I was thinking after lunch, I have a meeting in the morning</li>
			 -->
		</ul>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/views/js/front/towall/wall.js"></script>

<!-- <script type="text/javascript">
//自动变更img size
setImgSize();
var btn = document.getElementById('btn');
$(function(){
	// var n=0,
	// 	timer = null;
	// $(window).resize(function(){
	// 	setImgSize();
	// 	$(".qiang_bg").outerWidth( $(window).outerWidth() );
	// 	$(".qiang_bg").outerHeight( $(document).outerHeight() );
	// })
	// $(".qiang_bg").outerWidth( $(window).outerWidth() );
	// $(".qiang_bg").outerHeight( $(document).outerHeight() );

})

function ajaxData()
{
	//ajax 调用数据
	/* $.ajax({
		 type: "POST",
		 url: "./ajaxgetlastphoto",
		 data: "",
		 dataType: "JSON",
		 success: function(msg){
			 
			 setImgSize();			 
			 $('.l2 .img1').attr('src',msg[0]['photo']);
			 $('.l2 .content0').html("[" + msg[0]['user']['user_dep'] + "]" + msg[0]['content']);
			 $('.l1 img:eq(0)').attr('src',msg[1]['photo']);
			 $('.l1 img:eq(1)').attr('src',msg[2]['photo']);
			 $('.l3 img:eq(1)').attr('src',msg[3]['photo']);
			 $('.l3 img:eq(0)').attr('src',msg[4]['photo']);			 
			 
		 }
	}); */
	$.ajax({
		 type: "POST",
		 url: "./ajaxgetlasttxt",
		 data: "",
		 dataType: "JSON",
		 success: function(msg){
			 
			 //获得页面的留言信息
			 var p = $('.cont_in p').html();
			 
			 //处理刷新没有数据 ajax 动态从数据抓取数据
			 if(p==null && msg.length==0) {
					
					 $.ajax({
						 type: "POST",
						 url: "./ajaxgetlastmsg",
						 data: "",
						 dataType: "JSON",
						 success: function(msg){
								 var p = '';
								 //遍历数据
								 $(msg).each(function(i) {
									 p += "<p>[" + msg[i]['user']['user_dep'] + "]" + msg[i]['content'] + "</p>";
								 });
								 //传入信息
								 $('.cont_in').html(p);
						 }
					 });
					 return ;
			 } else {
					 var p = '';
					 var show_number = 7;
					 $(msg).each(function(i) {
						 p += "<p>[" + msg[i]['user']['user_dep'] + "]" + msg[i]['content'] + "</p>";
					 });
					 $('.cont_in').prepend(p); 
					 
					 //去除多余的p
					 var show_p = $('.cont_in').children('p');
					 if(show_p.length > show_number) {
					 		$('.cont_in p:last').remove();
					 }					 
			 }
		 }
	});
	setTimeout('ajaxData()',5000);
}

function setImgSize() {
	
	var w = $(".l2").width();
	var h = $(".l2").height();
	
	$('.l2 .img1').css('max-width', w);
	$('.l2 .img1').css('max-height', h);
	
	var img_w = $(".l2 .img1").width();
	var img_h = $(".l2 .img1").height();
	
	return ;
	
	if(w > img_w) {
		n_w = ((w-img_w)/2)+"px"
		$('.l2 .img1').css('left', n_w);
	}
	
	if(h>img_h){
		n_h = ((h-img_h)/2)+"px";
		$('.l2 .img1').css('top', n_h);
	}
	
}

</script> -->
</body>
</html>
