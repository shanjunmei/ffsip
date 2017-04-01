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
					
						<!--版权说明--s-->
					<div class="copyright-fold">
			            <h3 id="copyright">版权声明</h3>
			            <div class="copyright-con">
			                	凡本网注明“来源：${member.name == null ? '非凡之星' : member.name}”的所有作品，
			                	均为深圳市非凡之星网络科技有限公司合法拥有版权或有权使用的作品，欢迎转载，但在转载前须注明
			                	“来源：${member.name == null ? '非凡之星' : member.name}”。
			                	未经本网授权不得利用除转载外的其它方式使用上述作品，违反本声明者，本网将追究其相关法律责任。
			                <br>
			                	凡本网注明“来源：XXX（非${member.name == null ? '非凡之星' : member.name}）”的作品，
			                	均转载自其它媒体，转载目的在于传递更多信息，并不代表本网赞同其观点和对其真实性负责。
			                <br>
			                	本网充分尊重原作者的合法权益及相关利益，如所发布文章的原作者对其文章内容的使用或转载存在争议，
			                	请直接联系：151-1234-8325，我们将及时按照作者意愿予以更正或删除。
			            </div>
			        </div>
				</div>
								
				<c:if test="${company != null}">
				<div class="section">
			        <div class="section-head">
			            <p class="left">发布者</p>
			        </div>
			        <div class="user-info">
			        	<div onclick="location.href = '${BasePath}/WxArticle/list.do?memberCode=${member.code }'">
			            <div class="avatar" style="background-image: url('${company.logoImg}');"></div>
			            <div class="info">
			                <p class="name">${company.name}</p>
			
			                <p class="about">${company.introduction}</p>
			
			                <p class="st">
			                    <span>关注 ${member.subscribeNum*1 }</span>
			                    <input type="hidden" id="fansNum" value="${member.fansNum*1 }">
			                    <span id="fansNumSpan">粉丝 ${member.fansNum*1 }</span>
			                    <span>文章 ${member.articleNum*1 }</span>
			                </p>
			            </div></div>
			            <c:if test="${fans != 1}">
			            	<div class="btn fill-green" onclick="doFollow('${member.code}',this)"><span class="font-jia">+</span>关注</div>
			            </c:if>
			            <c:if test="${fans == 1}">
			            	<div class="btn fill-blue" onclick="doUnFollow('${member.code}',this)">已关注</div>
			            </c:if>
			            
			        </div>
			    </div>
			    </c:if>
			    
				<c:if test="${company == null}">
			    <div class="section">
			        <div class="section-head">
			            <p class="left">发布者</p>
			        </div>
			        <div class="user-info">
			        	<div onclick="location.href = '${BasePath}/WxArticle/list.do?memberCode=${member.code }'">
			            <div class="avatar" style="background-image: url('${member.wxHeadimgurl}');"></div>
			            <div class="info">
			                <p class="name">${member.wxNickName}</p>	
			                <p class="st">
			                    <span>关注 ${member.subscribeNum*1 }</span>
			                    <input type="hidden" id="fansNum" value="${member.fansNum*1 }">
			                    <span id="fansNumSpan">粉丝 ${member.fansNum*1 }</span>
			                    <span>文章 ${member.articleNum*1 }</span>
			                </p>
			            </div></div>
			            <c:if test="${fans != 1}">
			            	<div class="btn fill-green" onclick="doFollow('${member.code}',this)"><span class="font-jia">+</span>关注</div>
			            </c:if>
			            <c:if test="${fans == 1}">
			            	<div class="btn fill-blue" onclick="doUnFollow('${member.code}',this)">已关注</div>
			            </c:if>
			            
			        </div>
			    </div>
			    </c:if>
			    
			    <div class="section">
			        <div class="section-head">
			            <p class="left">评论</p>
			            <p class="right">${article.commentNum*1 }</p>
			        </div>
			        <div class="do-comment">
			            <div class="edit-area" contenteditable="true" placeholder="写评论"></div>
			            <div class="btn fill-green" onclick="doComment('${article.code }')">提交
			            </div>
			        </div>
			        
			        <div id="commentStr">
			        <c:forEach items="${commentList }" var="comment">
			        <div class="comment-list">
			            <div class="comment">
			                <div class="row">
			                    <p class="user">${comment.wxNickName}</p>
			                    <div class="praise-count praised"><span></span>${comment.likeNum*1}</div>
			                    <!--span是心形图标，点击后在父div上加样式praised，没有加，就没有这个样式-->
			                </div>
			                <p class="content">${comment.commentContent}</p>
			            </div>			           
			        </div>
			        </c:forEach>
			        </div>
			        
			        <div class="row" style="display: none;">
			            <div class="big-btn fill-blue">更多内容</div>
			        </div>
			    </div>
			    <!-- 
			    <nav class="toolbar">
			        <ul>
			            <li class="input-bar">
			                <input value="发布新内容" disabled="">
			            </li>
			            <li class="cell">
			                <span class="icon-menu"></span>
			            </li>
			            <li class="cell">
			                <span class="icon-comment"></span>
			                <p>0</p>
			            </li>
			            <li class="cell">
			                <span class="icon-star active"></span>
			                <p>1</p>
			            </li>
			        </ul>
			    </nav> -->
			    
			    
			</div>
		</div>
	</div>
</body>
	<script type="text/javascript" src="${BasePath}/js/common/fans.js"></script>
<script type="text/javascript">
	$("#copyright").click(function(){
		$(this).parent("div.copyright-fold").toggleClass("unfold");
	});
	
    
    var doCommentFlag = true;
	
     function doComment(articleCode) {
         if (!doCommentFlag) return;
         commentFlag = false;
         content = $.trim($('.edit-area').html());
         if (!content) {
        	 notify("评论内容不能为空");
        	 return;}
         if (user == null || user == '') {
             return;
         }
         if (content.length > 140) {
        	 notify("评论内容要不超过140个字符");
             return;
         } else {
             commentFlag = true;
             $.post("${BasePath}/comment/addComment.do", {
            	 articleCode: articleCode,
            	 commentContent: content
             }, function (data) {
                 commentFlag = false;
                 if (data.type == "fail") {
                	 notify("发表评论失败");
                 } else {
//                      attach({
//                          '+commentList': data.commentList
//                      });
                     $(".edit-area").text("");
                     notify("发表评论成功");
                     doCommentFlag = true;
                 }
             });
         }
     }
	
</script>
<style type="text/css">
	.copyright-con{font-size: 14px; color: #888;}
</style>
</html>