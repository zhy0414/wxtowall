<!doctype html>
<html>
<head>
<title>注册</title>
<meta charset="utf-8">
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<link rel="icon" href="../favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon" />
<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1;" id="viewport" name="viewport" />
<!--离线应用的另一个技巧-->
<meta content="yes" name="apple-mobile-web-app-capable" />
<!--指定的iphone中safari顶端的状态条的样式-->
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<!--告诉设备忽略将页面中的数字识别为电话号码-->
<meta content="telephone=no" name="format-detection" />

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/views/css/towall/regist.css" />

</head>
<body>
	
	<div class="com-content">

		<div class="com-content-area" id="js-com-content-area">
			<!--content-->
			<div class="page-role login-page">
				

				<div class="page-title">
					<!-- <a href="javascript:history.back();void(0)" class="return">返 回</a> -->
					注册
					<!-- <a href="../register/default.htm">注册<i></i></a> -->
				</div>
				<form autocomplete="off" class="pxui-form-info" id="js-login-form"
					method="post" action="/member/login?act=checklogin">
					<input type="hidden" name="rtnurl" value="index" />
					<p class="error-msg" id="js-error-msg"></p>
					<div>
						<span>姓&nbsp;&nbsp;&nbsp;&nbsp;名</span>
						<p>
							<span><input name="username" id="js-username"
								placeholder="真实姓名" type="text" value="" /></span>
						</p>
					</div>
					<div>
						<span>手&nbsp;&nbsp;&nbsp;&nbsp;机</span>
						<p>
							<span><input name="phone" id="js-phone" placeholder="手机号码" type="text" value="" /></span>
						</p>
					</div>
					<div>
						<span>&nbsp;</span>
						<p>
							<span> <input type="submit" id="js-login"
								value="   注  册   " class="pxui-light-button" /> 
								<!-- <a class="pxui-gray-button" href="forget_phone">找回密码</a> -->
							</span>
						</p>
					</div>
				</form>
				<h3 class="pxui-color-blue">小伙伴们要注意啊</h3>
				<div class="pxui-form-info pxui-other-login">
					<a href="apis@t=sina&r=1" class="pxui-button"><i></i>新浪微博账号登录</a> <a
						href="apis@t=qq&r=1" class="pxui-button"><i></i>QQ登录</a> <a
						href="apis@t=alipay&r=1" class="pxui-button"><i></i>支付宝登录</a> <a
						href="apis@t=360&r=1" class="pxui-button"><i></i>360登录</a>
				</div>
			</div>
			<!--content-end-->
		</div>
		<div class="com-footer-area" id="js-com-footer-area">
			<!-- <div class="com-footer-nav">
				<a href="../default.htm">首页</a><a href="../help/index.html">帮助中心</a><a
					href="../feedback/index.html">反馈建议</a>
			</div>
			<div class="com-footer">
				<p class="com-policy">
					<strong> <a class="pxui-color-white"
						href="javascript:void(0)"> <i></i> <span>自营商品<br />
								满99包邮
						</span>
					</a>&nbsp;&nbsp;&nbsp;&nbsp; <a class="pxui-color-white"
						href="javascript:void(0)"> <i
							style="background-position: -40px -108px;"></i> <span>15天无理由<br />
								免邮退换货
						</span>
					</a>
					</strong>
				</p>
				<br /> <br />
				<p>
					<strong> <a style="color: #769fbf;" href="default.htm">登录</a>&nbsp;&nbsp;
						<a style="color: #769fbf;" href="../register/default.htm">注册</a>
					</strong>
				</p>
				<br />
				<p>
					<strong> <a href="../../wap.paixie.net/default.htm">极速版</a>&nbsp;&nbsp;
						<a href="../default.htm">触屏版</a>&nbsp;&nbsp; <a
						href="../help/app.html">客户端</a>
					</strong>
				</p>
				<br /> © 2007-2013 Paixie All Rights Reserved<br /> 闽B2-20110084
			</div> -->
		</div>
	</div>
	
</body>
</html>
