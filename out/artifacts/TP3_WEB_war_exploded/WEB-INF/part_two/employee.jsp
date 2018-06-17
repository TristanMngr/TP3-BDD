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
    <h1>Employee found</h1>
    <c:choose>
        <c:when test="${!empty employee.empno}">
            <table class="ui celled table">
                <thead>
                <tr>
                    <th>Empno</th>
                    <th>Ename</th>
                    <th>Efirst</th>
                    <th>Job</th>
                    <th>Manager</th>
                    <th>Hiredate</th>
                    <th>Salaire</th>
                    <th>Comm</th>
                    <th>Telephone</th>
                    <th>deptno</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><c:out value="${employee.empno}"/></td>
                    <td><c:out value="${employee.ename}"/></td>
                    <td><c:out value="${employee.efirst}"/></td>
                    <td><c:out value="${employee.job}"/></td>
                    <td><c:out value="${employee.mgrId}"/></td>
                    <td><c:out value="${employee.hiredate}"/></td>
                    <td><c:out value="${employee.sal}"/></td>
                    <td><c:out value="${employee.comm}"/></td>
                    <td><c:out value="${employee.tel}"/></td>
                    <td><c:out value="${employee.depnoId}"/></td>
                </tr>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="ui negative message">
                <div>No employee found</div>
            </div>
        </c:otherwise>
    </c:choose>
    <a class="ui basic blue button" href="<c:url value="/index.jsp"/>">Welcome page</a>
</div>
</body>
</html>
