<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 

<title>Add Product</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addProduct.css">

</head>
<body>

	<nav class="navbar">
		<div class="nav-content">
			<div class="nav-brand">Admin Dashboard</div>
			<div class="nav-links">
				<a href="/admin/dashboard"><i class="fas fa-arrow-left"></i>Back to Dashboard</a> 
				<a href="/admin/logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
			</div>
		</div>
	</nav>

	<div class="container">
	
		<h2>Add New Product</h2>
		
		<form action="/admin/addProduct" method="post" enctype="multipart/form-data">
			<input type="text" name="name" placeholder="Product Name" required>
			<input type="text" name="description" placeholder="Description" required> 
			<input type="number" name="price" placeholder="Price" required>
			<input type="file" name="image" required>
			<button type="submit"><i class="fas fa-plus"></i> Add Product</button>
		</form>
	</div>

</body>
</html>
