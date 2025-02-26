<%@ page import="com.ns.ecommerce.main.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/checkout.css">
    
    <title>Checkout</title>

</head>
<body>

<div class="container">
    <% 
        User user = (User) session.getAttribute("user"); 
        if (user == null) { 
    %>
        <p class="login-message">Please log in before placing an order.</p>
        <a href="/users/login" class="login-btn">Log In</a>
    <% } else { %>
        <form action="/order/place" method="post" class="checkout-form">
            <label for="name">Full Name</label>
            <input type="text" id="name" name="name" value="<%= user.getName() %>" readonly>

            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="<%= user.getEmail() %>" readonly>

            <label for="address">Shipping Address</label>
            <textarea id="address" name="address" required></textarea>

            <label for="payment">Payment Method</label>
            
            <select id="payment" name="payment">
                <option value="cod">Cash on Delivery</option>
                <option value="card">Credit/Debit Card</option>
                <option value="upi">UPI</option>
            </select>

            <button type="submit" class="checkout-btn">Place Order</button>
        </form>
    <% } %>
</div>

</body>
</html>