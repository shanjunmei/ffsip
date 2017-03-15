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
				<div class="head-section">
					<div class="avatar" style="background-image: url(${company != null ? company.logoImg : member.wxHeadimgurl});"></div>
					<p><span class="name">${company != null ? company.name : member.wxNickName}</span></p>
					<div class="cover"></div>
				</div>

				<div class="tab-section" move-mode="">
					<div class="art-tab active">
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
							<li class="more">
								<input type="hidden" id="pageIndex" value="1">
								<input type="hidden" id="pageTotal" value="${pageTotal}">
								<input type="hidden" id="memberCode" value="${memberCode}">
								<a href="javascript:loadMore()">更多内容</a>
							</li>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function loadMore(){			//分页
	var pageIndex = $("#pageIndex").val()*1 + 1;
	var pageTotal = $("#pageTotal").val();
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
		     }
		});		
	}
}
</script>
</html>