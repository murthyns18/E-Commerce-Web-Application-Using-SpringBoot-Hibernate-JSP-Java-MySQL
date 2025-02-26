<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Manage Orders</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminOrdrMngt.css">
    
</head>
<body>

    <div class="container">
        <h2>Admin Order Management</h2>

        <c:if test="${empty orders}">
            <p class="no-orders">No orders found.</p>
            <a href="/admin/dashboard" class="btn-back">Back to Dashboard</a>
        </c:if>

        <c:if test="${not empty orders}">
            <table>
                <tr>
                    <th>Order ID</th>
                    <th>Products</th>
                    <th>Total Amount</th>
                    <th>Payment Mode</th>
                    <th>Order Date</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.productNames}</td>
                        <td>₹${order.totalAmount}</td>
                        <td>${order.paymentMode}</td>
                        <td>${order.orderDate}</td>
                        <td class="status">${order.status}</td>
                        <td>
                            <c:if test="${order.status != 'Shipped'}">
                                <form action="/admin/shipOrder" method="post">
                                    <input type="hidden" name="orderId" value="${order.orderId}">
                                    <button type="submit" class="btn-ship">Ship Now</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <br>
        <a href="/admin/dashboard" class="btn-back">Back to Dashboard</a>
    </div>

</body>
</html>