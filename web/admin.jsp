<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}" scope="session"/>
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
    <a href="admin.jsp?theLocale=en_US"><img src="img\us.png"></a>&nbsp;&nbsp;
    <a href="admin.jsp?theLocale=ru_RU"><img src="img\ru.png"></a>&nbsp;&nbsp;
    <a href="admin.jsp?theLocale=uk_UA"><img src="img\ua.png"></a>&nbsp;&nbsp;
</div>
<div align="center">
    <br/><br/>
    &nbsp;
    <input class="btn btn-secondary" type="button" value="<fmt:message key="label.addnewuser"/>"
           onclick="window.location.href='addNewUser.jsp'; return false;"/>
    &nbsp;
    &nbsp;
    &nbsp;
    <input class="btn btn-secondary" type="button" value="<fmt:message key="label.addnewbus"/>"
           onclick="window.location.href='addNewBus.jsp' ; return false;"/>
    &nbsp;
    &nbsp;
    &nbsp;
    <input class="btn btn-secondary" type="button" value="<fmt:message key="label.addnewroute"/>"
           onclick="window.location.href='addNewBus.jsp' ; return false;"/>

    <br/><br/>

</div>

<%--<table align="center" width="25%" border="0">--%>
<%--<tr>--%>
<%--<td align="center">--%>
<%--Login<br> <input type="text" name="nameInput" required><br>--%>
<%--Password<br> <input type="password" name="passInput" required><br>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>&nbsp;</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td align="center">--%>
<%--<input name="command" type="submit" class="btn btn-secondary" value="Login" />--%>
<%--&lt;%&ndash;<input type="submit" name="command" value="LoginCommand">&ndash;%&gt;--%>
<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--</form>--%>


<script src="webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<div class="footer" align="center">
    <font size="-1"> &copy; GreegAV 2018</font>
</div>

</body>
</html>
