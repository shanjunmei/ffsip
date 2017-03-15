<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="root-view">
    <header>
        <div class="logo-btn">
	        <c:if test="${empty company}">
        		<img src="${BasePath}/images/50.png" style="height:30px !important; margin-top:7px;" onclick="location.href='${BasePath}'">
        	</c:if>									
			<c:if test="${not empty company}">
				<a href="${BasePath}/WxArticle/list.do?memberCode=${member.code}" style="color:red ; margin-top:7px; line-height: 30px;">
				${company.name}</a>
			</c:if>
        </div>
        <div class="right" style="float: right;margin: 7px 10px 0 0; line-height:37px;">
            <c:if test="${not empty loginMember}">
            <%--    <c:if test="${not empty company}">

                </c:if>--%>
                <c:choose>
                    <c:when test="${not empty company}">
                        <img src="${ company.logoImg }" class="tx" onclick="location.href = '${BasePath}/WxArticle/list.do?memberCode=${loginMember.code }'">
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
</div>