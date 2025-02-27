function validatePasswords() {
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const errorMessage = document.getElementById("passwordError");

    if (password !== confirmPassword) {
        errorMessage.style.display = "block";  
        return false; 
    } else {
        errorMessage.style.display = "none";  
        return true; 
    }
}