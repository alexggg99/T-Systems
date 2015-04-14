<%-- 
    Document   : error_page
    Created on : Mar 1, 2015, 10:26:38 PM
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
        <link type="text/css" rel="stylesheet" href="../css/common.css"/>
        <link type="text/css" rel="stylesheet" href="../css/menu.css"/>
        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
        <script src="/scripts/jquery.js"></script>
    </head>
    <script>
        $(document).ready(function ()
        {
            if (document.getElementById('isTicketBought').value == "true") {
                var o = document.getElementById('isTicketBought-mess');
                o.style.display = 'block';
            }
        });
    </script>
    <body>
        <div id="wrapper">
            <div id="nav">
                <div class="menu">
                    <li><a href="../index.html">Home</a></li>
                    <li><a href="../login.jsp">Login</a></li>
                </div>
            </div><!--nav end-->
            <div id="content" class="clearfix">
                <div id="page-content">

                    <div id="isValid" style="display:none">
                        <input id="isTicketBought" value=${isTicketBought}>

                    </div>
                    <div class="error-alert" id="isTicketBought-mess" style="display:none">
                        <div><span class="error-alert-message">Ticket successfully bought !!</span>
                        </div>
                    </div> 

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

