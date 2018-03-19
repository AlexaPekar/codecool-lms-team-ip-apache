function validateLoginForm() {
    var email = document.getElementById("email").value;
    if (email == null || email == "") {
        document.getElementById("message-field").innerText="Enter an e-mail adress!";
        return false;
    }
    var password = document.getElementById("password").value;
    if (password == null || password == "") {
        document.getElementById("message-field").innerText="Enter a password!";
        return false;
    }
    else if (password.length<9){
        document.getElementById("message-field").innerText="Password must be longer than 8 character!";
        return false;
    }
    return true;
}