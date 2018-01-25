<!-- 未使用 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>微特派-运单查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/web/frame/jquery.mobile-1.3.0.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/web/frame/ios_inspired/styles.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css"/>
<script type="text/javascript" charset="utf-8" src="./js/frame/iscroll.js"></script>
<script type="text/javascript" charset="utf-8" src="./js/frame/jquery-1.8.2.min.js"></script>
<script type="text/javascript" charset="utf-8" src="./js/frame/jquery.mobile-1.3.0.min.js"></script>
<script type="text/javascript" charset="utf-8" src="./js/global.js"></script>
<script type="text/javascript" src="/weixinems/js/publictrack.js"></script>
</head>

<body class="ui-mobile-viewport ui-overlay-c">
	<div data-role="page" id="orderCheckPage">
    <div data-role="content">
    <!-- <a href="#popupMenu" id="showProvSelect" data-rel="popup" data-role="button" data-inline="true" data-transition="slideup" data-icon="gear" data-theme="e">选择省份</a>
<div data-role="popup" id="popupMenu" data-theme="d">
        <ul id="provSelect" data-role="listview" data-inset="true" style="min-width:210px;" data-theme="d">
            <li data-role="divider" data-theme="e">北京</li>
            <li><a href="#">广州</a></li>
            <li><a href="#">上海</a></li>
            <li><a href="#">湖北</a></li>
            <li><a href="#">天津</a></li>
        </ul>
</div> -->
    
    
        <div data-role="fieldcontain">
            <input name="" id="searchinput1" placeholder="" value="" type="search"/>
        </div>
        
        <select id="selectProvId" style='font-color:blue;'>
        	<option value="0" style="color:blue" selected="selected">请选择省份</option>
        	<option value="1" style="color:blue">北京</option>
        	<option value="2" style="color:blue">广州</option>
        	<option value="3" style="color:blue">上海</option>
        	<option value="4" style="color:blue">湖北</option>
        	<option value="5" style="color:blue">天津</option>
        </select>
        
        <input id="checkOrderButton" data-inline="true" data-theme="b" data-icon="search" data-iconpos="left" value="查询" data-mini="true" type="submit"/>
            
        <ul id="showtrack" data-role="listview" style="margin-top:20px; background:"";">
		</ul> 
            
        <!-- <div id="showtrack" class="ui-grid-a">
            <div class="ui-block-a">
            </div>
            <div class="ui-block-b">
            </div>
            <div class="ui-block-a">
            </div>
            <div class="ui-block-b">
            </div>
            <div class="ui-block-a">
            </div>
            <div class="ui-block-b">
            </div>
            <div class="ui-block-a">
            </div>
            <div class="ui-block-b">
            </div>
            <div class="ui-block-a">
            </div>
            <div class="ui-block-b">
            </div>
            <div class="ui-block-a">
            </div>
            <div class="ui-block-b">
            </div>
        </div> -->
    </div>
</div>
</body>
</html>