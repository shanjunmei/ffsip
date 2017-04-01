<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%@include file="common/head.jsp"%>
    <title>非凡之星</title>
</head>

<body>
<%@include   file="common/header.jsp"%>

<div class="active-view">

	<div view-id="home-focus" class="view-container">
		<div class="view view-home home-focus" cacheview="home-focus">
			<ul class="tabs i2">
		        <li tab=".art-tab" class="active" id="art-tabLI" onclick="showDiv('art-tab')">
		            <p class="name">文章</p>
		        </li>
		        <li tab=".user-tab" id="user-tabLI" onclick="showDiv('user-tab')">
		            <p class="name">用户</p>
		        </li>
		    </ul>
			<div class="tab-section">
				<!--文章列表-->
				<div class="art-tab active" id="art-tabDIV">
                    <div class="art-list">
						<ul id="ulhtml">
                           	<c:forEach items="${list}" var="item" varStatus="vs">
							<li class="article with-cover" onclick="location.href='${BasePath}/WxArticle/detail.do?articleCode=${item.wxArticle.code }'">
								<p class="title">${item.wxArticle.title }</p>
								<p class="meta-list">
									<span class="meta link">${item.company.name != null ? item.company.name : item.member.wxNickName}</span>
									<span class="meta"><fmt:formatDate value="${item.wxArticle.createDate }" pattern="yyyy-MM-dd" /></span>
								</p>
								<p class="st">
									<span>阅读${item.wxArticle.readingNum*1}</span>
									<span>转发${item.wxArticle.forwardingNum*1}</span>
									<span>评论${item.wxArticle.commentNum*1}</span>
								</p>
								<div class="cover" style="background-image: url('${item.wxArticle.coverImg}');"></div>
							</li>
							</c:forEach>

                       </ul>
                       <li class="more" id="articleA" ${pageTotal == 1 ? 'style="display: none;"':''}>
						<input type="hidden" id="pageIndex" value="1">
						<input type="hidden" id="pageTotal" value="${pageTotal}">
						<a href="javascript:loadMore()">更多内容</a>
                       </li>
					</div>
				</div>
				<!--用户列表-->
				<div class="user-tab" id="user-tabDIV">
		            <div class="user-list">			                
		                <ul id="subscribeNumUlhtml">
		                
		                </ul>
		                <li class="more" id="subscribeA"  ${pageTotal == 1 ? 'style="display: none;"':''}>
							<input type="hidden" id="subscribePageIndex" value="0">
							<input type="hidden" id="subscribePageTotal" value="1">
							<input type="hidden" id="memberCode" value="${memberCode}">
							<a href="javascript:subscribeNumLoad()">更多内容</a>
	                    </li>
		            </div>		            
		        </div>
			</div>
		</div>
	</div>    
</div>
</body>

<script type="text/javascript">
var userTabLI = 0;
function showDiv(divStr){			//切换主题卡
	$("#art-tabLI").attr('class',"");
	$("#user-tabLI").attr('class',"");
	$("#art-tabDIV").attr('class',"art-tab");
	$("#user-tabDIV").attr('class',"user-tab");

	if(divStr == 'art-tab'){
		$("#art-tabLI").attr('class',"active");
		$("#art-tabDIV").attr('class',"art-tab active");		
	}
	if(divStr == 'user-tab'){
		$("#user-tabLI").attr('class',"active");
		$("#user-tabDIV").attr('class',"user-tab active");
		if(userTabLI == 0){
			userTabLI = 1;
			subscribeNumLoad();
		}
	}	
}

function loadMore(){			//分页
	var pageIndex = $("#pageIndex").val()*1 + 1;
	var pageTotal = $("#pageTotal").val();
	if(pageIndex <= pageTotal){		
		$.ajax({url:"${BasePath}/WxArticle/homeFocusLoad.do",
			data:{'pageIndex':pageIndex},
			async:false,
			success: function(res){
				var str = '';			
				$.each(res.list,function(n,item) {
					var company = item.company;
					var member = item.member;
					var namestr = item.member.wxNickName;
					if(company != null){
						namestr = item.company.name;
					}
					str += '<li class="article with-cover" onclick="location.href=\'${BasePath}/WxArticle/detail.do?articleCode='+item.wxArticle.code+'\'">'
					+'<p class="title">'+item.wxArticle.title+'</p>'
					+'<p class="meta-list">'
					+'	<span class="meta link" onclick="location.href=\'${BasePath}/WxArticle/list.do?memberCode='+item.member.code+'\'">'
					+'		'+namestr+'</span>'
					+'	<span class="meta">'+formatDate(new Date(item.wxArticle.createDate))+'</span>'
					+'</p>'
					+'<p class="st">'
					+'	<span>阅读'+item.wxArticle.readingNum*1+'</span>'
					+'	<span>转发'+item.wxArticle.forwardingNum*1+'</span>'
					+'	<span>评论'+item.wxArticle.commentNum*1+'</span>'
					+'</p>'
					+'<div class="cover" style="background-image: url(\''+item.wxArticle.coverImg+'\');"></div>'
					+'</li>';
				});
				$("#ulhtml").append(str);
				$("#pageIndex").val(pageIndex);
				if(pageIndex == pageTotal){
					$("#articleA").attr("style","display: none;");
				}
		     }
		});		
	}
}
</script>
<script type="text/javascript" src="${BasePath}/js/common/fans.js"></script>
</html>
