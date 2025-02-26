<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">
    
</head>
<body>

    <nav class="navbar">
        <div class="nav-content">
            <div class="nav-brand">Admin Dashboard</div>
            	<div class="nav-links">
                	<a href="/admin/addProduct"><i class="fas fa-plus-circle"></i> Add Product</a>
                	<a href="/admin/discount"><i class="fas fa-tag"></i> Edit Discount</a>
                	<a href="/admin/orders"><i class="fas fa-truck"></i> Ship Items</a>
                	<a href="/admin/logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
            	</div>
        </div>
    </nav>

    <div class="container">
        <!-- Success Message -->
        <c:if test="${not empty successMessage}">
            <div class="success-message">${successMessage}</div>
        </c:if>
    
        <h2>Existing Products</h2>
        <div class="product-grid">
            <c:forEach var="product" items="${products}">
                <div class="product-card">
                   <img src="${product.imageUrl}" alt="${product.name}">
                    <h3>${product.name}</h3>
                    <p>${product.description}</p>
                    <p class="price"><b>₹${product.price}</b></p>
                    
                    <div class="buttons-container">
                        <a href="/admin/editProduct?id=${product.id}" class="edit-btn"><i class="fas fa-edit"></i> Edit</a>
                        
                        <form action="/admin/removeProduct" method="post" class="remove-form">
                            <input type="hidden" name="id" value="${product.id}">
                            <button type="submit" class="remove-btn"><i class="fas fa-trash"></i> Remove</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
	<script src="${pageContext.request.contextPath}/js/admin.js"></script>
	
</body>
</html>