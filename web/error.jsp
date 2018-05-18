<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Error page</title><link href="webjars/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 60px;
            background-color: #f5f5f5;
        }
    </style>

</head>
<body>
<div align="center">
    <br /> <br /> <br />
    <img src="img\error.png"><br />
    <%--Что-то пошло не так.<br />--%>
    <%--Попробуйте еще раз.<br /><br />--%>
   <%--<font size="+1"> На этот раз внимательно :)</font>--%>
    <%--<br /><br /><br />--%>
    <%--<a href="index.jsp">--%>
        <%--<input  method="post" onclick="history.back(); return false;" type="button" value="<fmt:message key="label.stepback"/>"  >--%>
            <a onclick="javascript:history.back(); return false;"><img src="img\back.png"></a>
    <%--</a>--%>
    <div class="footer" align="center">
        <br/>
        <br/>
        <font size="-1"> &copy; GreegAV 2018</font>
    </div>
</div>


</body>
</html>
