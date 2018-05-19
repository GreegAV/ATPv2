<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${theLocale}"/>

<fmt:setBundle basename="mylabels"/>

<html>
<head>
    <title>ATP - Admin page - Add new driver</title>
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
        <a href="removeDriver.jsp?theLocale=en_US"><img src="img\us.png"></a>&nbsp;&nbsp;
        <a href="removeDriver.jsp?theLocale=ru_RU"><img src="img\ru.png"></a>&nbsp;&nbsp;
        <a href="removeDriver.jsp?theLocale=uk_UA"><img src="img\ua.png"></a>&nbsp;&nbsp;
    </div>
    <div align="center">
        <br/>
        <table>

            <tr>
                <th>Driver ID</th>
                <th>Driver Name</th>
                <th>Driver Route</th>
                <th>Driver Bus</th>
            </tr>

            <c:forEach var="tempDriver" items="${DRIVER_LIST}">

                <c:url var="deleteLink" value="MainServlet">
                    <c:param name="command" value="DELETE" />
                    <c:param name="driverID" value="${tempDriver.id}" />
                </c:url>

                <tr>
                    <td> ${tempDriver.driverID} </td>
                    <td> ${tempDriver.driverName} </td>
                    <td> ${tempDriver.driverRoute} </td>
                    <td> ${tempDriver.driverBus} </td>
                    <td>
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this driver?'))) return false">
                            Delete</a>
                    </td>
                </tr>

            </c:forEach>

        </table>
        <br/>
        <a onclick="javascript:history.back(); return false;"><img src="img\back.png"></a>
    </div>
</form>


<script src="webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<div class="footer" align="center">
    <font size="-1"> &copy; GreegAV 2018</font>
</div>

</body>
</html>
