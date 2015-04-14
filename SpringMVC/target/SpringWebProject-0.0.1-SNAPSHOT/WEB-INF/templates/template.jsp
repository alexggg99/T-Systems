<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="utf-8"/>
        <title>Transport Company</title>
        <meta name="keywords" content="Transport Company"/>
        <meta name="description" content="Transport Company"/>
        <link type="text/css" rel="stylesheet" 
              href=" <c:url value="/resources/css/common.css" /> "/>
        <link type="text/css" rel="stylesheet" 
              href=" <c:url value="/resources/css/menu.css" /> "/>
        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
    </head>
    <body>
        <div id="wrapper">
            <tiles:insertAttribute name="header" />
            <tiles:insertAttribute name="body" />
        </div>
    </body>
</html>