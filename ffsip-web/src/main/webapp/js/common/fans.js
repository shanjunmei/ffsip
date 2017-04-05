//关注
function doFollow(userObj,obj) {
     if (user == null || user == '') {
         return;
     }
     $.ajax({
         url: BasePath+"/fans/addFans.do",
			 data:{'fansCode':user.code,'subscribeCode':userObj},
         method: "post",
         dataType: "json",
         success: function (data) {
             if (data.status == 'isSelf') {
            	 notify("你不能关注自己！");
                 return;
             }
             if (data.status == 'success') {
				$(obj).attr("class", "btn fill-blue");
				$(obj).attr("onclick", "doUnFollow('"+userObj+"',this)");
				$(obj).html("已关注");
				var fansNum = $("#fansNum").val();
				$("#fansNum").val(fansNum*1+1);
				$("#fansNumSpan").html('粉丝 ' + (fansNum*1+1));
				notify("关注成功！");
             } else {
            	 notify("关注失败！请稍后重试。");
             }
         }
     })
 }
 function doUnFollow(userObj,obj) {			//取消关注
     if (user == null || user == '') {
         return;
     }
     $.ajax({
         url: BasePath+"/fans/removeFans.do",
			 data:{'fansCode':user.code,'subscribeCode':userObj},
         method: "post",
         dataType: "json",
         success: function (data) {
        	 if (data.status == 'success') {
				$(obj).attr("class", "btn fill-green");
				$(obj).attr("onclick", "doFollow('"+userObj+"',this)");
				$(obj).html("<span class=\"font-jia\">+</span>关注");
				var fansNum = $("#fansNum").val();
				$("#fansNum").val(fansNum*1-1);
				$("#fansNumSpan").html('粉丝 ' + (fansNum*1-1));
				notify("取关成功！")
             } else {
                 notify("取消失败！请稍后重试。");
             }
         }
     })
 }
 

 function subscribeNumLoad(){			//关注的人
     if (user == null || user == '') {
         return;
     }
 	var pageIndex = $("#subscribePageIndex").val()*1 + 1;
 	var pageTotal = $("#subscribePageTotal").val();
 	var memberCode = $("#memberCode").val();
 	if(pageIndex <= pageTotal){		
 		$.ajax({url:BasePath+"/fans/fansLoad.do",
 			data:{'pageIndex':pageIndex,'memberCode':memberCode,'type':'0'},
 			async:false,
 			success: function(res){
 				var str = '';			
 				$.each(res.fansList,function(n,user) {
 					name = user.member.wxNickName;
 					introduction = user.member.remarks;
 					img = user.member.wxHeadimgurl;
 					if(user.company != null){
 						name = user.company.name;
 						introduction = user.company.introduction;
 						img = user.company.logoImg;						
 					}
 					str += 	'<li>'+					
 					'<div class="user-info"><div onclick="location.href = \''+BasePath+'/WxArticle/list.do?memberCode='+user.member.code+'\'">'+
 		            '<div class="avatar" style="background-image: url(\''+img+'\');"></div>'+
 		            '<div class="info">'+
 		            '    <p class="name">'+name+'</p>';		            
 		            if(introduction != null){
 		            	str += '<p class="about">'+introduction+'</p>';
 		            }		            
 		            str +='    <p class="st">'+
 		            '        <span>关注 '+user.member.subscribeNum*1+'</span>'+
 		            '        <input type="hidden" id="fansNum" value="'+user.member.fansNum*1+'">'+
 		            '        <span id="fansNumSpan">粉丝 '+user.member.fansNum*1+'</span>'+
 		            '        <span>文章'+user.member.articleNum*1+'</span>'+
 		            '    </p>'+
 		            '</div></div>';
 		            if(user.isFans*1 != 1){
 		            	str += '<div class="btn fill-green" onclick="doFollow(\''+user.member.code+'\',this)"><span class="font-jia">+</span>关注</div>';
 		            }else if(user.isFans*1 == 1){
 		            	str += '<div class="btn fill-blue" onclick="doUnFollow(\''+user.member.code+'\',this)">已关注</div>';
 		            }	            
 		            str += '</div></li>';
 				});
 				$("#subscribeNumUlhtml").append(str);
 				$("#subscribePageIndex").val(pageIndex);
 				$("#subscribePageTotal").val(res.pageTotal);
 				if(pageIndex == res.pageTotal){
 					$("#subscribeA").attr("style","display: none;");
 				}
 		     }
 		});		
 	}
 }

 function fansNumLoad(){			//粉丝
     if (user == null || user == '') {
         return;
     }
 	var pageIndex = $("#fansNumPageIndex").val()*1 + 1;
 	var pageTotal = $("#fansNumPageTotal").val();
 	var memberCode = $("#memberCode").val();
 	if(pageIndex <= pageTotal){		
 		$.ajax({url:BasePath+"/fans/fansLoad.do",
 			data:{'pageIndex':pageIndex,'memberCode':memberCode,'type':'1'},
 			async:false,
 			success: function(res){
 				var str = '';			
 				$.each(res.fansList,function(n,user) {
 					name = user.member.wxNickName;
 					introduction = user.member.remarks;
 					img = user.member.wxHeadimgurl;
 					if(user.company != null){
 						name = user.company.name;
 						introduction = user.company.introduction;
 						img = user.company.logoImg;						
 					}
 					str += 	'<li>'+					
 					'<div class="user-info"><div onclick="location.href = \''+BasePath+'/WxArticle/list.do?memberCode='+user.member.code+'\'">'+
 		            '<div class="avatar" style="background-image: url(\''+img+'\');"></div>'+
 		            '<div class="info">'+
 		            '    <p class="name">'+name+'</p>';		            
 		            if(introduction != null){
 		            	str += '<p class="about">'+introduction+'</p>';
 		            }		            
 		            str +='    <p class="st">'+
 		            '        <span>关注 '+user.member.subscribeNum*1+'</span>'+
 		            '        <input type="hidden" id="fansNum" value="'+user.member.fansNum*1+'">'+
 		            '        <span id="fansNumSpan">粉丝 '+user.member.fansNum*1+'</span>'+
 		            '        <span>文章'+user.member.articleNum*1+'</span>'+
 		            '    </p>'+
 		            '</div></div>';
 		            if(user.isFans*1 != 1){
 		            	str += '<div class="btn fill-green" onclick="doFollow(\''+user.member.code+'\',this)"><span class="font-jia">+</span>关注</div>';
 		            }else if(user.isFans*1 == 1){
 		            	str += '<div class="btn fill-blue" onclick="doUnFollow(\''+user.member.code+'\',this)">已关注</div>';
 		            }	            
 		            str += '</div></li>';
 				});
 				$("#fansNumUlhtml").append(str);
 				$("#fansNumPageIndex").val(pageIndex);
 				$("#fansNumPageTotal").val(res.pageTotal);
 				if(pageIndex == res.pageTotal){
 					$("#fansA").attr("style","display: none;");
 				}
 		     }
 		});		
 	}
 }