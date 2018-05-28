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
        <a href="userConfirmed.jsp?theLocale=en_US"><img src="img\us.png"></a>&nbsp;&nbsp;
        <a href="userConfirmed.jsp?theLocale=ru_RU"><img src="img\ru.png"></a>&nbsp;&nbsp;
        <a href="userConfirmed.jsp?theLocale=uk_UA"><img src="img\ua.png"></a>&nbsp;&nbsp;
    </div>
    <div align="center">
        <c:set var="loggedUser" value="${LOGGED_USER}" scope="session"/>
        <br/>
        <h3>${loggedUser.driverName}</h3><br/>
        <table class="table-striped" width="50%" border="0">
            <tr>
                <td colspan="4">
                    <div align="center">
                        <h2>
                            <fmt:message key="label.workplace"/>
                        </h2>
                    </div>
                </td>
            </tr>
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
        <br/>

        <div class="btn btn-success"><fmt:message key="label.confirmed"/></div>
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
