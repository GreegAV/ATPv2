<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<div align="center">
    <br />
    <img src="img\error.png"><br />
    <br/>    <br/>
    Такой пользователь не найден.<br />
    Или пароль неправильный. <br />
    Попробуйте еще раз.<br /><br />
    <font size="+1"> На этот раз внимательно :)</font>
    <br /><br /><br />
    <a href="index.jsp">
        <input type="button" value="В начало">
    </a>
    <jsp:include page="footer.jsp" />
</div>

<div class="footer" align="right">
    <input class="btn btn-secondary" type="button"
           value="<fmt:message key="label.logout"/>"
           onclick="window.location.href='MainServlet?command=LOGOUT' ; return false;"/>&nbsp;&nbsp;&nbsp;
    <div align="center"><font size="-1"> &copy; GreegAV 2018</font></div>
</div>
</body>
</html>
