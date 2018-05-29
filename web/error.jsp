<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${theLocale}"/>

<fmt:setBundle basename="mylabels"/>

<html>
<head>
    <title>Error page</title><link href="webjars/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 60px;
            background-color: #f5f5f5;
        }
    </style>

</head>
<body>
<div align="center">
    <br /> <br /> <br />
    <img src="img\error.png"><br />
            <a onclick="history.back(); return false;"><img src="img\back.png"></a>
    <div class="footer" align="right">
        <input class="btn btn-secondary" type="button"
               value="<fmt:message key="label.logout"/>"
               onclick="window.location.href='MainServlet?command=LOGOUT' ; return false;"/>&nbsp;&nbsp;&nbsp;
        <div align="center"><font size="-1"> &copy; GreegAV 2018</font></div>
    </div>
</div>


</body>
</html>
