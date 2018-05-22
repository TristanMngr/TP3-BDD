<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tristanmenager
  Date: 22/05/2018
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Part One</title>
    <link rel="stylesheet" type="text/css" href="../../assets/css/semantic.css">
    <link rel="stylesheet" type="text/css" href="../../assets/css/custom.css">
</head>
<body>
<div class="ui container">
    <h1>Display Table</h1>
    <div class="ui segment">
        <p><c:out value="${table}" escapeXml="false"/></p>
    </div>
    <c:if test="${!empty errors}">
        <c:forEach items="${errors}" var="error">
            <div class="ui negative message">
                <div>${error}</div>
            </div>
        </c:forEach>
    </c:if>

    <a class="ui basic blue button" href="<c:url value="/index.jsp"/>">Welcome page</a>
</div>
</body>
</html>
