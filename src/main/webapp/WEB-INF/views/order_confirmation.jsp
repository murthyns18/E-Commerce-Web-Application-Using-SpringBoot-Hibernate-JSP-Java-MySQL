<%@ page import="com.ns.ecommerce.main.model.Order" %>

<% 
    Order order = (Order) session.getAttribute("lastOrder");
    if (order == null) { 
%>
<p class="no-order-message">No order found. Please place an order.</p>
<a href="/cart" class="go-to-cart-btn">Go to Cart</a>
<% } else { %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/orderConfirm.css">
    
    <title>Order Confirmation</title>

</head>
<body>

    <div class="container">
        <h2>Your Order is Confirmed!</h2>

        <div class="order-details">
            <p><strong>Order ID:</strong> <%= order.getOrderId() %></p>
            <p><strong>Product(s):</strong> <%= order.getProductNames() %></p>
            <p><strong>Total Amount Paid:</strong> &#8377; <%= order.getTotalAmount() %></p>
            <p><strong>Payment Mode:</strong> <%= order.getPaymentMode() %></p>
            <p><strong>Order Date:</strong> <%= order.getOrderDate() %></p>
        </div>

        <p class="success-message">
            Your order has been placed successfully! We will notify you via email when your order is shipped.
        </p>

        <a href="/" class="checkout-btn">Continue Shopping</a>
    </div>

</body>
</html>

<% } %>