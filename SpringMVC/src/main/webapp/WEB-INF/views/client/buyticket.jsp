<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link type="text/css" rel="stylesheet" href=" <c:url value="/resources/css/findTickets.css" /> "/>
<script src=" <c:url value="/resources/scripts/jquery.js" /> "></script>
<script>
    $(document).ready(function ()
    {
        if (document.getElementById('isEnoughTickets').value == "false") {
            var o = document.getElementById('isEnoughTickets-error');
            o.style.display = 'block';
        } else {
            var o = document.getElementById('isEnoughTickets-error');
            o.style.display = 'none';
        }
        if (document.getElementById('isPassengerOnTrain').value == "false") {
            var o = document.getElementById('isPassengerOnTrain-error');
            o.style.display = 'block';
        } else {
            var o = document.getElementById('isPassengerOnTrain-error');
            o.style.display = 'none';
        }

        if (document.getElementById('isMoreTh10min').value == "false") {
            var o = document.getElementById('isMoreTh10min-error');
            o.style.display = 'block';
        } else {
            var o = document.getElementById('isMoreTh10min-error');
            o.style.display = 'none';
        }
    });
</script>
<div id="content" class="clearfix">
    <div id="page-content">
        <form class="pure-form pure-form-aligned" action="buyTicket?index=${param.index}" method="post">
            <fieldset>
                <div class="pure-control-group">
                    <label for="firstName"><spring:message code="tickets.FirstName" /></label>
                    <input id="firstName" type="text" placeholder="firstName" name="firstName" 
                           value="${requestScope["firstName"]}" required>
                </div>
                <div class="pure-control-group">
                    <label for="lastName"><spring:message code="tickets.LastName" /></label>
                    <input id="lastName" type="text" placeholder="lastName" name="lastName" 
                           value="${requestScope["lastName"]}" required>
                </div>
                <div class="pure-control-group">
                    <label for="birthday"><spring:message code="tickets.Birthday" /></label>
                    <input id="name" type="date" placeholder="birthday" name="birthday" 
                           value="${requestScope["birthday"]}" required>
                </div>

                <div class="pure-control-group">
                    <label for="cityFrom"><spring:message code="home.cityFrom" /></label>
                    <input id="cityFrom" type="text" placeholder="cityFrom" 
                           name="cityFrom" value="<%= request.getSession().getAttribute("cityFrom")%>" readonly>
                </div>

                <div class="pure-control-group">
                    <label for="cityTo"><spring:message code="home.cityTo" /></label>
                    <input id="cityTo" type="text" placeholder="cityTo" 
                           name="cityTo" value="<%= request.getSession().getAttribute("cityTo")%>" readonly>
                </div>
                <div class="pure-control-group">
                    <label for="depatureDate"><spring:message code="trains.DepartureTime" /></label>

                    <input id="depatureDate" type="date" placeholder="depatureDate" 
                           name="depatureDate" 
                           value="<fmt:formatDate value="${tickets.get(param.index).getDepatureTime()}" pattern="yyyy-MM-dd" />" readonly>
                </div>

                <div class="pure-controls">

                    <button type="submit" class="pure-button pure-button-primary"><spring:message code="tickets.submit" /></button>
                </div>
            </fieldset>
        </form>

    </div><!--body end-->  

    <div id="aside">
        <div id="isValid" style="display:none">
            <input id="isEnoughTickets" value=${isEnoughTickets}>

        </div>
        <div class="error-alert" id="isEnoughTickets-error" style="display:none">
            <div><span class="error-alert-message"><spring:message code="tickets.noSeats" /></span>
            </div>
        </div> 
        <div id="isValid" style="display:none">
            <input id="isPassengerOnTrain" value=${isPassengerOnTrain}>

        </div>
        <div class="error-alert" id="isPassengerOnTrain-error" style="display:none">
            <div><span class="error-alert-message"><spring:message code="tickets.alreadyReg" /></span>
            </div>
        </div> 
        <div id="isValid" style="display:none">
            <input id="isMoreTh10min" value=${isMoreTh10min}>

        </div>
        <div class="error-alert" id="isMoreTh10min-error" style="display:none">
            <div><span class="error-alert-message"><spring:message code="tickets.10min" /></span>
            </div>
        </div> 
    </div>         
</div><!--content end-->