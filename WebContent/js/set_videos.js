document.getElementById("menu-home").onclick = function onGet() {
    main_menu(1);
}

document.getElementById("logo-vidtube").onclick = function onGet() {
    main_menu(1);
}


document.getElementById("menu-trending").onclick = function onGet() {
    main_menu(2);
}

document.getElementById("menu-music").onclick = function onGet() {
    main_menu(3);
}

document.getElementById("menu-sports").onclick = function onGet() {
    main_menu(4);
}
document.getElementById("menu-games").onclick = function onGet() {
    main_menu(5);
}
document.getElementById("menu-news").onclick = function onGet() {
    main_menu(6);
}
document.getElementById("menu-movies").onclick = function onGet() {
    main_menu(7);
}

function main(set) {
    alert(set);
}

function main_menu(set) {
    $http.get("./set_videos", {
        type: set,
    }, function (err, data) {
        if (err) throw err;
        console.log(data);
        if (data.r[1].response == "0") {
            alert("Sign in first");
        }
        if (data.r[1].response == "1") {
            if (document.contains(document.getElementById("home-videos"))) {
                if (document.getElementById('video').src !== "") {
                    document.getElementById('video').src = "";
                }
                document.getElementById("home-videos").style.display = "block";
                document.getElementById("video-player").style.display = "none";
                document.getElementById("home-videos").remove();
                var newhome = document.createElement('div');
                newhome.setAttribute('id', 'home-videos')
                document.getElementById('home-content').appendChild(newhome);
                newhome.className = "row";
                var newbox = document.createElement('div');
                newhome.appendChild(newbox)
                newbox.style.marginLeft = '60px';
                newbox.style.marginRight = "60px";
                newbox.style.marginTop = "30px";
                for (var i = 0; i < data.r[0].videos.length; i++) {
                    var thumbnails = document.createElement('div');
                    newbox.appendChild(thumbnails)
                    thumbnails.className = 'col-sm-6 col-md-3';
                    var newdiv = document.createElement('div');
                    newdiv.className = 'thumbnail';
                    thumbnails.appendChild(newdiv);
                    var newimg = document.createElement('img');
                    newimg.src = 'Resources/thumbnail.jpg';
                    newimg.setAttribute('id', i);
                    newdiv.appendChild(newimg);
                    newdiv.style.cursor = "pointer";
                    var temp = data.r[0].videos[i];
                    var newlink = document.createElement('div');
                    newdiv.appendChild(newlink);
                    var newusername = document.createElement('div');
                    newdiv.appendChild(newusername);
                    var newviews = document.createElement('div');
                    newdiv.appendChild(newviews);
                    var newdate = document.createElement('div');
                    newdiv.appendChild(newdate);
                    newusername.innerHTML = data.r[0].videos[i].username;
                    newviews.innerHTML = data.r[0].videos[i].views_media + ' views';
                    newdate.innerHTML = 'Uploaded: ' + data.r[0].videos[i].created_at;
                    newlink.setAttribute('id', i);
                    newlink.innerHTML = data.r[0].videos[i].name_media;
                    newlink.onclick = function () {
                        var id1 = this.getAttribute('id');
                        SetStream(data.r[0].videos[id1]);
                    }
                    newlink.style.color = '#0174DF';
                    newlink.onmouseover = function sub() {
                        this.style.textDecoration = 'underline';
                    }
                    newlink.onmouseout = function sub() {
                        this.style.textDecoration = 'none';
                    }
                    newimg.addEventListener("click", function () {
                        var id1 = this.getAttribute('id');
                        modal3.style.display = "block";
                    });

                }



            }
        }
    });
}

