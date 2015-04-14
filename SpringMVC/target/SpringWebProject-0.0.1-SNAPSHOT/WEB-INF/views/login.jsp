<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link type="text/css" rel="stylesheet" 
      href=" <c:url value="/resources/css/login.css" /> "/>
<script src=" <c:url value="/resources/scripts/jquery.js" /> "></script>
<script>
    $(document).ready(function ()
    {
        if (document.getElementById('isLoginInValid').value == "true") {
            var o = document.getElementById('alert-error');
            o.style.display = 'block';
        }
    });
</script>
<div id="content" class="clearfix">
    <div id="page-content">
        <div id="isValid" style="display:none">
            <input id="isLoginInValid" value=${param.isLoginInValid}>

        </div>
        <div class="error-alert" id="alert-error" style="display:none">
            <div><span class="error-alert-message"><spring:message code="login.incorrect" /></span>
            </div>
        </div>    

        <section class="loginform cf">
            <div class="login">
                <!--<form:form method="POST" commandName="passenger" action="checkUser" id="slick-login">
                    <form:input path="lastName" class="placeholder" placeholder="login"/>
                    <form:password path="password" class="placeholder" placeholder="password"/>
                    <p class="submit"><input type="submit" name="commit" value="Login"></p>
                    </form:form>-->

                <form method="POST" action="<c:url value="/j_spring_security_check"/>" id="slick-login">
                    <input type="text" name="j_username" class="placeholder" placeholder="login"/>
                    <input type="password" name="j_password" class="placeholder" placeholder="password"/>
                    <p class="submit"><input type="submit" name="commit" value="Login"></p>
                </form>    
            </div>
        </section>



    </div><!--body end-->
    <div id="aside">
    </div>    
</div>