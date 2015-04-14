<%@page import="ru.tsystems.project.domain.entities.RouteEntity"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transport Company</title>
        <meta name="keywords" content="Transport Company"/>
        <meta name="description" content="Transport Company"/>
        <link type="text/css" rel="stylesheet" href="../css/findTickets.css"/>
        <link type="text/css" rel="stylesheet" href="../css/common.css"/>
        <link type="text/css" rel="stylesheet" href="../css/menu.css"/>
        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
        <script type="text/javascript" src="master-script.js"></script>
        <script src="/scripts/jquery.js"></script>
    </head>
    <script>
        $(document).ready(function ()
        {
            if (document.getElementById('isEnoughTickets').value == "false") {
                var o = document.getElementById('isEnoughTickets-error');
                o.style.display = 'block';
            }else{
                var o = document.getElementById('isEnoughTickets-error');
                o.style.display = 'none';
            }
            if (document.getElementById('isPassengerOnTrain').value == "false") {
                var o = document.getElementById('isPassengerOnTrain-error');
                o.style.display = 'block';
            }else{
                var o = document.getElementById('isPassengerOnTrain-error');
                o.style.display = 'none';
            }

            if (document.getElementById('isMoreTh10min').value == "false") {
                var o = document.getElementById('isMoreTh10min-error');
                o.style.display = 'block';
            }else{
                var o = document.getElementById('isMoreTh10min-error');
                o.style.display = 'none';
            }
        });
    </script>
    <body>
        <div id="wrapper">
            <div id="nav">
                <div class="menu">
                    <li><a href="../index.html">Home</a></li>
                </div>
            </div><!--nav end-->
            <div id="content" class="clearfix">
                <div id="page-content">
                    <form class="pure-form pure-form-aligned" action="/controllers/BuyTicketServlet?item=${param.item}" method="post">
                        <fieldset>
                            <div class="pure-control-group">
                                <label for="firstName">First Name</label>
                                <input id="firstName" type="text" placeholder="firstName" name="firstName" required>
                            </div>
                            <div class="pure-control-group">
                                <label for="lastName">Last Name</label>
                                <input id="lastName" type="text" placeholder="lastName" name="lastName" required>
                            </div>
                            <div class="pure-control-group">
                                <label for="birthday">Birthday</label>
                                <input id="name" type="date" placeholder="birthday" name="birthday" required>
                            </div>

                            <div class="pure-control-group">
                                <label for="cityFrom">City from</label>
                                <input id="cityFrom" type="text" placeholder="cityFrom" 
                                       name="cityFrom" value="<%= request.getSession().getAttribute("cityFrom")%>">
                            </div>

                            <div class="pure-control-group">
                                <label for="cityTo">City to</label>
                                <input id="cityTo" type="text" placeholder="cityTo" 
                                       name="cityTo" value="<%= request.getSession().getAttribute("cityTo")%>">
                            </div>

                            <div class="pure-control-group">
                                <label for="depatureDate">Departure date</label>
                                <input id="depatureDate" type="date" placeholder="depatureDate" 
                                       name="depatureDate" value="<%= request.getSession().getAttribute("departureDate")%>">
                            </div>

                            <div class="pure-controls">

                                <button type="submit" class="pure-button pure-button-primary">Submit</button>
                            </div>
                        </fieldset>
                    </form>

                </div><!--body end-->  

                <div id="aside">
                    <div id="isValid" style="display:none">
                        <input id="isEnoughTickets" value=${isEnoughTickets}>

                    </div>
                    <div class="error-alert" id="isEnoughTickets-error" style="display:none">
                        <div><span class="error-alert-message">There are no free seats on train !!</span>
                        </div>
                    </div> 
                    <div id="isValid" style="display:none">
                        <input id="isPassengerOnTrain" value=${isPassengerOnTrain}>

                    </div>
                    <div class="error-alert" id="isPassengerOnTrain-error" style="display:none">
                        <div><span class="error-alert-message">The passenger is alreade registed on train!!</span>
                        </div>
                    </div> 
                    <div id="isValid" style="display:none">
                        <input id="isMoreTh10min" value=${isMoreTh10min}>

                    </div>
                    <div class="error-alert" id="isMoreTh10min-error" style="display:none">
                        <div><span class="error-alert-message">Less than 10 minutes till departure!!</span>
                        </div>
                    </div> 
                </div>         
            </div><!--content end-->

            <div id="empty-div"></div>
        </div>	
        <div id="footer">
            <span>2015. Transport company. All rights reserved.</span>
        </div><!--footer end-->

    </body>
</html>