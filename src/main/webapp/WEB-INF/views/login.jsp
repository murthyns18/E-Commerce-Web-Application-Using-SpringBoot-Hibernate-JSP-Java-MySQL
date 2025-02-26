<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userLogin.css">

</head>
<body>

    <div class="container">
        <h2>Login</h2>
        
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <form action="/users/checkLogin" method="post">
            <label>Email:</label><input type="email" name="email" required>

            <label>Password:</label><input type="password" name="password" required>

            <button type="submit">Login</button>
        </form>

        <p>New user? <a href="/users/register">Register here</a></p>
    </div>

</body>
</html>