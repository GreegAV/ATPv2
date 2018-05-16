<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}"
       scope="session"/>

<fmt:setLocale value="${theLocale}"/>

<fmt:setBundle basename="mylabels"/>

<html>
<head>
    <title>ATP</title>
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
    <%--<select>--%>
    <a href="index.jsp?theLocale=en_US">English (en_US)</a>&nbsp;&nbsp;
    <a href="index.jsp?theLocale=ru_RU">Russian (ru_RU)</a>&nbsp;&nbsp;
    <a href="index.jsp?theLocale=uk_UA">Ukrainian (uk_UA)</a>&nbsp;&nbsp;
    <%--</select>--%>
</div>
<div align="center">

    <div align="center">
        <form method="get" action="MainServlet">
            <table align="center" width="25%" border="0">
                <tr>
                    <td align="center">
                        Login<br> <input type="text" name="nameInput" required><br>
                        Password<br> <input type="password" name="passInput" required><br>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center">
                        <input name="theLocale" value="${theLocale}" type="hidden">
                        <input type="submit" class="btn btn-secondary" value=<fmt:message key="label.login"/>
                        <%--<input type="submit" name="command" value="LoginCommand">--%>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="footer" align="center">
        <br/>
        <br/>
        <font size="-1"> &copy; GreegAV 2018</font>
    </div>
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</body>
</html>
