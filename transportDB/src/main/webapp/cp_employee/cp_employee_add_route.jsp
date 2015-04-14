<%-- 
    Document   : error_page
    Created on : Mar 1, 2015, 10:26:38 PM
    Author     : alexggg99
--%>

<%@page import="ru.tsystems.project.domain.entities.Station"%>
<%@page import="ru.tsystems.project.services.implementations.StationServiceImplementation"%>
<%@page import="ru.tsystems.project.services.API.StationService"%>
<%@page import="java.util.List"%>

<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Transport Company</title>
        <meta name="keywords" content="Transport Company"/>
        <meta name="description" content="Transport Company"/>
        <link type="text/css" rel="stylesheet" href="../css/common.css"/>
        <link type="text/css" rel="stylesheet" href="../css/menu.css"/>
        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
        <script type="text/javascript" src="master-script.js"></script>
    </head>
    <body>
        <div id="wrapper">
            <div id="nav">
                <div class="menu">
                    <li><a href="../index.html">Home</a></li>
                    <li><a href="/controllers/LogouServlet">Logout</a></li>
                </div>
            </div><!--nav end-->
            <div id="content" class="clearfix">
                <div id="page-content">

                    <form method="post" class="pure-form pure-form-aligned" action="/controllers/AddRouteEntityServlet">
                        <fieldset>
                            <legend>Add route</legend>
                            <div class="pure-control-group">
                                <label for="name">Route</label>
                                <select name="route" class="pure-input-1-2">
                                    <c:forEach items="${sessionScope.routeList}" var="route">
                                        <option value="${route.routeId}">${route.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="pure-control-group">
                                <label for="name">Station</label>
                                <select name="station" class="pure-input-1-2">
                                    <c:forEach items="${sessionScope.stationList}" var="station">
                                        <option value="${station.stationId}">${station.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="pure-control-group">
                                <label for="name">Arrival date</label>
                                <input id="arrivalDate" type="datetime-local" placeholder="" name="arrivalDate" required>
                            </div>
                            <div class="pure-control-group">
                                <label for="name">Departure date</label>
                                <input id="depatereDate" type="datetime-local" placeholder="" name="depatureDate" required>
                            </div>
                            
                            <div class="pure-control-group">
                                <label for="name">Sequence</label>
                                <input id="sequence" type="text" placeholder="" name="sequence" required>
                            </div>
                        </fieldset>

                        <button type="submit" class="pure-button pure-button-active">Add route</button>
                    </form>

                </div><!--body end-->
                <div id="aside">
                </div>    
            </div><!--content end-->
            <div id="empty-div"></div>
        </div>	
        <div id="footer">
            <span>2015. Transport company. All rights reserved.</span>
        </div><!--footer end-->

    </body>
</html>

