<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ns.ecommerce.main.model.Product" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cart.css">
    
</head>
<body>
    <div class="cart-container">
        <h2>Your Shopping Cart</h2>

        <% List<Product> cart = (List<Product>) session.getAttribute("cart"); %>

        <% if (cart == null || cart.isEmpty()) { %>
            <p class="empty-cart">Your cart is empty</p>
            <a href="/" class="continue-shopping">Add Products</a>
        <% } else { %>
            <table class="cart-table">
                <thead>
                    <tr>
                        <th>Image</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="cart-body">
                    <% double total = 0; %>
                    <% for (Product p : cart) { 
                        total += p.getPrice(); 
                    %>
                        <tr id="row-<%= p.getId() %>">
                            <td>
                                <img src="<%= p.getImageUrl() %>" alt="<%= p.getName() %>" class="cart-img">
                            </td>
                            <td><%= p.getName() %></td>
                            <td>&#8377; <span class="product-price"><%= p.getPrice() %></span></td>
                            <td>
                                <button class="remove-btn" onclick="removeFromCart(<%= p.getId() %>)">Remove</button>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

            <div class="cart-actions">
                <a href="/" class="continue-shopping">Continue Shopping</a>
                <div class="cart-total">
                    <h3>Total Price: &#8377; <span id="total-price"><%= total %></span></h3>
                </div>
                <a href="/order/checkout" class="checkout-btn">Proceed to Checkout</a>
            </div>
        <% } %>
    </div>

  <script src="${pageContext.request.contextPath}/js/cart.js"></script>

</body>
</html>