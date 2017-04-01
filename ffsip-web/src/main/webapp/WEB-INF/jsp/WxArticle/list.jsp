<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<html>
<head>
	<%@include   file="../common/head.jsp"%>
	<title>${company != null ? company.name : member.wxNickName}的主页</title>
</head>
<body>
<%@include   file="../common/header.jsp"%>
	<div class="active-view">
		<div view-id="user" class="view-container">
			<div class="view module-user view-user" cacheview="user">
				<c:if test="${company == null}">
				<div class="head-section">
					<div class="avatar" style="background-image: url(${member.wxHeadimgurl});"></div>
					<p><span class="name">${member.wxNickName}</span></p>
					<div class="cover"></div>
					<center>					
					<c:if test="${fans != 1}">
			            	<div class="btn fill-green" onclick="doFollow('${member.code}',this)">关注动态</div>
			            </c:if>
			            <c:if test="${fans == 1}">
			            	<div class="btn fill-blue" onclick="doUnFollow('${member.code}',this)">已关注</div>
			            </c:if></center>
			            
				</div>
				</c:if>
				
				<c:if test="${company != null}">
				<div class="view-shared" cacheview="shared">
					<!--s-->
					<div class="head-section bg-empty logo-hide name-hide" style="background: none;">
				    	<p class="name">${company.name}</p>
					    <p class="about">${company.introduction}</p>
					    <div class="btn-group">
					        <c:if test="${company.wxImg != null}"><div class="btn fill-green" id="attention">关注公众号</div></c:if>
					        					        
			            	<c:if test="${fans != 1}">
			            	<div class="btn fill-green" onclick="doFollow('${member.code}',this)">关注动态</div>
			           		</c:if>
			            	<c:if test="${fans == 1}">
			            	<div class="btn fill-blue" onclick="doUnFollow('${member.code}',this)">已关注</div>
			            	</c:if>
					        <!--
					        	点关注时类样式变为fill-blue，并且文字变为已关注
					        	取消关注时，样式为fill-green，并且文字为关注动态
					        -->
					    </div>
					    <div class="cover"></div>
				    </div>
					
					<div class="qr-code hide"><!--关注公众号时display: block;取消时为display: none;-->
					    <p>${company.name}</p>
					    <p><img src="${company.wxImg}"></p>
					    <p>手机端请长按图中二维码，关注公众号</p>
					</div>
					<!--e-->
				</div>
				</c:if>
					
				<ul class="tabs i3" data-on-tab="moreData('',1)"><!--标签几个就加样式i几，比如说当前是三个标签，所以加样式i3-->
				    <li tab=".art-0-last-10-tab" class="active" id="articleNumLi" onclick="showDiv('articleNum')"><!--当前，加样式active-->
					    <p class="num">${member.articleNum*1}</p>
					    <p class="name">文章</p>
				    </li>
				    <li tab=".followed--last-10-tab" id="subscribeNumLi" onclick="showDiv('subscribeNum')">
					    <p class="num">${member.subscribeNum*1}</p>
					    <p class="name">关注</p>
				    </li>
				    <li tab=".followed--last-10-tab" id="fansNumLi" onclick="showDiv('fansNum')">
				    	<p class="num">${member.fansNum*1}</p>
				    	<p class="name">粉丝</p>
				    </li>
				</ul>
			        
				<div class="tab-section" move-mode="">				
					<!--关注用户列表-->
					<div class="user-tab active" id="subscribeNumDIV" style="display: none;">
			            <div class="user-list">			                
			                <ul id="subscribeNumUlhtml">
			                
			                </ul>
			                <li class="more" id="subscribeA">
								<input type="hidden" id="subscribePageIndex" value="0">
								<input type="hidden" id="subscribePageTotal" value="1">
								<a href="javascript:subscribeNumLoad()">更多内容</a>
		                    </li>
			            </div>
			        </div>	  		
					<!--粉丝用户列表-->
					<div class="user-tab active" id="fansNumDIV" style="display: none;">
			            <div class="user-list">			                
			                <ul id="fansNumUlhtml">
			                
			                </ul>
		                    <li class="more" id="fansA">
								<input type="hidden" id="fansNumPageIndex" value="0">
								<input type="hidden" id="fansNumPageTotal" value="1">
								<a href="javascript:fansNumLoad()">更多内容</a>
		                    </li>
			            </div>
			        </div>	 
				
					<div class="art-tab active" id="articleNumDIV" style="display: block;">
						<div class="art-list">
							<ul id="ulhtml">
								<c:forEach items="${list}" var="article" varStatus="vs">
								<li class="article with-cover" onclick="location.href='${BasePath}/WxArticle/detail.do?articleCode=${article.code }'">
									<p class="title">${article.title }</p>
									<p class="meta-list">
										<span class="meta link" onclick="location.href='${BasePath}/WxArticle/list.do?memberCode=${member.code}'">
											${company != null ? company.name : member.wxNickName}</span>
										<span class="meta"><fmt:formatDate value="${article.createDate }" pattern="yyyy-MM-dd" /></span>
									</p>
									<p class="st">
										<span>阅读${article.readingNum*1}</span>
										<span>转发${article.forwardingNum*1}</span>
										<span>评论${article.commentNum*1}</span>
									</p>
									<div class="cover" style="background-image: url('${article.coverImg}');"></div>
								</li>
								</c:forEach>								
							</ul>
							<li class="more" id="articleA" ${pageTotal == 1 ? 'style="display: none;"':''}>
								<input type="hidden" id="articlePageIndex" value="1">
								<input type="hidden" id="articlePageTotal" value="${pageTotal}">
								<a href="javascript:loadMore()">更多内容</a>
							</li>
						</div>
					</div>
				</div>
				
				
			</div>
		</div>
	</div>
