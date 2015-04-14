

<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
        <link type="text/css" rel="stylesheet"
              href=" <c:url value="/resources/css/findTickets.css" />"/>
        <script src=" <c:url value="/resources/scripts/jquery.js" /> "></script>

            <div id="content" class="clearfix">
                <div id="page-content">

                    <table id="customers">
                        <tr>
                            <th><spring:message code="train.trainId" /></th>
                            <th><spring:message code="train.trainName" /></th>
                            <th><spring:message code="train.seats" /></th>
                        </tr>
                        <c:forEach items="${requestScope['allTrains']}" var="list">
                            <tr> 
                                <td>${list.getTrainId()}</td>
                                <td>${list.getName()}</td>
                                <td>${list.getSeats()}</td>
                            </tr>     
                        </c:forEach>
                    </table>

                </div><!--body end-->  
            </div>