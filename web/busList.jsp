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
        <table>
            <tr>
                <th>Bus ID</th>
                <th>Bus Name</th>
                <th>Driver Name</th>
                <th>Bus Route</th>
            </tr>

            <c:forEach var="tempBus" items="${BUS_LIST}">

                <c:url var="deleteLink" value="MainServlet">
                    <c:param name="command" value="DELETEBUS"/>
                    <c:param name="busID" value="${tempBus.userID}"/>
                </c:url>

                <tr>
                    <td> ${tempBus.busID} </td>
                    <td> ${tempBus.busName} </td>
                    <td> ${tempBus.driverName} </td>
                    <td> ${tempBus.busRoute} </td>
                    <td>
                        <a href="${deleteLink}">
                            Delete</a>
                    </td>
                </tr>

            </c:forEach>

        </table>
        <br/>
        <a href="admin.jsp"><img src="img\back.png"></a>
    </div>
</form>


<script src="webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<div class="footer" align="center">
    <font size="-1"> &copy; GreegAV 2018</font>
</div>

</body>
</html>