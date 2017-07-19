<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Http Session</title>
    <%
        HttpSession httpSession = (HttpSession) session.getAttribute("session");
        String username = (String) session.getAttribute("username");
    %>
</head>
<body>
    Username <b><%=username%></b> or password is invalid! Please try again.
    <br>
    Your session id is <%=httpSession.getId()%>
</body>
</html>
