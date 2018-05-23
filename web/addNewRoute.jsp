<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${theLocale}"/>

<fmt:setBundle basename="mylabels"/>

<html>
<head>
    <title>ATP - Admin page - Add new route</title>
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
        <a href="addNewRoute.jsp?theLocale=en_US"><img src="img\us.png"></a>&nbsp;&nbsp;
        <a href="addNewRoute.jsp?theLocale=ru_RU"><img src="img\ru.png"></a>&nbsp;&nbsp;
        <a href="addNewRoute.jsp?theLocale=uk_UA"><img src="img\ua.png"></a>&nbsp;&nbsp;
    </div>
    <div align="center">
        <br/><br/>
        &nbsp;
        <table width="33%" border="0">
            <tr>
                <td align="center">
                    <h3><fmt:message key="label.addnewroute"/></h3><br/>
                    <table>
                        <tr>
                            <td align="right">
                                <fmt:message key="label.routeStart"/>
                            </td>
                            <td>
                                <input type="text" name="routeStart" required>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <fmt:message key="label.routeFinish"/>
                            </td>
                            <td>
                                <input type="text" name="routeFinish" required>
                            </td>
                        </tr>
                    </table>
                    <br>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <br/>
                    <input name="command" value="ADDROUTE" type="hidden">
                    <input name="theLocale" value="${theLocale}" type="hidden">
                    <input class="btn btn-secondary" type="submit" value="<fmt:message key="label.addnewroute"/>">
                </td>
            </tr>
        </table>
        <br/><br/>

        <a href="admin.jsp?theLocale=${theLocale}"><img src="img\back.png"></a>
    </div>
</form>


<script src="webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<div class="footer" align="center">
    <font size="-1"> &copy; GreegAV 2018</font>
</div>

</body>
</html>
