<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
	
</head>
<body>

    <div class="container">
        <h2>Register</h2>
        <form id="registrationForm" action="/users/saveRegisterUser" method="post" onsubmit="return validatePasswords()">
            <label for="name">Full Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            <div id="passwordError" class="error-message">Passwords do not match.</div>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>

            <button type="submit">Register</button>
        </form>
    </div>
<script src="${pageContext.request.contextPath}/js/register.js"></script>

</body>
</html>