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
    <title>Part Three</title>
    <link rel="stylesheet" type="text/css" href="../../assets/css/semantic.css">
    <link rel="stylesheet" type="text/css" href="../../assets/css/custom.css">
</head>
<body>
<div class="ui container">
    <h1>Beans created with these tables</h1>
    <c:choose>
        <c:when test="${!empty tables}">
            <c:forEach var="table" items="${tables}">
                <h3 class="ui top attached block header"><c:out value="${table.key}"/></h3>
                <div class="ui form bottom attached segment" action="/part-three/generate-beans" method="post">
                    <c:forEach var="column" items="${table.value}">
                        <c:out value="${column.key}"/>(<c:out value="${column.value}"/>)<br>
                    </c:forEach>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="ui negative message">
                <div>Beans Already Created (remove beans if you want to try it)</div>
            </div>
        </c:otherwise>
    </c:choose>

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
