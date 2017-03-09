<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<html>

<head>
	<%@include   file="../common/head.jsp"%>
	<title>${company == '' ? company.name : member.wxNickName}的主页</title>
</head>

<body>

<%@include   file="../common/header.jsp"%>

	<div class="active-view">
		<div view-id="user" class="view-container">
			<div class="view module-user view-user" cacheview="user">
				<div class="head-section">
					<div class="avatar" style="background-image: url(${company == '' ? company.logoImg : member.wxHeadimgurl});"></div>
					<p><span class="name">${company == '' ? company.name : member.wxNickName}</span></p>
					<div class="cover"></div>
				</div>

				<div class="tab-section" move-mode="">
					<div class="art-tab active">
						<div class="art-list">

							<ul>
								<c:forEach items="${list}" var="article" varStatus="vs">
								<li class="article with-cover" onclick="location.href='/ffsip-web/WxArticle/detail.do?articleCode=${article.code }'">
									<p class="title">${article.title }</p>
									<p class="meta-list">
										<span class="meta link">${company == '' ? company.name : member.wxNickName}</span>
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

								<li class="more">
									<a>更多内容</a>
								</li>
							</ul>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>
</body>
</html>