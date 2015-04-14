<%-- 
    Document   : cp_employee_main
    Created on : Mar 1, 2015, 7:45:12 PM
    Author     : alexggg99
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Transport Company</title>
        <meta name="keywords" content="Transport Company"/>
        <meta name="description" content="Transport Company"/>
        <link type="text/css" rel="stylesheet" href="/css/common.css"/>
        <link type="text/css" rel="stylesheet" href="/css/menu.css"/>
        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
        <script type="text/javascript" src="/scripts/jquery.js"></script>
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

                    <form method="post" class="pure-form" action="/controllers/AddStationServlet">
                        <fieldset class="pure-group">
                            <legend>Add station</legend>
                            <div id="isValid" style="display:none">
                                <input id="isStationInputSucceed" value=${requestScope.isStationInputSucceed}>

                            </div>
                            <div class="input-alert" id="alert-station" style="display:none">
                                <div><span class="error-alert-message">Station added !</span>
                                </div>
                            </div> 
                            <input type="text" class="pure-input-1-2" placeholder="StationName" 
                                   value="" name="stationName"required >
                        </fieldset>

                        <button type="submit" class="pure-button pure-button-active">Add station</button>
                    </form>

                    <form method="post" class="pure-form" action="/controllers/AddTrainServlet">
                        <fieldset class="pure-group">
                            <legend>Add train</legend>
                            <div id="isValid" style="display:none">
                                <input id="isTrainInputSucceed" value=${requestScope.isTrainInputSucceed}>

                            </div>
                            <div class="input-alert" id="alert-train" style="display:none">
                                <div><span class="error-alert-message">Train added !</span>
                                </div>
                            </div> 
                            <input type="text" class="pure-input-1-2" placeholder="TrainName"
                                   value="" name="trainName" required>
                            <input type="text" class="pure-input-1-2" placeholder="seats"
                                   value="" name="trainSeats" required>
                        </fieldset>

                        <button type="submit" class="pure-button pure-button-active">Add train</button>
                    </form>

                    <form method="post" class="pure-form" action="/controllers/ShowPassengers">
                        <fieldset class="pure-group">
                            <legend>Show passengers</legend>
                            <input type="text" class="pure-input-1-2" placeholder="RouteId"
                                   name="RouteId" required>
                        </fieldset>

                        <button type="submit" class="pure-button pure-button-active">Show passengers</button>
                    </form>

                    <form method="post" class="pure-form" action="/controllers/ShowAllTrains">
                        <fieldset class="pure-group">
                            <legend>Show all trains</legend>
                        </fieldset>

                        <button type="submit" class="pure-button pure-button-active">Show all trains</button>
                    </form>

                    <form method="post" class="pure-form" action="/controllers/InsertNewRouteServlet">
                        <fieldset class="pure-group">
                            <legend>Add route</legend>
                        </fieldset>
                        <div id="isValid" style="display:none">
                                <input id="isRouteEntityInputSucceed" value=${requestScope.isRouteEntityInputSucceed}>

                            </div>
                            <div class="input-alert" id="alert-routeEntity" style="display:none">
                                <div><span class="error-alert-message">Route entity added !</span>
                                </div>
                            </div> 
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
