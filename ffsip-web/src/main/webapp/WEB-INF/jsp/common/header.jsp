<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.ffzx.ffsip.util.JsonConverter"%>
<%@ page import="com.ffzx.ffsip.model.Member"%>

<div class="root-view">
    <header>
        <div class="logo-btn">
	        <c:if test="${empty company}">
        		<img src="${BasePath}/images/50.png" style="height:30px !important; margin-top:7px;" onclick="location.href='${BasePath}'">
        	</c:if>									
			<c:if test="${not empty company}">
				<a href="${BasePath}/WxArticle/list.do?memberCode=${member.code}" style="color:red ; margin-top:7px; line-height: 40px;">
				${company.name}</a>
			</c:if>
        </div>	
        <c:if test="${not empty homeFocus}">
        <div class="nav">
            <ul class="opacity-transition">
                <li tab="find" ${homeFocus*1 == 1 ? 'class="active"':''} onclick="location.href='${BasePath}/WxArticle/index.do'">发现</li>
                <li tab="focus" ${homeFocus*1 == 2 ? 'class="active"':''} onclick="location.href='${BasePath}/WxArticle/homeFocus.do'">关注</li>
            </ul>
        </div>
        </c:if>
        <div class="right" >
        <span class="link-search" onclick="location.href='${BasePath}/Search/toSearch.do'">
        	<img src="${BasePath}/images/icon-search.png" height="15">
        </span>
            <c:if test="${not empty loginMember}">
            <%--    <c:if test="${not empty company}">

                </c:if>--%>
                <c:choose>
                    <c:when test="${not empty loginCompany}">
                        <img src="${loginCompany.logoImg }" class="tx" onclick="location.href = '${BasePath}/WxArticle/list.do?memberCode=${loginMember.code }'">
                    </c:when>
                    <c:otherwise>
                        <img src="${loginMember.wxHeadimgurl}" class="tx" onclick="location.href = '${BasePath}/WxArticle/list.do?memberCode=${loginMember.code }'">
                    </c:otherwise>

                </c:choose>
            </c:if>            
            <c:if test="${empty loginMember}">
            	<%--<a href="#" style="color:#2f8efe ; font-weight: 300;">登录</a>--%>
                <%--<img src="${BasePath}/images/img1.jpg" class="tx">--%>
            </c:if>
        </div>
    </header>
    <div class="pad show"></div>
</div>

<div class="noti-top top-transition" style="display: none;">取关成功！</div>
<script type="text/javascript">
var user = JSON.parse('<%=JsonConverter.toJson(session.getAttribute("loginMember"))%>');

var BasePath = '/ffsip-web';
function notify(str){
	  $(".noti-top").html(str);
	  $(".noti-top").attr('style',"display: block;");
	  setTimeout(function () { 
  	  $(".noti-top").attr('style',"display: none;");
	    }, 2000);
}
</script>