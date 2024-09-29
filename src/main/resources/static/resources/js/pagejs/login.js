function check() {
    if ("" == $("#userAccount").val()) {
        document.getElementById("userAccount").focus();
        alert("Pls input user！");
        return false;
    }

    if ("" == $("#userPasswd").val()) {
        document.getElementById("userPasswd").focus();
        alert("Pls input passwd！");
        return false;
    }

    loginForm.submit();
}

function login() {
    var event = getEvent();
    event.cancelBubble = true;
    if (event.keyCode == 13) check();
}

function resetForm() {
    $("#userAccount").val("");
    $("#userPasswd").val("");
}

function toPswd() {
    var event = getEvent();
    if (event.keyCode == 13) {
        document.getElementById("userPasswd").focus();
    }
}