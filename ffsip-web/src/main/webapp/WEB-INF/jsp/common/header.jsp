<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="root-view">
    <header>
        <div class="logo-btn">
	        <c:if test="${empty loginCompany}">
        		<img src="/ffsip-web/images/50.png" style="height:40px !important;" onclick="location.href='/ffsip-web'">
        	</c:if>									
			<c:if test="${not empty loginCompany}">
				<a href="/ffsip-web/WxArticle/list.do?memberCode=${loginMember.code}" style="color:red ;">
				${loginCompany.name}</a>
			</c:if>
        </div>
        <div class="right" style="float: right;margin: 7px 10px 0 0; line-height:37px;">
            <c:if test="${not empty loginMember}">
            	<img src="${loginMember == '' ? loginMember.logoImg : loginMember.wxHeadimgurl}" height="30">
            </c:if>            
            <c:if test="${empty loginMember}">
            	<a href="#" style="color:#2f8efe ; font-weight: 300;">登录</a>
            </c:if>
        </div>
    </header>
</div>
