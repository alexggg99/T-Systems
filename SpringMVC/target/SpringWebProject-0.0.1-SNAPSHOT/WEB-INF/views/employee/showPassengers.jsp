<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
        <link type="text/css" rel="stylesheet"
              href=" <c:url value="/resources/css/findTickets.css" />"/>

            <div id="content" class="clearfix">
                <div id="page-content">

                    <table id="customers">
                        <tr>
                            <th><spring:message code="passenger.passengerId" /></th>
                            <th><spring:message code="passenger.firstName" /></th>
                            <th><spring:message code="passenger.lastName" /></th>
                        </tr>
                        <c:forEach items="${requestScope['passengersOnTrain']}" var="list">
                            <tr> 
                                <td>${list.getPassengerId()}</td>
                                <td>${list.getFirstName()}</td>
                                <td>${list.getLastName()}</td>
                            </tr>     
                        </c:forEach>
                    </table>

                </div><!--body end-->  
            </div>