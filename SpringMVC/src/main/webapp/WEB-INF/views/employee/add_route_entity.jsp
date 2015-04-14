

<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

            <div id="content" class="clearfix">
                <div id="page-content">

                    <form method="post" class="pure-form pure-form-aligned" action="addRouteEntity">
                        <fieldset>
                            <legend><spring:message code="employee.addRouteEntity" /></legend>
                            <div class="pure-control-group">
                                <label for="name"><spring:message code="trains.RouteName" /></label>
                                <select name="route" class="pure-input-1-2">
                                    <c:forEach items="${sessionScope.routeList}" var="route">
                                        <option value="${route.routeId}">${route.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="pure-control-group">
                                <label for="name"><spring:message code="trains.Station" /></label>
                                <select name="station" class="pure-input-1-2">
                                    <c:forEach items="${sessionScope.stationList}" var="station">
                                        <option value="${station.stationId}">${station.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="pure-control-group">
                                <label for="name"><spring:message code="trains.ArrivalTime" /></label>
                                <input id="arrivalDate" type="datetime-local" placeholder="" name="arrivalDate" required>
                            </div>
                            <div class="pure-control-group">
                                <label for="name"><spring:message code="trains.DepartureTime" /></label>
                                <input id="depatereDate" type="datetime-local" placeholder="" name="depatureDate" required>
                            </div>
                            
                            <div class="pure-control-group">
                                <label for="name"><spring:message code="routeEntity.Sequence" /></label>
                                <input id="sequence" type="text" placeholder="" name="sequence" required>
                            </div>
                            <button type="submit" style="margin-left: 180px;" 
                                    class="pure-button pure-button-active"><spring:message code="employee.addRouteEntity" /></button>
                        </fieldset>

                        
                    </form>

                </div><!--body end-->
                <div id="aside">
                </div>    
            </div>

