
$.extend({
	
	//����λ��  ԭ�ַ� �����ַ� �ܳ���  ��䷽��
	fn_str_pad:function(strsrc,strpad,len,direct){
		var len = len || 2;
		var direct = direct || 0;
		var allflag=1;  //0�� 1��
		

		
		direct = direct>2 ? 0 : direct;
		if(typeof(strsrc) != 'string'){
			strsrc = strsrc.toString();
		}
		if(strsrc.length>=len){
			return strsrc;
		}

		for(var i=strsrc.length;i<len;i++){
			switch(direct){
				case 0:
					strsrc = strpad + strsrc;
					break;
				case 1:
					strsrc += strpad;
					break;
				case 2:
					if(allflag%2){
						strsrc = strpad + strsrc;
			        }else{
			        	strsrc += strpad;
			        }
					break;
			}
		}
		return strsrc; 
	}
});

$(document).ready(function(){
	
	//�ϲ�����
	var iNum=0;
	var iTimer=null;
	$('#f_ifocus_lis > li').first().css({opacity:'1',zIndex:'2'});//Ĭ����ʾ��һ��ͼƬ
	$('#f_ifocus_btn > a').click(function(){
		iNum = $(this).index();//˳������
		$.tabSwitch();
	});

	$.extend({
		set_play:function(){
			iNum++;
			if(iNum==$('#f_ifocus_btn > a').length){
				iNum=0;
			}
			$.tabSwitch();
		},
		tabSwitch:function(){
			$('#f_ifocus_btn > a').removeClass();
			$('#f_ifocus_btn > a').eq(iNum).addClass('f_ifocus_cur');
			$('#f_ifocus_lis > li').css({opacity:'0',zIndex:'1'});
			$('#f_ifocus_lis > li').eq(iNum).stop(true,false).animate({opacity:'1'},500);
			$('#f_ifocus_lis > li').eq(iNum).css('z-index','2');
		}
	});

	iTimer=setInterval($.set_play,2000);
	
	
	
	/**
	 * ͼƬ���?ʼ
	 */	
	//����ͼƬ����
	var _objpic = {
		xhr:null, //ajax����	
		xhrtimer:null, //ͼƬ����ʱ��ʱ��	
		xhrtimeout:5000, //ajax����ʱʱ��	
		picload:[], //Ҫ����ͼƬ����	
		picloadtimer:null, //ͼƬ���ض�ʱ��	
		picloadtimeout:30, //ͼƬ����ʱ����
		picloadi:0, //��ǰͼƬ����	
		picloadj:0, //��ǰͼƬ���ش���	
		picattr:'truesrc', //ͼƬ��ʵ·������	
		picloadtimerout:null, //ͼƬ��ʵ·������	
		picloadtimeout:30, //ͼƬ��ʵ·������	
		picpid:document.getElementById('f_inews_lis_ul'), //ul id
		//��λ
		reset:function(){
			this.picloadi=0;
			this.picloadj=0;
		},
		//��������Ҫ����
		getloadpic:function(){
			this.picload=[];
			
			var _imgs = this.picpid.getElementsByTagName('img');
			for(var i=0,len=_imgs.length;i<len;i++){
				if(_imgs[i].getAttribute('src').indexOf("static/")!==-1){
					this.picload.push(_imgs[i]);
				}
			}
		},
		//����ͼƬ
		setloadpic:function(){
			var _this = this;
			
		   clearInterval(_this.picloadtimerout);
		   _this.picloadtimerout=setInterval(function(){
			   _this.getloadpic();
			   var picload = _this.picload;
			   if(picload.length>0){
		    	   for(var i=0,len=picload.length;i<len;i++){
		    		   picload[i].src=picload[i].getAttribute(_this.picattr);
		    	   }   
			   }else{
				   clearInterval(_this.picloadtimerout);  
			   }
	       },_this.picloadtimeout);
		}	
	};
	
	//ҳ������� ��ʼ����ͼƬ
	_objpic.setloadpic();

	
	//������ظ��
	$("#a_getmorenews").click(function(){
		
		$("#span_timeout").hide();  //��ʾ������ʧ
		$("#div_getmorenews").hide();//���ظ������
		$("#f_loadimg").show();//�ȴ�ͼƬ����

		//���峬ʱ����
		_objpic.xhrtimer = setTimeout(function(){
			_objpic.xhr.abort();//��ֹ����
			$("#f_loadimg").hide();//�ȴ�ͼƬ����
			$("#span_timeout").show(); //��ʾ���ֳ���
			$("#a_getmorenews").text('������¼���'); //��ʾ�����Ѻô���
			$("#div_getmorenews").show();//���ظ�����
		},_objpic.xhrtimeout);
		
		_objpic.xhr = $.ajax({
			url:'web.php@c=wapnews&a=getnewsajax&channelid='+_global_var._channelid+'&page='+_global_var._curpage+'&t='+(new Date()).valueOf(),
			dataType:'json',
			success:function(msg){
				//���ʱ��
				clearTimeout(_objpic.xhrtimer);
				
				//�ȴ�ͼƬ����
				$("#f_loadimg").hide();
				
				if(msg.error==0){
					//ƴ�����
					var _newslist=msg.newslist,
				        _strnewslist='';
					for(var i in _newslist){
						var _odate = new Date(parseInt(_newslist[i].publishtime+'000'));
						var _month = $.fn_str_pad(_odate.getMonth()+1,0,2);
						var _date =  $.fn_str_pad(_odate.getDate(),0,2);
						var _strdate = _month+'-'+_date;
						
						_strnewslist +='<li>';
						_strnewslist +='<a class="f_inewsimg" href="'+_newslist[i].newslink+'">';
						_strnewslist +='<img src="'+_global_var._staticdomain+'/static/wapnews/images/defaultpic.png" truesrc="'+_global_var._picdomain+'/'+_newslist[i].photo+'.180x120.jpg" />';
						_strnewslist +='</a>';
						_strnewslist +='<div class="f_inewscon">';
						_strnewslist +='<h4><a href="'+_newslist[i].newslink+'">'+_newslist[i].stitle+'</a></h4>';
						_strnewslist +='<p class="clears">'+_newslist[i].summary+'<span>'+_strdate+'</span></p>';
						_strnewslist +='</div>';
						_strnewslist +='</li>';
					}
					//ҳ��+1
					_global_var._curpage++;
					$("#f_inews_lis_ul").append(_strnewslist);

					$("#span_timeout").hide();  //��ʾ������ʧ
					$("#a_getmorenews").text('������ظ��').show();//���ظ�����
					$("#div_getmorenews").show();
				//�����
				}else if(msg.error==1){
					$("#div_getmorenews").text('û�и�����').css({color:'red'}).show();
				}else{
					//�쳣
				}
				
				//�������ͼƬ
				_objpic.setloadpic();
			}
		});
	});
	
	
	//������
	var _obj_query = $("#query");
	var _default_text = $.trim(_obj_query.val());
	_obj_query.focus(function(){
		if($.trim(_obj_query.val()) == _default_text){
			_obj_query.val('');
		}
	}).blur(function(){
		if($.trim(_obj_query.val()) == ''){
			_obj_query.val(_default_text);
		}
	});
	
	//���form�ύ
	$("#form_search").submit(function(){
		if($.trim(_obj_query.val())=='' || $.trim(_obj_query.val())==_default_text){
			return false;
		}
		return true;
	});
	
	
	/**
	 * ���ض�������
	 */
	var _objscroll = {
		win:$(window),	
		doc:$(document),	
		gotopdiv:$('#gotop')	
	};
 	
	_objscroll.win.scroll(function(){
		if(_objscroll.win.scrollTop() > _objscroll.win.height()){
			_objscroll.gotopdiv.show();
		}else{
			_objscroll.gotopdiv.hide();
		}
	});
	
	//���ض������
	_objscroll.gotopdiv.click(function(){//���Ʒ��ض���
		_objscroll.win.scrollTop(0);
		return false;
	}); 
	
	
	
	
});