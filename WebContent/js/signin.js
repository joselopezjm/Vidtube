var submit2 = document.getElementById('button-submit2');
var username2 = document.getElementById('login-username');
var password2 = document.getElementById('login-password');
var $http = new XHR();
var x = null;

window.onload = function () {
    $http.get("./login", {}, function (err, data) {
        if (err) throw err;
        console.log(data);
        check(data);
    });
}

submit2.onclick = function onPost() {
    $http.post("./login", {
        username: username2.value,
        password: password2.value
    }, function (err, data) {
        if (err) throw err;
        check(data);
        
    });
}




function check(data) {
    console.log(data);
    if (parseInt(data.p[0].result) == 1) {
        restart();
        document.getElementById('form-login').style.display = "none";
        logout.style.display = "block";
        login.style.display = "none";
        signup.style.display = 'none';
        upload.style.display = 'block';
        document.getElementById('name-profile').innerHTML = firstToUpperCase(data.p[0].name);
        document.getElementById('name-profile-box').innerHTML= firstToUpperCase(data.p[0].name);
        document.getElementById("email-profile-box").innerHTML = firstToUpperCase(data.p[0].email);
        document.getElementById('img-profile').style.display = "block";
        console.log(document.getElementById('img-logerror'));
        document.getElementById('img-logerror').style.display = "none";
        document.getElementById('error-login').style.display = 'none';
        main_menu(1);
        document.getElementById("img-logerror-button").style.display = "none";
    }
    if (parseInt(data.p[0].result) == 0) {
        document.getElementById('error-login').style.display = 'block';
    }

    if (parseInt(data.p[0].result) == 2) {
        document.getElementById('img-logerror').style.display = "block";
    }
}



function restart() {
    document.getElementById("login-username-icon").style.display = "none";
    document.getElementById("login-password-icon").style.display = "none";
    username2.value = "";
    password2.value = "";


}