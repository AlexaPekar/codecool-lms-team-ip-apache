function validateRegisterForm() {
    var name = document.getElementById("name").value;
    if (name == null || name == "") {
        document.getElementById("message-field").innerText = "Enter a name!";
        document.getElementById("password").value = "";
        document.getElementById("password-again").value = "";
        return false;
    }
    var email = document.getElementById("email").value;
    if (email == null || email == "") {
        document.getElementById("message-field").innerText = "Enter an e-mail adress!";
        document.getElementById("password").value = "";
        document.getElementById("password-again").value = "";
        return false;
    }
    var password = document.getElementById("password").value;
    if (password == null || password == "") {
        document.getElementById("message-field").innerText = "Enter a password!";
        document.getElementById("password-again").value = "";
        return false;
    }
    if (password.length < 9) {
        document.getElementById("message-field").innerText = "Password must be longer than 8 character!";
        return false;
    }
    var passwordAgain = document.getElementById("password-again").value;
    if (passwordAgain == null || passwordAgain == "") {
        document.getElementById("message-field").innerText = "Please enter the password again!";
        document.getElementById("password").value = "";
        return false;
    }
    if (password !== passwordAgain) {
        document.getElementById("message-field").innerText = "The two password must match!";
        return false;
    }
    return true;
}