var usersJson = "";

$(function(){
	//console.log("初始"+$("#chat-thread li").length);
	change_position();
	
	ajax_wall_users();
	ajax_new_message();
	
	setInterval(function(){
		ajax_new_message();
		
	}, 5000);
	
	setInterval(function(){
		ajax_wall_users();
	}, 20000);
})

$(window).resize(function(){
	change_position();
});

function change_position(){
	var width = $(window).width();
	var height = $(window).height();
	
	$("#cont_bot").css("height", height-60+"px");
	$("#cont_in").css("height", height-60+"px");
	$("#chat-thread").css("height", height-84+"px");
	$("#cont_bot").css("width", width-320+"px");
	$("#cont_in").css("width", width-320+"px");
	$("#chat-thread li").css("max-width", ((Number(width-320)*Number(0.8)).toFixed(1)-Number(200)).toFixed(0)+"px");
}

//获取用户列表
function ajax_wall_users(){
	$.ajax({
		url: "./ajaxwallusers?var="+(new Date()).getTime(),
		type: "POST",
		dataType: "text",
		success: function(value){
			
			
			if(value != usersJson){
				$("head style").html("");
				$("head style").append(value);
				
				/*console.log("value is:"+value);
				console.log("usersJson is:"+usersJson);
				var json = $.parseJSON(value);
				$("head style").html("");
				for(var i=0;i<json.length;i++){
					//$("head style").append("#chat-thread li:nth-child(odd)."+json[i].openid+":before{background-image: url(../views/images/towall/face1.png)}");
					$("head style").append("#chat-thread li:nth-child(odd)."+json[i].openid+":before{background-image: url("+json[i].headimgurl+"); background-size:50px 50px; background-repeat:no-repeat; right:-80px;}");
					$("head style").append("#chat-thread li:nth-child(even)."+json[i].openid+":before{background-image: url("+json[i].headimgurl+"); background-size:50px 50px; background-repeat:no-repeat; left:-80px;}");
				}*/
				usersJson = value;
			}
			
		}
	});
}



//获取新消息
function ajax_new_message(){
	$.ajax({
		url: "./ajaxwallmessage?var="+(new Date()).getTime(),
		type: "POST",
		dataType: "text",
		success: function(value){
			
			
			if(value != ""){
				var json = $.parseJSON(value);
				
				console.log(Number($("#chat-thread li").length) + Number(json.length));
				
				var delnum = Number($("#chat-thread li").length) + Number(json.length) - 20;
				
				if(delnum > 0){
					if(delnum%2 == 1){
						delnum++;
					}
					$("#chat-thread").find("li:lt("+delnum+")").remove();
				}
				
				var liStr;
				for(var i=0; i<json.length; i++){
					liStr = "";
					if(json[i].msgType == 0){
						liStr = "<li class='"+json[i].openid+"'><span style='vertical-align:top;'>["+json[i].username+"]</span>"+json[i].msgTextPc+"</li>";
					} else if(json[i].msgType == 1) {
						liStr = "<li class='"+json[i].openid+"'><span style='vertical-align:top;'>["+json[i].username+"]</span><img style='max-width:400px; max-height:200px;' src='"+json[i].msgPath+"'/></li>";
					} else if(json[i].msgType == 2) {
						liStr = "<li class='"+json[i].openid+"'><span>["+json[i].username+"]</span>发送语音消息！</li>";
					}
					
					$("#chat-thread").append(liStr);
				}
			}
			
			
			$("#chat-thread").scrollTop($("#chat-thread")[0].scrollHeight);
		}
	})
}