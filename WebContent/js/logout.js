var logout = document.getElementById('button-logout');
var $http = new XHR();
logout.onclick = function onGet() {
    $http.get("./logout", {}, function (err, data) {
        if (err) throw err;
        if (parseInt(data) == 2) {
            logout.style.display = "none";
            login.style.display = "block";
            signup.style.display = 'block';
            upload.style.display = 'none';
            document.getElementById('name-profile').innerHTML = "";
            document.getElementById('img-profile').style.display = 'none';
            document.getElementById('img-logerror').style.display = "block";
            document.getElementById("home-videos").style.display = "none";
            document.getElementById("video-player").style.display = "none";
            document.getElementById("video").src = "";
            document.getElementById("menu-profile").style.display="none";
            document.getElementById("img-logerror-button").style.display="block";
        }


    });
};