function SetStream(video) {
    console.log(video);
    var uc_str = firstToUpperCase(video.name_media);
    document.getElementById('name-video').innerHTML = uc_str;
    uc_str = firstToUpperCase(video.username);
    document.getElementById('name1-user').innerHTML = uc_str;
    document.getElementById("views-video").innerHTML = video.views_media + " views";
    document.getElementById("date-video").innerHTML = "Published on " + video.created_at;
    

    document.getElementById("desc-video").innerHTML = video.description_media;
    document.getElementById("home-videos").style.display = "none";
    document.getElementById("video-player").style.display = "block";
    var play = document.getElementById('video');
    play.src = "./video?id=" + video.url_media + "&id2=" + video.id_media;

    //                $http.post("./video", {
    //                    url: video.url_media,
    //                }, function (err, data) {
    //                    if (err) throw err;
    //                    console.log(data);
    //                    var play = document.createElement('video');
    //                    play.setAttribute('id', 'video');
    //                    play.setAttribute("src", './video')
    //                    play.controls = true;
    //                    document.getElementById("video-player").appendChild(play);
    //                });

    $http.get("./views", {
        id: video.id_media
    }, function (err, data) {
        if (err) throw err;
        console.log(data);
    });
    
    




    //recomendations
    function recomendation() {
        console.log("recom");
        $http.get("./recom", {
            cat: video.category_media
        }, function (err, data) {
            if (err) throw err;
            console.log(data);
            if (data.r[1].response == "0") {
                alert("Sign in first");
            }
            if (data.r[1].response == "1") {
                if (document.contains(document.getElementById("recom-panel"))) {

                    document.getElementById("recom-panel").remove();
                    var newhome = document.createElement('div');
                    newhome.setAttribute('id', 'recom-panel')
                    document.getElementById('video-player').appendChild(newhome);
                    newhome.className = "row";
                    var newbox = document.createElement('div');
                    newhome.appendChild(newbox)

                    for (var i = 0; i < data.r[0].videos.length; i++) {
                        var thumbnails = document.createElement('div');
                        thumbnails.style.width = "100%";
                        newbox.style.marginTop = "10px";
                        newbox.style.marginLeft = "0px";
                        newbox.appendChild(thumbnails)
                        thumbnails.className = 'col-sm-2 col-md-2';
                        var newdiv = document.createElement('div');
                        newdiv.className = 'thumbnail';
                        thumbnails.appendChild(newdiv);
                        var newimg = document.createElement('img');
                        newimg.setAttribute("src", 'Resources/thumbnail.jpg');
                        newimg.setAttribute('id', i);
                        newdiv.appendChild(newimg);
                        newdiv.style.cursor = "pointer";
                        var temp = data.r[0].videos[i];
                        var newlink = document.createElement('div');
                        newdiv.appendChild(newlink);
                        var newusername = document.createElement('div');
                        newdiv.appendChild(newusername);
                        var newviews = document.createElement('div');
                        newdiv.appendChild(newviews);
                        var newdate = document.createElement('div');
                        newdiv.appendChild(newdate);
                        newusername.innerHTML = data.r[0].videos[i].username;
                        newviews.innerHTML = data.r[0].videos[i].views_media + ' views';
                        newdate.innerHTML = 'Uploaded: ' + data.r[0].videos[i].created_at;
                        newlink.setAttribute('id', i);
                        newlink.innerHTML = data.r[0].videos[i].name_media;
                        newlink.onclick = function () {
                            var id1 = this.getAttribute('id');
                            SetStream(data.r[0].videos[id1]);
                        }
                        newlink.style.color = '#0174DF';
                        newlink.onmouseover = function sub() {
                            this.style.textDecoration = 'underline';
                        }
                        newlink.onmouseout = function sub() {
                            this.style.textDecoration = 'none';
                        }
                        newimg.addEventListener("click", function () {
                            var id1 = this.getAttribute('id');
                            SetStream(data.r[0].videos[id1]);
                        });

                    }



                }

                checklikes();
            }

        });
    }
    recomendation();



    function checklikes() {

        console.log("likes");
        var like = document.getElementById('img-likevideo');
        $http.get("./likes", {
            func: "0",
            id: video.id_media,
        }, function (err, data) {
            if (err) throw err;
            console.log(data);
            checkcomments();
            document.getElementById('like-count').innerHTML = data.count[0].count;
            if (parseInt(data.response) == 2) {
                like.src = "Resources/like.png";
                like.onclick = function () {
                    like.src = "Resources/like2.png";
                    $http.get("./likes", {
                        func: "1",
                        id: video.id_media,
                    }, function (err, data) {
                        checklikes();
                    });
                }
            }
            if (parseInt(data.response) == 1) {

                like.src = "Resources/like2.png";
                like.onclick = function () {
                    $http.get("./likes", {
                        func: "2",
                        id: video.id_media,
                    }, function (err, data) {
                        checklikes();
                    });
                    like.src = "Resources/like.png";
                }
            }
        });

    }
    post.onclick = function () {
        if (document.getElementById("input-comment").value != "") {
            $http.post("./comments", {
                id: video.id_media,
                data: document.getElementById("input-comment").value
            }, function (err, data) {
                if (err) throw err;
                console.log(data);
                document.getElementById("input-comment").value = "";
                post.style.backgroundColor = "#58ACFA";
                post.disabled = true;
                post.style.cursor = "arrow";
                post.onmouseover = function changecolor() {};
                post.onmouseout = function changecolor() {};
                post.onclick = function () {};
                checkcomments();
            });
        }
    }





    function checkcomments() {
        $http.get("./comments", {
            id: video.id_media,
        }, function (err, data1) {
            if (err) throw err;
            console.log(data1);
            if (data1.response1 == "2") {
                console.log("no comments")
                document.getElementById("title-comments").innerHTML = "Comments • " + 0;
            } else {

                if (document.contains(document.getElementById("comment-box2"))) {
                    document.getElementById("comment-box2").remove();
                    var newcbox = document.createElement('div');
                    newcbox.setAttribute('id', 'comment-box2')
                    document.getElementById('comments-video').appendChild(newcbox);
                }
                var cbox = document.getElementById("comment-box2");
                for (var k = 0; k < data1.e.length; k++) {
                    var commentdiv = document.createElement('div');
                    commentdiv.className = "comment-div";
                    cbox.appendChild(commentdiv);
                    var cimg = document.createElement('img');
                    cimg.src = "Resources/profile.png";
                    cimg.className = "comment-img";
                    commentdiv.appendChild(cimg);
                    var cname = document.createElement('div');
                    cname.className = "comment-name";
                    commentdiv.appendChild(cname);
                    var cdate = document.createElement('div');
                    cdate.className = "comment-date";
                    commentdiv.appendChild(cdate);
                    var ccont = document.createElement('div');
                    ccont.className = "comment-content";
                    commentdiv.appendChild(ccont);
                    cname.innerHTML = data1.e[k].username;
                    cdate.innerHTML = data1.e[k].created_at;
                    ccont.innerHTML = data1.e[k].content;
                }
                document.getElementById("title-comments").innerHTML = "Comments • " + data1.e.length;
            }
        });
    }
}


function firstToUpperCase(str) {
    return str.substr(0, 1).toUpperCase() + str.substr(1);
}