<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${theLocale}"/>

<fmt:setBundle basename="mylabels"/>

<html>
<head>
    <title>ATP - Admin page - Bus list</title>
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
        <a href="busList.jsp?theLocale=en_US"><img src="img\us.png"></a>&nbsp;&nbsp;
        <a href="busList.jsp?theLocale=ru_RU"><img src="img\ru.png"></a>&nbsp;&nbsp;
        <a href="busList.jsp?theLocale=uk_UA"><img src="img\ua.png"></a>&nbsp;&nbsp;
    </div>
    <div align="center">
        <br/>
        <table class="table-striped" width="70%">
            <tr>
                <th width="5%" align="center">№№</th>
                <th width="3%" align="center">&nbsp;</th>
                <th width="15%" align="center"><fmt:message key="label.drivername"/></th>
                <th width="15%" align="center"><fmt:message key="label.busname"/></th>
                <th width="57%" align="center"><fmt:message key="label.routename"/></th>
                <th width="5%" align="center">&nbsp;</th>
            </tr>

            <c:forEach var="tempBus" items="${BUSES_LIST}">

                <c:url var="deleteLink" value="MainServlet">
                    <c:param name="command" value="DELETEBUS"/>
                    <c:param name="busID" value="${tempBus.busID}"/>
                    <c:param name="theLocale" value="${theLocale}"/>
                </c:url>

                <tr>
                    <td align="center"> ${tempBus.busID} </td>
                    <td>&nbsp;</td>
                    <td> ${tempBus.busName} </td>
                    <td> ${tempBus.driverName} </td>
                    <td> ${tempBus.routeName} </td>
                    <td>
                        <a class="btn-danger btn-block" href="${deleteLink}">
                            &nbsp;<fmt:message key="label.delete"/>&nbsp;</a>
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
