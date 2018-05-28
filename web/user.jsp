<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${theLocale}"/>

<fmt:setBundle basename="mylabels"/>

<html>
<head>
    <title>ATP - User page</title>
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
<form method="get" action="MainServlet">
    <div align=right>
        <a href="user.jsp?theLocale=en_US"><img src="img\us.png"></a>&nbsp;&nbsp;
        <a href="user.jsp?theLocale=ru_RU"><img src="img\ru.png"></a>&nbsp;&nbsp;
        <a href="user.jsp?theLocale=uk_UA"><img src="img\ua.png"></a>&nbsp;&nbsp;
    </div>
    <div align="center">
        <c:set var="loggedUser" value="${LOGGED_USER}" scope="session"/>
        <br/>
        <h3>${loggedUser.driverName}<br/>
            <fmt:message key="label.workplace"/></h3>
        <br/>

        <table class="table-striped" width="50%" border="1">
            <tr>
                <td>&nbsp;</td>
                <td align="right"><fmt:message key="label.busnumber"/></td>
                <td>&nbsp;</td>
                <td align="left">${loggedUser.busID}</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td align="right"><fmt:message key="label.busname"/></td>
                <td>&nbsp;</td>
                <td align="left">${loggedUser.busName}</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td align="right"><fmt:message key="label.routename"/></td>
                <td>&nbsp;</td>
                <td align="left">${loggedUser.routeName}</td>
            </tr>
        </table>

        <%--<table class="table-striped" width="70%" border="0">--%>
        <%--<tr align="center">--%>
        <%--<th>№№</th>--%>
        <%--<th><fmt:message key="label.drivername"/></th>--%>
        <%--<th><fmt:message key="label.busname"/></th>--%>
        <%--<th><fmt:message key="label.routename"/></th>--%>
        <%--<th><fmt:message key="label.action"/></th>--%>
        <%--</tr>--%>
        <%--<c:forEach var="tempDriver" items="${DRIVER_LIST}">--%>
        <%--<c:url var="deleteLink" value="MainServlet">--%>
        <%--<c:param name="command" value="DELETEDRIVER"/>--%>
        <%--<c:param name="driverID" value="${tempDriver.userID}"/>--%>
        <%--<c:param name="theLocale" value="${theLocale}"/>--%>
        <%--</c:url>--%>

        <%--<tr>--%>
        <%--<td align="center"> ${tempDriver.userID} </td>--%>
        <%--<td align="center"><c:choose>--%>
        <%--<c:when test="${tempDriver.confirmed==0}">--%>
        <%--<div class="bg-danger">&nbsp;${tempDriver.driverName}</div>--%>
        <%--</c:when>--%>
        <%--<c:otherwise>--%>
        <%--&nbsp;${tempDriver.driverName}--%>
        <%--</c:otherwise>--%>
        <%--</c:choose></td>--%>
        <%--<td align="center"> ${tempDriver.busName} </td>--%>
        <%--<td align="center"> ${tempDriver.routeName} </td>--%>
        <%--<td align="center">--%>
        <%--<div align="center" class="btn-block">--%>
        <%--<a class="btn-danger" href="${deleteLink}">--%>
        <%--<fmt:message key="label.delete"/></a>--%>
        <%--&nbsp;&nbsp;|&nbsp;&nbsp;--%>
        <%--<a class="btn-warning" href="${freeLink}">--%>
        <%--<fmt:message key="label.free"/></a>--%>
        <%--</div>--%>
        <%--</td>--%>
        <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--</table>--%>
        <br/>


        <c:choose>
            <c:when test="${loggedUser.confirmed==0}">
                <input name="command" value="CONFIRM" type="hidden">
                <input name="theLocale" value="${theLocale}" type="hidden">
                <input name="driverID" value="${loggedUser.userID}" type="hidden">
                <input type="submit" class="btn btn-info" value="<fmt:message key="label.confirm"/>">
            </c:when>
            <c:otherwise>
                &nbsp;
            </c:otherwise>
        </c:choose>
    </div>
</form>

<script src="webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<div class="footer" align="center">
    <font size="-1"> &copy; GreegAV 2018</font>
</div>

</body>
</html>
