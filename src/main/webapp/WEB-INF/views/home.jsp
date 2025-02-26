<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ns.ecommerce.main.model.Product"%>
<%@ page import="com.ns.ecommerce.main.model.Discount"%>
<%@ page import="com.ns.ecommerce.main.model.User"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    List<Discount> discounts = (List<Discount>) request.getAttribute("discounts");
    User loggedInUser = (session != null) ? (User) session.getAttribute("user") : null;
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ShoopEase</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">

</head>
<body>
    <nav class="navbar">
        <div class="nav-content">
            <div class="nav-brand">ShopEase</div>
            <div class="nav-links">
                <a href="/" class="nav-link"><i class="fas fa-home"></i> Home</a> 
                <a href="#" id="discount-link" class="nav-link"><i class="fa fa-tag fa-lg"></i>Discount</a> 
                <a href="/cart" class="nav-link"> <i class="fas fa-shopping-cart"></i> Cart <span id="cart-count">0</span></a>

                <div class="search-bar">
                    <input type="text" id="searchInput" placeholder="Search products...">
                </div>

                <% if (loggedInUser != null) { %>
                <a href="/order/history" class="nav-link"><i class="fas fa-box"></i>Order History</a> 
                <a href="/users/logout" class="nav-link"><i class="fas fa-sign-out-alt"></i> Logout</a>
                <% } else { %>
                <div class="dropdown">
                    <button class="dropbtn">
                        <i class="fas fa-sign-in-alt"></i> Login</button>
                    <div class="dropdown-content">
                        <a href="/users/login"><i class="fas fa-user"></i> User Login</a>
                        <a href="/admin/login"><i class="fas fa-user-shield"></i>Admin Login</a>
                    </div>
                </div>
                <a href="/users/register" class="nav-link"><i
                    class="fas fa-user-plus"></i> Register</a>
                <% } %>
            </div>
        </div>
    </nav>
    <div id="cart-message" style="display: none;"></div>

    <!-- Welcome Message -->
    <div id="welcome-msg" class="welcome-msg">
        Welcome,
        <%= (loggedInUser != null) ? loggedInUser.getName() : "Guest" %>!
    </div>

    <!-- Discount Section (Hidden by Default) -->
    <div id="discount-section" class="discount-section" style="display: none;">
        <h3 class="secial">Special Discount</h3>
        <div class="discount-grid">
            <% if (discounts != null && !discounts.isEmpty()) {
                for (Discount discount : discounts) { %>
            <div class="discount-card">
                <div class="discount-image">
                    <img src="<%= discount.getImagePath() %>" alt="<%= discount.getOfferName() %>">
                </div>
                <div class="discount-content">
                    <h3><%= discount.getOfferName() %></h3>
                    <p class="discount-percentage"><%= discount.getDiscountPercentage() %>% OFF</p>
                    <button class="apply-discount-btn" 
                            data-discount-percentage="<%= discount.getDiscountPercentage() %>"
                            data-discount-available="<%= discount.isAvailable() %>">
                        Apply Discount
                    </button>
                </div>
            </div>
            <% }
            } else { %>
            <p>No discounts available at the moment.</p>
            <% } %>
        </div>
    </div>

    <div class="container">
        <div class="product-grid" id="productGrid">
            <% if (products != null) {
                for (Product p : products) { %>
            <div class="product-card" data-name="<%= p.getName().toLowerCase() %>">
                <img src="<%= p.getImageUrl() %>" alt="<%= p.getName() %>">
                <h3><%= p.getName() %></h3>
                <p><%= p.getDescription() %></p>

                <p class="price">
                    <b>Price: ₹<span class="original-price"><%= p.getPrice() %></span></b>
                </p>

                <button class="add-to-cart-btn" data-product-id="<%= p.getId() %>">Add to Cart</button>

            </div>
            <% }
            } else { %>
            <p>No products available</p>
            <% } %>
        </div>
    </div>

    <footer>
        <div class="contact-form">
            <h2>Contact Us</h2>
            <form id="contactForm" action="/users/contactUs" method="post">
            
                <div class="form-group">
                    <label for="name">Name:</label> <input type="text" id="name" name="name" required>
                </div>
                
                <div class="form-group">
                    <label for="email">Email:</label> <input type="email" id="email" name="email" required>
                </div>
                
                <div class="form-group">
                    <label for="message">Message:</label> <textarea id="message" name="message" rows="1" required></textarea>
                </div>
                
                <div class="form-group">
                    <button type="submit">Send Message</button>
                </div>
            </form>
            <div id="formMessage" style="display: none; color: green; margin-top: 10px;"></div>
        </div>
        
        <p>Contact us: <a href="mailto:murthy.ns646@gmail.com">murthy.ns646@gmail.com</a></p>
        <p>&copy; 2025 Narasimhamurthy N S. All rights reserved.</p>
        
    </footer>
    <script src="${pageContext.request.contextPath}/js/home.js"></script>

</body>
</html>