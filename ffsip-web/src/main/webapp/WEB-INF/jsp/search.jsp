<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%@include   file="common/head.jsp"%>
    <title>非凡之星</title>
</head>

<body>
<%@include   file="common/header.jsp"%>
<div class="root-view">    
    <div class="popup"><!--以下full出现的三个div谁出现给谁 active-->
        <div class="search-pop full active">
            <div class="pop-head">
                <form class="search-group" onsubmit="javascript:return false;">
                    <input class="ipt-search" type="search" placeholder="搜索用户、文章" value="${valueK}">
                    <span class="link-text" onclick="javascript:history.back(-1);">取消</span>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="active-view">
	<div view-id="search" class="view-container">
		<div class="view view-search" cacheview="search">
			<!--这里是搜索结果--1-->
			<!--以搜索字段'青年孙志刚在收容过程中被殴打致死'为例,如果只搜索到了用户，那么下面的文章列表不出现，如果只出现了文章，那么用户列表不出现-->
			
			<c:if test="${not empty companyList}">
			<div class="section">
			    <div class="section-head">企业</div>
			    <div class="user-list">
			    	<ul>
			    		<c:forEach items="${companyList}" var="user">
			        	<li><div class="user-info">
				        	<div onclick="location.href = '${BasePath}/WxArticle/list.do?memberCode=${user.member.code }'">
				            <div class="avatar" style="background-image: url('${user.company.logoImg}');"></div>
				            <div class="info">
				                <p class="name">${user.company.name}</p>
				
				                <p class="about">${user.company.introduction}</p>
				
				                <p class="st">
				                    <span>关注 ${user.member.subscribeNum*1 }</span>
				                    <input type="hidden" id="fansNum" value="${user.member.fansNum*1 }">
				                    <span id="fansNumSpan">粉丝 ${user.member.fansNum*1 }</span>
				                    <span>文章 ${user.member.articleNum*1 }</span>
				                </p>
				            </div></div>
				            <c:if test="${user.isFans != 1}">
				            	<div class="btn fill-green" onclick="doFollow('${user.member.code}',this)"><span class="font-jia">+</span>关注</div>
				            </c:if>
				            <c:if test="${user.isFans == 1}">
				            	<div class="btn fill-blue" onclick="doUnFollow('${user.member.code}',this)">已关注</div>
				            </c:if>			            
				        </div></li>
			        	</c:forEach>
				        <li class="more">
				        	<a>更多内容</a>
				        </li>
			        </ul>
			    </div>
			</div>
			</c:if>
			
			<c:if test="${not empty memberList}">
			<div class="section">
			    <div class="section-head">用户</div>
			    <div class="user-list">
			    	<ul>
			    		<c:forEach items="${memberList}" var="user">
			        	<li><div class="user-info">
				        	<div onclick="location.href = '${BasePath}/WxArticle/list.do?memberCode=${user.member.code }'">
				            <div class="avatar" style="background-image: url('${user.member.wxHeadimgurl}');"></div>
				            <div class="info">
				                <p class="name">${user.member.wxNickName}</p>	
				                <p class="st">
				                    <span>关注 ${user.member.subscribeNum*1 }</span>
				                    <input type="hidden" id="fansNum" value="${user.member.fansNum*1 }">
				                    <span id="fansNumSpan">粉丝 ${user.member.fansNum*1 }</span>
				                    <span>文章 ${user.member.articleNum*1 }</span>
				                </p>
				            </div></div>
				            <c:if test="${user.isFans != 1}">
				            	<div class="btn fill-green" onclick="doFollow('${user.member.code}',this)"><span class="font-jia">+</span>关注</div>
				            </c:if>
				            <c:if test="${user.isFans == 1}">
				            	<div class="btn fill-blue" onclick="doUnFollow('${user.member.code}',this)">已关注</div>
				            </c:if>
				            
				        </div></li>
			        	</c:forEach>
				        <li class="more">
				        	<a>更多内容</a>
				        </li>
			        </ul>
			    </div>
			</div>
			</c:if>
			
			<!--文章列表-->
			<c:if test="${not empty list}">
			<div class="section">
				<div class="section-head">文章</div>
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
			</c:if>
				
			<c:if test="${empty userList && empty companyList && empty memberList}">
			<!--这里是搜索结果--2--这个没有结果的永远出现在其他搜索结果后面-->
			<div class="section" id="notSearchResult">
				<p class="more">无搜索结果</p>
			</div>	
			</c:if>
		</div>
	</div>
</div>
</body>
<input type="hidden" id="valueK" value="${valueK}">
<script type="text/javascript">

document.onkeydown = function(e){  
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
    	var search = $(".ipt-search").val();
    	location.href= encodeURI('${BasePath}/WxArticle/search.do?valueK='+search);
    }
  }

function loadMore(){			//分页
	var pageIndex = $("#pageIndex").val()*1 + 1;
	var pageTotal = $("#pageTotal").val();
	if(pageIndex <= pageTotal){		
		$.ajax({url:"${BasePath}/WxArticle/indexLoad.do",
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
					$("#subscribeA").attr("style","display: none;");
				}
		     }
		});		
	}
}
</script>
</html>
