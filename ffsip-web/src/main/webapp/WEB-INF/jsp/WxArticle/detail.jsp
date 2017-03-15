<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<html>

<head>
	<%@include   file="../common/head.jsp"%>
	<title>${article.title}</title>
</head>

<body>
<script type="text/javascript">
 window.location.hash = 'list';

 setTimeout(function(){
 	window.location.hash = 'detail';
 }, 0);

 setTimeout(function(){
 	window.onhashchange = function(){

 		if (window.location.hash == '#list') {
 			window.location.href = '${BasePath}/';
 		}
 	};
 }, 0);
</script>
<%@include   file="../common/header.jsp"%>

	<div class="active-view">
		<div class="view-container" view-id="article">
			<div class="view module-art view-article" cacheview="article">
				<div class="art-section">
					<h2 class="art-title">${article.title }</h2>
				
					<div class="art-meta-list">
						<p>
							<span class="art-meta">
								<fmt:formatDate value="${article.createDate }" pattern="yyyy-MM-dd" /> 
							</span>
							<span class="art-meta link" onclick="location.href = '${BasePath}/WxArticle/list.do?memberCode=${member.code }'">
								<span>推荐者：</span>${company != null ? company.name : member.wxNickName}</span>
							<span class="art-meta" style="float:right">阅读：${article.readingNum*1}</span>
						</p>
					</div>
				
					<div class="art-content"><!--art-content 里面是新闻内容-->
						${article.content}						
					</div><!--art-content 里面是新闻内容-->
				
				</div>
			</div>
		</div>
	</div>
</body>

</html>