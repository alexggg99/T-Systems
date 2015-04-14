<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link type="text/css" rel="stylesheet" href=" <c:url value="/resources/css/findTickets.css" /> "/>

<div id="content" class="clearfix">
    <div id="page-content">

        <table id="customers">
            <tr>
                <th><spring:message code="trains.Station" /></th>
                <th><spring:message code="trains.ArrivalTime" /></th>
                <th><spring:message code="trains.DepartureTime" /></th>
                <th><spring:message code="trains.TrainName" /></th>
                <th><spring:message code="trains.RouteName" /></th>
            </tr>
            <c:forEach items="${requestScope['trains']}" var="list" varStatus="loop">
                <tr> 
                    <td>${list.getStation()}</td>
                    <td>${list.getArrivalTime()}</td>
                    <td>${list.getDepatureTime()}</td>
                    <td>${list.getRoute().getTrain().getName()}</td>
                    <td>${list.getRoute().getName()}</td>
                </tr>     
            </c:forEach>
        </table>

    </div><!--body end-->  
</div><!--content end-->
