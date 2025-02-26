<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/editProduct.css">

</head>
<body>

    <div class="container">
        <h2>Edit Product</h2>
        <form action="/admin/updateProduct" method="post">
            <input type="hidden" name="id" value="${product.id}">
            
            <div class="form-group">
                <label for="name">Product Name:</label>
                <input type="text" id="name" name="name" value="${product.name}" required>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" required>${product.description}</textarea>
            </div>

            <div class="form-group">
                <label for="price">Price (₹):</label>
                <input type="number" id="price" name="price" step="0.01" value="${product.price}" required>
            </div>

            <div class="form-group">
                <label>Current Image:</label>
                <br>
                <img src="${product.imageUrl}" alt="Product Image" style="max-width: 100px;">
            </div>

            <button type="submit">Update Product</button>
        </form>

        <a href="/admin/dashboard" class="back-link">Back to Dashboard</a>
    </div>

</body>
</html>
