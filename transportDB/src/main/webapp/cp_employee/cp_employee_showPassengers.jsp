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

<table id="customers">
  <tr>
    <th>Passenger Id</th>
    <th>First name</th>
    <th>Last name</th>
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
            </div><!--content end-->
            
            <div id="empty-div"></div>
        </div>	
        <div id="footer">
            <span>2015. Transport company. All rights reserved.</span>
        </div><!--footer end-->

    </body>
</html>