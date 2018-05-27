<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${theLocale}"/>

<fmt:setBundle basename="mylabels"/>

<html>
<head>
    <title>ATP - Admin page - Routes list</title>
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
        <a href="routeList.jsp?theLocale=en_US"><img src="img\us.png"></a>&nbsp;&nbsp;
        <a href="routeList.jsp?theLocale=ru_RU"><img src="img\ru.png"></a>&nbsp;&nbsp;
        <a href="routeList.jsp?theLocale=uk_UA"><img src="img\ua.png"></a>&nbsp;&nbsp;
    </div>
    <div align="center">
        <br/>
        <h3><fmt:message key="label.removeroute"/></h3>
        <br/>
        <table class="table-striped" width="70%">
            <tr>
                <th align="center">№№</th>
                <th>&nbsp;</th>
                <th align="center"><fmt:message key="label.routename"/></th>
                <th align="center"><fmt:message key="label.busname"/></th>
                <th align="center"><fmt:message key="label.drivername"/></th>
                <th>&nbsp;</th>
                <th align="center"><fmt:message key="label.action"/></th>
            </tr>

            <c:forEach var="tempRoute" items="${ROUTES_LIST}">

                <c:url var="deleteLink" value="MainServlet">
                    <c:param name="command" value="DELETEROUTE"/>
                    <c:param name="routeID" value="${tempRoute.routeID}"/>
                    <c:param name="theLocale" value="${theLocale}"/>
                </c:url>

                <c:url var="freeLink" value="MainServlet">
                    <c:param name="command" value="FREEROUTE"/>
                    <c:param name="routeID" value="${tempRoute.routeID}"/>
                    <c:param name="theLocale" value="${theLocale}"/>
                </c:url>
                <tr>
                    <td align="center"> ${tempRoute.routeID} </td>
                    <td>&nbsp;</td>
                    <td> ${tempRoute.routeName} </td>
                    <td> ${tempRoute.busName} </td>
                    <td> ${tempRoute.driverName} </td>
                    <td>
                        <div class="btn-block">
                            <a class="btn-danger" href="${deleteLink}">
                                <fmt:message key="label.delete"/></a>
                            &nbsp;&nbsp;|&nbsp;&nbsp;
                            <a class="btn-warning" href="${freeLink}">
                                <fmt:message key="label.free"/></a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="admin.jsp?theLocale=${theLocale}"><img src="img\back.png"></a>
    </div>
</form>

<script src="webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<div class="footer" align="center">
    <font size="-1"> &copy; GreegAV 2018</font>
</div>

</body>
</html>
