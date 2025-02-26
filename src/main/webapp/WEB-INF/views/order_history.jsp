<%@ page import="java.util.List" %>
<%@ page import="com.ns.ecommerce.main.model.Order" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% 
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    if (orders == null || orders.isEmpty()) { 
%>
    <div>
        <p>No order history found.</p>
        <a href="/" class="checkout-btn">Continue Shopping</a>
    </div>
<% } else { %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/orderHistory.css">
    
    <title>Order History</title>

</head>
<body>

    <div class="container">
        <h2>Your Order History</h2>

        <table>
            <tr>
                <th>Order ID</th>
                <th>Products</th>
                <th>Total Amount</th>
                <th>Payment Mode</th>
                <th>Order Date</th>
                <th>Status</th>
            </tr>

            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.productNames}</td>
                    <td>&#8377; ${order.totalAmount}</td>
                    <td>${order.paymentMode}</td>
                    <td>${order.orderDate}</td>
                    <td class="status">${order.status}</td>
                </tr>
            </c:forEach>

        </table>

        <a href="/" class="checkout-btn">Continue Shopping</a>
    </div>

</body>
</html>

<% } %>