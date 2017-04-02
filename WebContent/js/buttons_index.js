var search = document.getElementById('button-search');
search.onmouseover = function changecolor() {
    search.style.backgroundColor = "#E6E6E6";
    search.style.cursor = "pointer";
};
search.onmouseout = function changecolor() {
    search.style.backgroundColor = "#F2F2F2";
};

var menu = document.getElementById('img-menu');
var dropmenu = document.getElementById('dropmenu');
menu.onmouseover = function changecolor() {
    document.getElementById('icon-menu').setAttribute('src', 'Resources/menu_2.png');
    menu.style.cursor = "pointer";
};
menu.onmouseout = function changecolor() {
    document.getElementById('icon-menu').setAttribute('src', 'Resources/menu_1.png');
};
menu.onclick = function open() {
    style = window.getComputedStyle(dropmenu).getPropertyValue('display');
    if (style == 'none') {
        dropmenu.style.display = 'block';
        document.getElementById('home-box').style.left = 'calc(18% - 14px)';
        document.getElementById('home-box').style.width = '84%';
        document.getElementById('upper-bar').style.left = '17%';
        document.getElementById('upper-bar').style.width = '81%';
    }
    if (style == 'block') {
        dropmenu.style.display = 'none';
        document.getElementById('home-box').style.left = '0%';
        document.getElementById('home-box').style.width = '100%';
        document.getElementById('upper-bar').style.left = '0%';
        document.getElementById('upper-bar').style.width = '100%';
        document.getElementById('home-content').style.left = '5%';
    }
}

var login = document.getElementById('button-login');
login.onmouseover = function changecolor() {
    login.style.backgroundColor = "#045FB4";
    login.style.cursor = "pointer";
};

login.onmouseout = function changecolor() {
    login.style.backgroundColor = "#0174DF"
};

var logout = document.getElementById('button-logout');
logout.onmouseover = function changecolor() {
    logout.style.backgroundColor = "#045FB4";
    logout.style.cursor = "pointer";
};

logout.onmouseout = function changecolor() {
    logout.style.backgroundColor = "#0174DF"
};

var signup = document.getElementById('button-signup');


signup.onmouseover = function changecolor() {
    signup.style.backgroundColor = "#045FB4";
    signup.style.cursor = "pointer";
};

signup.onmouseout = function changecolor() {
    signup.style.backgroundColor = "#0174DF"
};

var upload = document.getElementById('button-upload');

upload.onmouseover = function changecolor() {
    upload.style.backgroundColor = "#E6E6E6";
    upload.style.cursor = "pointer";
};

upload.onmouseout = function changecolor() {
    upload.style.backgroundColor = "#F2F2F2"
};

var submit = document.getElementById('button-submit');

submit.onmouseover = function changecolor1() {
    document.getElementById('button-submit').style.backgroundColor = "#045FB4";
    document.getElementById('button-submit').style.cursor = "pointer";
    console.log(submit);
};

submit.onmouseout = function changecolor1() {
    document.getElementById('button-submit').style.backgroundColor = "#0174DF"
};

var submit2 = document.getElementById('button-submit2');

submit2.onmouseover = function changecolor() {
    submit2.style.backgroundColor = "#045FB4";
    submit2.style.cursor = "pointer";
};

submit2.onmouseout = function changecolor() {
    submit2.style.backgroundColor = "#0174DF"
};

var trending = document.getElementById('menu-trending');

trending.onmouseover = function changecolor() {
    trending.style.backgroundColor = "#585858"
    trending.style.color = 'white';
    trending.style.cursor = "pointer";
};
trending.onmouseout = function changecolor() {
    trending.style.backgroundColor = "white"
    trending.style.color = '#585858';
};



var music = document.getElementById('menu-music');

music.onmouseover = function changecolor() {
    music.style.backgroundColor = "#585858"
    music.style.color = 'white';
    music.style.cursor = "pointer";
};

music.onmouseout = function changecolor() {
    music.style.backgroundColor = "white"
    music.style.color = '#585858';
};


var sports = document.getElementById('menu-sports');

sports.onmouseover = function changecolor() {
    sports.style.backgroundColor = "#585858"
    sports.style.color = 'white';
    sports.style.cursor = "pointer";
};

sports.onmouseout = function changecolor() {
    sports.style.backgroundColor = "white"
    sports.style.color = '#585858';
};

var games = document.getElementById('menu-games');

games.onmouseover = function changecolor() {
    games.style.backgroundColor = "#585858"
    games.style.color = 'white';
    games.style.cursor = "pointer";
};

games.onmouseout = function changecolor() {
    games.style.backgroundColor = "white"
    games.style.color = '#585858';
};

var news = document.getElementById('menu-news');

news.onmouseover = function changecolor() {
    news.style.backgroundColor = "#585858"
    news.style.color = 'white';
    news.style.cursor = "pointer";
};

news.onmouseout = function changecolor() {
    news.style.backgroundColor = "white"
    news.style.color = '#585858';
};

var movies = document.getElementById('menu-movies');

movies.onmouseover = function changecolor() {
    movies.style.backgroundColor = "#585858"
    movies.style.color = 'white';
    movies.style.cursor = "pointer";
};

movies.onmouseout = function changecolor() {
    movies.style.backgroundColor = "white"
    movies.style.color = '#585858';
};

var modal = document.getElementById("form-signup");
var span = document.getElementsByClassName("close")[0];


signup.onclick = function () {
    modal.style.display = "block";
}
span.onclick = function () {
    modal.style.display = "none";
}


window.onclick = function (event) {

    if (event.target == modal) {
        modal.style.display = "none";
    }
    if (event.target == modal2) {
        modal2.style.display = "none";
        document.getElementById("img-logerror-button").style.display = "block";
    }
    if (event.target == modal3) {
        modal3.style.display = "none";
        name2.value = "";
        category.value = "";
        description.value = "";
        video.value = "";
    }
    if (event.target == modal4) {
        modal4.style.display = "none";
    }
}

var modal2 = document.getElementById("form-login");

var span2 = document.getElementsByClassName("close")[1];

login.onclick = function () {
    modal2.style.display = "block";
}

span2.onclick = function () {
    modal2.style.display = "none";
    document.getElementById("img-logerror-button").style.display = "block";
}

document.getElementById("img-logerror-button").onclick = function () {
    modal2.style.display = "block";
    document.getElementById("img-logerror-button").style.display = "none";
}



var modal3 = document.getElementById("form-upload");
var span3 = document.getElementsByClassName("close")[2];

upload.onclick = function () {
    modal3.style.display = "block";
}

span3.onclick = function () {
    name2.value = "";
    category.value = "";
    description.value = "";
    video.value = "";
    modal3.style.display = "none";
}

var modal4 = document.getElementById("menu-profile");
var profile1 = document.getElementById("img-profile");
profile1.onclick = function () {
    modal4.style.display = "block";
}

var post = document.getElementById('button-post');

document.getElementById("input-comment").oninput = function () {
    if (document.getElementById("input-comment").value == "") {
        post.style.backgroundColor = "#58ACFA";
        post.disabled = true;
        post.style.cursor = "arrow";
        post.onmouseover = function changecolor() {};
        post.onmouseout = function changecolor() {};
        post.onclick = function () {};
    } else {
        post.disabled = false;
        post.style.backgroundColor = "#0174DF"
        post.onmouseover = function changecolor() {
            post.style.backgroundColor = "#045FB4";
            post.style.cursor = "pointer";
        };

        post.onmouseout = function changecolor() {
            post.style.backgroundColor = "#0174DF"
        };
    }
}