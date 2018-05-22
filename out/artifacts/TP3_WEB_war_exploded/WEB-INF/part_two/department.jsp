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
    <title>Part Two</title>
    <link rel="stylesheet" type="text/css" href="../../assets/css/semantic.css">
    <link rel="stylesheet" type="text/css" href="../../assets/css/custom.css">
</head>
<body>
<div class="ui container">
    <h1>Department found</h1>
    <c:choose>
        <c:when test="${!empty department.loc}">
            <table class="ui celled table">
                <thead>
                <tr>
                    <th>Location</th>
                    <th>Name</th>
                    <th>Deptno</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><c:out value="${department.loc}"/></td>
                    <td><c:out value="${department.dname}"/></td>
                    <td><c:out value="${department.deptno}"/></td>
                </tr>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="ui negative message">
                <div>No Department found</div>
            </div>
        </c:otherwise>
    </c:choose>
    <a class="ui basic blue button" href="<c:url value="/index.jsp"/>">Welcome page</a>
</div>
</body>
</html>
