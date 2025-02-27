setTimeout(function() {
          var message = document.getElementById("successMessage");
          if (message) {
              message.style.opacity = "0";  
              setTimeout(function() {
                  message.style.display = "none";  
              }, 500); 
          }
      }, 3000);