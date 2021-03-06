<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${theLocale}"/>

<fmt:setBundle basename="mylabels"/>

<html>
<head>
    <title>ATP - Admin page - Set bus for route</title>
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
    <a href="setBus.jsp?theLocale=en_US"><img src="img\us.png"></a>&nbsp;&nbsp;
    <a href="setBus.jsp?theLocale=ru_RU"><img src="img\ru.png"></a>&nbsp;&nbsp;
    <a href="setBus.jsp?theLocale=uk_UA"><img src="img\ua.png"></a>&nbsp;&nbsp;
</div>
<c:url var="homeLink" value="MainServlet">
    <c:param name="command" value="CHANGEPAGE"/>
    <c:param name="currentPage" value="0"/>
    <c:param name="theLocale" value="${theLocale}"/>
</c:url>
<form method="post" action="MainServlet">

    <div align="center">
        <br/>
        <h3><fmt:message key="label.setbus"/></h3>
        <br/>
        <table class="table-striped" align="center" width="70%" border="0">
            <tr>
                <th>№№</th>
                <th><fmt:message key="label.drivername"/></th>
                <th><fmt:message key="label.busname"/></th>
                <th><fmt:message key="label.routename"/></th>
            </tr>
            <c:forEach var="tempBus" items="${FREEBUSES_LIST}">
                <tr>
                    <td> ${tempBus.busID} </td>
                    <c:choose>
                        <c:when test="${tempBus.driverName eq 'Admin'}">
                            <td>-----</td>
                        </c:when>
                        <c:otherwise>
                            <td> ${tempBus.driverName} </td>
                        </c:otherwise>
                    </c:choose>
                    <td> ${tempBus.busName} </td>
                    <td><select name="busID" onchange="document.location=this.options[this.selectedIndex].value">
                        <c:forEach var="tempRoute" items="${FREEROUTES_LIST}">
                            <c:url var="setLink" value="MainServlet">
                                <c:param name="busID" value="${tempBus.busID}"/>
                                <c:param name="routeID" value="${tempRoute.routeID}"/>
                                <c:param name="command" value="SETBUS"/>
                            </c:url>
                            <c:choose>
                                <c:when test="${tempRoute.routeID==0}">
                                    <option value="${setLink}" selected disabled> ${tempRoute.routeName} </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${setLink}"> ${tempRoute.routeName} </option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="${homeLink}"><img src="img\back.png"></a>
    </div>

</form>
<script src="webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<div class="footer" align="right">
    <input class="btn btn-secondary" type="button"
           value="<fmt:message key="label.logout"/>"
           onclick="window.location.href='MainServlet?command=LOGOUT' ; return false;"/>&nbsp;&nbsp;&nbsp;
    <div align="center"><font size="-1"> &copy; GreegAV 2018</font></div>
</div>

</body>
</html>
