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
            if (document.getElementById('isInputValid').value == "false") {
                var o = document.getElementById('alert-error');
                o.style.display = 'block';
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

                    <!--<form class="pure-form pure-form-stacked" action="controllers/LoginServlet" method="GET">
                        <fieldset>
                    
                            <label for="lastName">Login</label>
                            <input id="lastName" type="text" placeholder="lastName">
                    
                            <label for="password">Password</label>
                            <input id="password" type="password" placeholder="password">
                    
                            <button type="submit" class="pure-button pure-button-primary">Sign in</button>
                        </fieldset>
                    </form>-->
                    <div id="isValid" style="display:none">
                        <input id="isInputValid" value=${isInputValid}>

                    </div>
                    <div class="error-alert" id="alert-error" style="display:none">
                        <div><span class="error-alert-message">Login or password is incorrect. Try one more time.</span>
                        </div>
                    </div>    
                    <form method="post" action="/controllers/LoginServlet">
                        <input type="text" name="lastName" required=""/>
                        <input type="password" name="password" required=""/>
                        <input type="submit" />
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
