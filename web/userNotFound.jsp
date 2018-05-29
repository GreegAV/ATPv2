<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Error page</title>
</head>
<body>
<c:url var="homeLink" value="MainServlet">
    <c:param name="command" value="LOGOUT"/>
    <c:param name="theLocale" value="${theLocale}"/>
</c:url>
<div align="center">
    <br />
    <img src="img\error.png"><br />
    <br/>    <br/>
    Такой пользователь не найден.<br />
    Или пароль неправильный. <br />
    Попробуйте еще раз.<br /><br />
    <font size="+1"> На этот раз внимательно :)</font>
    <br /><br /><br />
    <a class="btn btn-secondary" href="${homeLink}"><img src="img\back.png"></a>

</div>


    <div align="center"><font size="-1"> &copy; GreegAV 2018</font></div>

</body>
</html>
