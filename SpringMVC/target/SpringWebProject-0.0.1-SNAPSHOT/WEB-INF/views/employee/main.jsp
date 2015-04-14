<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
        <script src=" <c:url value="/resources/scripts/jquery.js" /> "></script>
        <script>
            $(document).ready(function ()
            {
                if (document.getElementById('isTrainInputSucceed').value == "true") {
                    var o = document.getElementById('alert-train');
                    o.style.display = 'block';
                }
                if (document.getElementById('isStationInputSucceed').value == "true") {
                    var o = document.getElementById('alert-station');
                    o.style.display = 'block';
                }
                if (document.getElementById('isRouteEntityInputSucceed').value == "true") {
                    var o = document.getElementById('alert-routeEntity');
                    o.style.display = 'block';
                }
            });
        </script>

            <div id="content" class="clearfix">
                <div id="page-content">
                
                    <form method="POST" class="pure-form" action="addstation">
                        <fieldset class="pure-group">
                            <legend><spring:message code="employee.addStation" /></legend>
                            <div id="isValid" style="display:none">
                                <input id="isStationInputSucceed" value=${requestScope.isStationInputSucceed}>

                            </div>
                            <div class="input-alert" id="alert-station" style="display:none">
                                <div><span class="error-alert-message"><spring:message code="employee.stationAdded" /></span>
                                </div>
                            </div> 
                            <input class="pure-input-1-2" placeholder="<spring:message code="employee.stationName" />" 
                                   value="" name="stationName" required/>
                        </fieldset>

                        <button type="submit" class="pure-button pure-button-active"><spring:message code="employee.addStation" /></button>
                    </form>

                    <form method="post" class="pure-form" action="addTrain">
                        <fieldset class="pure-group">
                            <legend><spring:message code="employee.addTrain" /></legend>
                            <div id="isValid" style="display:none">
                                <input id="isTrainInputSucceed" value=${requestScope.isTrainInputSucceed}>

                            </div>
                            <div class="input-alert" id="alert-train" style="display:none">
                                <div><span class="error-alert-message"><spring:message code="employee.trainAdded" /></span>
                                </div>
                            </div> 
                            <input type="text" class="pure-input-1-2" placeholder="<spring:message code="employee.trainName" />"
                                   value="" name="trainName" required>
                            <input type="text" class="pure-input-1-2" placeholder="<spring:message code="employee.trainSeats" />"
                                   value="" name="trainSeats" required>
                        </fieldset>

                        <button type="submit" class="pure-button pure-button-active"><spring:message code="employee.addTrain" /></button>
                    </form>

                    <form method="post" class="pure-form" action="showPassengers">
                        <fieldset class="pure-group">
                            <legend><spring:message code="employee.showPassengers" /></legend>
                            <input type="text" class="pure-input-1-2" placeholder="<spring:message code="employee.routeId" />"
                                   name="RouteId" required/>
                        </fieldset>

                        <button type="submit" class="pure-button pure-button-active"><spring:message code="employee.showPassengers" /></button>
                    </form>
                    
                    <form method="post" class="pure-form" action="showAllTrains">
                        <fieldset class="pure-group">
                            <legend><spring:message code="employee.showAllTrains" /></legend>
                        </fieldset>

                        <button type="submit" class="pure-button pure-button-active"><spring:message code="employee.showAllTrains" /></button>
                    </form>

                    <form method="post" class="pure-form">
                        <fieldset class="pure-group">
                            <legend><spring:message code="employee.addRouteEntity" /></legend>
                        </fieldset>
                        <div id="isValid" style="display:none">
                                <input id="isRouteEntityInputSucceed" value=${requestScope.isRouteEntityInputSucceed}>

                            </div>
                            <div class="input-alert" id="alert-routeEntity" style="display:none">
                                <div><span class="error-alert-message"><spring:message code="employee.routeEntityAdded" /></span>
                                </div>
                            </div>
                        <a class="pure-button pure-button-active" href="<c:url value="setRouteEntity"/>"><spring:message code="employee.addRouteEntity" /></a>
                       
                    </form>

                </div><!--body end-->
                <div id="aside">
                </div>    
            </div><!--content end-->


