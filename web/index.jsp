<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
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
</head>

<body>
<div align="center">
  <form name="loginForm" method="post" action="MainServlet">
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
          <input name="command" type="submit" class="btn btn-secondary" value="Login" />
          <%--<input type="submit" name="command" value="LoginCommand">--%>
        </td>
      </tr>
    </table>
  </form>
</div>
<jsp:include page="footer.jsp" />
<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</body>
</html>
