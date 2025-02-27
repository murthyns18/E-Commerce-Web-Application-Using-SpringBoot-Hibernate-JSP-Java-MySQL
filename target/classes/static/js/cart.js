       function removeFromCart(productId) {
           fetch('/cart/remove', {
               method: 'POST',
               headers: {
                   'Content-Type': 'application/json',
               },
               body: JSON.stringify({ productId: productId }),
           })
           .then(response => response.json())
           .then(data => {
               if (data.status === "success") {
                   let row = document.getElementById("row-" + productId);
                   if (row) {
                       row.remove(); 
                   }
                   updateTotalPrice(); 
                   updateCartCount(); 
               } else {
                   console.error(data.message);
               }
           })
           .catch(error => console.error('Error removing product:', error));
       }

	   
       function updateTotalPrice() {
           let total = 0;
           document.querySelectorAll(".product-price").forEach(priceElem => {
               total += parseFloat(priceElem.textContent);
           });
           document.getElementById("total-price").textContent = total.toFixed(2); 
       }

	   
       function updateCartCount() {
           fetch('/cart/count')
               .then(response => response.json())
               .then(data => {
                   const cartCountElement = document.getElementById('cart-count');
                   if (cartCountElement) {
                       cartCountElement.innerText = data.count; 
                   }
               })
               .catch(error => console.error('Error fetching cart count:', error));
       }