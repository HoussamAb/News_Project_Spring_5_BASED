<%--
  Created by IntelliJ IDEA.
  User: Houssam
  Date: 26/04/2020
  Time: 00:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>index page</title>
</head>
<body>
<c:forEach items="${session}"   var="users">
            <p> user="${users.key}" <p/>
            <p> role="${users.value}" <p/>

</c:forEach>

</body>
</html>
