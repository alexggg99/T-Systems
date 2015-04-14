<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div id="nav">
    <div class="menu">
        <li><a href=" <c:url value="."/> "><spring:message code="home.home" /></a></li>
            <sec:authorize access="!hasRole('manager')">
            <li><a href=" <c:url value="/login"/> "><spring:message code="home.login" /></a></li>
            </sec:authorize>
            <sec:authorize access="hasRole('manager')">
            <li><a href=" <c:url value="/logout"/> ">Logout</a></li>
            </sec:authorize>
            <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/'}">
                <li id="en"><a href="?locale=en" id="enFlag" class="selected"><img src="<c:url value="/resources/images/gb.png"/>" alt="" title="" border="0" /></a></li>
                <li id="de"><a href="?locale=de" id="deFlag"><img src="<c:url value="/resources/images/de.png"/>" alt="" title="" border="0" /></a></li>
            </c:if>    
    </div>
</div>