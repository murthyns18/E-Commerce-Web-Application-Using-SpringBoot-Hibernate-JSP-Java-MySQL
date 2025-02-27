    document.addEventListener('DOMContentLoaded', function () {
        updateCartCount();

        document.querySelectorAll('.add-to-cart-btn').forEach(button => {
            button.addEventListener('click', function () {
                const productId = this.getAttribute('data-product-id');
                console.log("Product ID:", productId);

                if (!productId) {
                    console.error("Product ID is missing from the button");
                    return;
                }
                addToCart(productId); 
            });
        });

        const searchInput = document.getElementById('searchInput');
        if (searchInput) {
            searchInput.addEventListener('input', function () {
                const searchQuery = searchInput.value.toLowerCase();
                const productCards = document.querySelectorAll('.product-card');

                productCards.forEach(card => {
                    const productName = card.getAttribute('data-name');
                    if (productName.includes(searchQuery)) {
                        card.style.display = 'block';
                    } else {
                        card.style.display = 'none';
                    }
                });
            });
        }
    });

    function addToCart(productId) {
        fetch('/cart/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ productId: parseInt(productId) }), 
        })
        .then(response => response.json())
        .then(data => {
            if (data.status === "success" || data.status === "info") {
                updateCartCount(); 
                displayCartMessage(data.message);
            } else {
                console.error(data.message); 
            }
        })
        .catch(error => console.error('Error adding product to cart:', error));
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

    function displayCartMessage(message) {
        const cartMessage = document.getElementById('cart-message');
        if (cartMessage) {
            cartMessage.innerText = message; 
            cartMessage.style.display = 'block'; 
            cartMessage.style.opacity = '1'; 

            setTimeout(() => {
                cartMessage.style.opacity = '0';
                setTimeout(() => {
                    cartMessage.style.display = 'none'; 
                }, 300);
            }, 3000);
        }
    }
  document.getElementById('contactForm').addEventListener('submit', function(event) {
    event.preventDefault(); 
    const form = event.target;
    const formData = new FormData(form);

    fetch(form.action, {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            document.getElementById('formMessage').innerText = data.success;
            document.getElementById('formMessage').style.display = 'block';

            form.reset();

            setTimeout(() => {
                document.getElementById('formMessage').style.display = 'none';
            }, 5000);
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
});


document.querySelectorAll('.apply-discount-btn').forEach(button => {
    let discountApplied = false;  
    button.addEventListener('click', () => {
        const discountPercentage = parseFloat(button.getAttribute('data-discount-percentage'));
        const isAvailable = button.getAttribute('data-discount-available') === 'true';
        const discountSection = document.querySelector('.discount-section');
        let messageElement = document.getElementById('discount-message');

        if (!messageElement) {
            messageElement = document.createElement('p');
            messageElement.id = 'discount-message';
            discountSection.appendChild(messageElement);
        }

        messageElement.style.textAlign = "center";
        messageElement.style.fontWeight = "bold";
        messageElement.style.fontSize = "18px";

        if (!isAvailable) {
            messageElement.textContent = "Offer expired.";
            messageElement.style.color = "red";
            return;
        }

        if (discountApplied) {
            messageElement.textContent = "Discount already applied. Please check.";
            messageElement.style.color = "green"; 
            return;
        }

        document.querySelectorAll('.product-card').forEach(productCard => {
            const originalPriceElement = productCard.querySelector('.original-price');
            const originalPrice = parseFloat(originalPriceElement.textContent.replace(/[^0-9.-]+/g, ""));
            const discountedPrice = originalPrice * (1 - discountPercentage / 100);

            originalPriceElement.innerHTML = `
                <span class="original-price strikethrough">₹${originalPrice.toLocaleString()}</span>
                <span class="discount-percentage" style="color: blue; font-weight: bold;">(${discountPercentage}% off)</span>
                <span class="final-price" style="color: green; font-weight: bold;">₹${discountedPrice.toLocaleString()}</span>
            `;
        });

        messageElement.textContent = "Discount applied successfully!";
        messageElement.style.color = "green";  
        discountApplied = true;
    });
});

     setTimeout(function() {
         document.getElementById("welcome-msg").style.display = "none";
     }, 3000);

     document.getElementById("discount-link").addEventListener("click", function(event) {
         event.preventDefault(); 
         const discountSection = document.getElementById("discount-section");
         if (discountSection.style.display === "none" || discountSection.style.display === "") {
             discountSection.style.display = "block";
         } else {
             discountSection.style.display = "none";
         }
     });