</body>
<input type="hidden" id="memberCode" value="${memberCode}">
<script type="text/javascript">
var subscribeNum = 0;
var fansNum = 0;
function showDiv(divStr){			//切换主题卡
	$("#articleNumLi").attr('tab',".followed--last-10-tab");
	$("#subscribeNumLi").attr('tab',".followed--last-10-tab");
	$("#fansNumLi").attr('tab',".followed--last-10-tab");
	$("#articleNumLi").attr('class',"");
	$("#subscribeNumLi").attr('class',"");
	$("#fansNumLi").attr('class',"");
	$("#articleNumDIV").attr('style',"display: none;");
	$("#subscribeNumDIV").attr('style',"display: none;");
	$("#fansNumDIV").attr('style',"display: none;");
	
	$("#"+divStr+"Li").attr('tab',".art-0-last-10-tab");
	$("#"+divStr+"Li").attr('class',"active");
	$("#"+divStr+"DIV").attr('style',"display: block;");
	
	if(divStr == "subscribeNum" && subscribeNum == 0){
		subscribeNum = 1;
		subscribeNumLoad();
	}
	if(divStr == "fansNum" && fansNum == 0){
		fansNum = 1;
		fansNumLoad();
	}
}
function loadMore(){			//分页
	var pageIndex = $("#articlePageIndex").val()*1 + 1;
	var pageTotal = $("#articlePageTotal").val();
	var memberCode = $("#memberCode").val();
	if(pageIndex <= pageTotal){		
		$.ajax({url:"${BasePath}/WxArticle/listLoad.do",
			data:{'pageIndex':pageIndex,'memberCode':memberCode},
			async:false,
			success: function(res){
				var str = '';
				var company = res.company;
				var member = res.member;
				var namestr = member.wxNickName;
				if(company != null){
					namestr = company.name;
				}
			
				$.each(res.list,function(n,article) {
					
					str += '<li class="article with-cover" onclick="location.href=\'${BasePath}/WxArticle/detail.do?articleCode='+article.code+'\'">'
					+'<p class="title">'+article.title+'</p>'
					+'<p class="meta-list">'
					+'	<span class="meta link" onclick="location.href=\'${BasePath}/WxArticle/list.do?memberCode='+member.code+'\'">'
					+'		'+namestr+'</span>'
					+'	<span class="meta">'+formatDate(new Date(article.createDate))+'</span>'
					+'</p>'
					+'<p class="st">'
					+'	<span>阅读'+article.readingNum*1+'</span>'
					+'	<span>转发'+article.forwardingNum*1+'</span>'
					+'	<span>评论'+article.commentNum*1+'</span>'
					+'</p>'
					+'<div class="cover" style="background-image: url(\''+article.coverImg+'\');"></div>'
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
$("#attention").click(function(){
	$(".qr-code").toggle();
});
</script>
<script type="text/javascript" src="${BasePath}/js/common/fans.js"></script>
<style type="text/css">
	.view-shared .head-section p{max-width: 100%; text-align: left; margin: 0;}
	.view-shared .btn-group{text-align: left;}
</style>
</html>