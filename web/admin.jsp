<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${theLocale}"/>

<fmt:setBundle basename="mylabels"/>

<html>
<head>
    <title>ATP - Admin page</title>
    <link href="webjars/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 60px;
            background-color: #f5f5f5;
        }
    </style>
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
</head>

<body>
<div align=right>
    <a href="admin.jsp?theLocale=en_US"><img src="img\us.png"></a>&nbsp;&nbsp;
    <a href="admin.jsp?theLocale=ru_RU"><img src="img\ru.png"></a>&nbsp;&nbsp;
    <a href="admin.jsp?theLocale=uk_UA"><img src="img\ua.png"></a>&nbsp;&nbsp;
</div>
<div align="center">
<br/>
<h1><fmt:message key="label.welcome"/></h1>
<br/>
<div align="center">
    <table width="85%" border="1">
        <tr>
            <td width="20%">
                <input class="btn btn-outline-success btn-block" type="button" value="<fmt:message key="label.addnewdriver"/>"
                       onclick="window.location.href='addNewDriver.jsp?theLocale=${theLocale}'; return false;"/>
                <br/><br/><br/>
                <input class="btn btn-outline-success btn-block" type="button" value="<fmt:message key="label.addnewbus"/>"
                       onclick="window.location.href='addNewBus.jsp?theLocale=${theLocale}' ; return false;"/>
                <br/><br/><br/>
                <input class="btn btn-outline-success btn-block" type="button" value="<fmt:message key="label.addnewroute"/>"
                       onclick="window.location.href='addNewRoute.jsp?theLocale=${theLocale}' ; return false;"/>
            </td>
            <td width="60%">
                Список водителей с маршрутами и автобусами
            </td>
            <td width="20%">
                <input class="btn btn-outline-danger btn-block" type="button" value="<fmt:message key="label.removedriver"/>"
                       onclick="window.location.href='driverList.jsp?theLocale=${theLocale}'; return false;"/>
                <br/><br/><br/>
                <input class="btn btn-outline-danger btn-block" type="button" value="<fmt:message key="label.removebus"/>"
                       onclick="window.location.href='busList.jsp?theLocale=${theLocale}' ; return false;"/>
                <br/><br/><br/>
                <input class="btn btn-outline-danger btn-block" type="button" value="<fmt:message key="label.removeroute"/>"
                       onclick="window.location.href='routeList.jsp?theLocale=${theLocale}' ; return false;"/>
            </td>
        </tr>
    </table>
    &nbsp;

    &nbsp;
    &nbsp;
    &nbsp;

    &nbsp;
    &nbsp;
    &nbsp;


    <br/><br/>

</div>


<script src="webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<div class="footer" align="center">
    <font size="-1"> &copy; GreegAV 2018</font>
</div>

</body>
</html>
