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
        <a href="setDriver.jsp?theLocale=en_US"><img src="img\us.png"></a>&nbsp;&nbsp;
        <a href="setDriver.jsp?theLocale=ru_RU"><img src="img\ru.png"></a>&nbsp;&nbsp;
        <a href="setDriver.jsp?theLocale=uk_UA"><img src="img\ua.png"></a>&nbsp;&nbsp;
    </div>
    <div align="center">
        <br/>
        <h3><fmt:message key="label.setdriver"/></h3>
        <br/>
        <table class="table-striped" width="70%">
            <tr>
                <th align="center">№№</th>
                <th align="center">&nbsp;</th>
                <th align="center"><fmt:message key="label.routename"/></th>
                <th align="center"><fmt:message key="label.drivername"/></th>
                <th align="center"><fmt:message key="label.busname"/></th>
            </tr>
            <c:forEach var="tempDriver" items="${DRIVER_LIST}">
                <tr>
                    <td align="center"> ${tempDriver.userID} </td>
                    <td>&nbsp;</td>
                    <td> ${tempDriver.routeName} </td>
                    <td> ${tempDriver.driverName} </td>
                    <td><select name="busID" onchange="document.location=this.options[this.selectedIndex].value">
                        <c:forEach var="tempBus" items="${BUSES_LIST}">
                            <c:url var="setLink" value="MainServlet">
                                <c:param name="driverID" value="${tempDriver.userID}"/>
                                <c:param name="busID" value="${tempBus.busID}"/>
                                <c:param name="command" value="SETDRIVER"/>
                            </c:url>
                            <c:choose>
                                <c:when test="${tempDriver.busID==tempBus.busID}">
                                    <option value="${setLink}" selected> ${tempBus.busName} </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${setLink}"> ${tempBus.busName} </option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="admin.jsp?theLocale=${theLocale}"><img src="img\back.png"></a>
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
