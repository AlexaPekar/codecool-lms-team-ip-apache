
function validateLoginForm() {
    var email = document.getElementById("email").value;
    if (email == null || email == "") {
        document.getElementById("message-field").innerText = "Enter an e-mail adress!";
        document.getElementById("password").value = "";
        return false;
    }
    var password = document.getElementById("password").value;
    if (password == null || password == "") {
        document.getElementById("message-field").innerText = "Enter a password!";
        return false;
    }
    return true;
}
