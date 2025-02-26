<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/discount.css">
    
    <title>Edit Discount</title>
</head>
<body>

    <div class="container">
        <h2>Edit Discount</h2>

        <!-- Success Message -->
        <c:if test="${not empty successMessage}">
            <div id="successMessage" class="success-message">${successMessage}</div>
        </c:if>

        <c:if test="${not empty discount}">
            <form action="/admin/updateDiscount" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${discount.id}">

                <label for="offerName">Offer Name:</label>
                <input type="text" id="offerName" name="offerName" value="${discount.offerName}" required>

                <label for="discountPercentage">Discount Percentage:</label>
                <input type="number" id="discountPercentage" name="discountPercentage" min="1" max="100" value="${discount.discountPercentage}" required>

                <label for="available">Available:</label>
                <select id="available" name="available">
                    <option value="true" ${discount.available ? 'selected' : ''}>Available</option>
                    <option value="false" ${!discount.available ? 'selected' : ''}>Unavailable</option>
                </select>

                <label for="offerImage">Select Offer Image:</label>
                <input type="file" id="offerImage" name="offerImage" accept="image/*">
                
                <button type="submit">Update Discount</button>
            </form>
        </c:if>

        <c:if test="${empty discount}">
            <p>No discount found. Please add one.</p>
            <form action="/admin/addDiscount" method="post" enctype="multipart/form-data">
                <label for="offerName">Offer Name:</label>
                <input type="text" id="offerName" name="offerName" required>

                <label for="discountPercentage">Discount Percentage:</label>
                <input type="number" id="discountPercentage" name="discountPercentage" min="1" max="100" required>

                <label for="available">Available:</label>
                <select id="available" name="available">
                    <option value="true">Available</option>
                    <option value="false">Unavailable</option>
                </select>
                
                <label for="offerImage">Select Offer Image:</label>
                <input type="file" id="offerImage" name="offerImage" accept="image/*">
                
                <button type="submit">Add Discount</button>
            </form>
        </c:if>

        <a href="/admin/dashboard" class="back-link">← Go Back</a>

    </div>
    <script src="${pageContext.request.contextPath}/js/discount.js"></script>

</body>
</html>
