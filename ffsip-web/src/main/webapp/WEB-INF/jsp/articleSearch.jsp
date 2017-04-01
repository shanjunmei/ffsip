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
                <form class="search-group" action="${BasePath}/Search/toSearch.do" method="post">
                    <input class="ipt-search" type="search" name="keyWords" placeholder="搜索文章" value="${keyWords}">
                    <span class="link-text" onclick="javascript:history.back(-1);">取消</span>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="active-view">
	<div view-id="search" class="view-container">
		<div class="view view-search" cacheview="search">

			<!--文章列表-->
			<c:if test="${not empty list}">
			<div class="section">
				<div class="section-head">文章</div>
				<div class="art-list">
					<ul id="ulhtml">
						<c:forEach items="${list}" var="item" varStatus="vs">
						<li class="article with-cover" onclick="location.href='${BasePath}/WxArticle/detail.do?articleCode=${item.code }'">
							<p class="title">${item.title }</p>
							<p class="content">${item.content }</p>
							<p class="meta-list">
								<span class="meta link">${item.publisher}</span>
								<span class="meta"><fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd" /></span>
							</p>
						<%--	<p class="st">
								<span>阅读${item.wxArticle.readingNum*1}</span>
								<span>转发${item.wxArticle.forwardingNum*1}</span>
								<span>评论${item.wxArticle.commentNum*1}</span>
							</p>--%>
							<div class="cover" style="background-image: url('${item.coverImg}');"></div>
						</li>
						</c:forEach>

                       </ul>
                       <li class="more" id="articleA" ${pageTotal == 1 ? 'style="display: none;"':''}>
						<input type="hidden" id="pageIndex" value="1">
						<input type="hidden" id="pageTotal" value="${pageTotal}">
					<%--	<a href="javascript:loadMore()">更多内容</a>--%>
                       </li>
				</div>
			</div>
			</c:if>
				
			<c:if test="${empty list}">
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
    	/*var search = $(".ipt-search").val();
    	location.href= encodeURI('/Search/toSearch.do?keyWords='+search);*/
    	$('form')[0].submit();
    }
  }

</script>
</html>
