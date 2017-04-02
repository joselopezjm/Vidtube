var searchbutton1 = document.getElementById('button-search');
console.log(searchbutton1);
var searchinput1 = document.getElementById('input-search');
searchbutton1.onclick = function onGet() {
    $http.get("./search", {
        search: searchinput1.value
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
                        SetStream(data.r[0].videos[id1]);
                    });

                }

                //                function SetStream(video) {
                //                    console.log(video);
                //                    var uc_str = firstToUpperCase(video.name_media);
                //                    document.getElementById('name-video').innerHTML = uc_str;
                //                    uc_str = firstToUpperCase(video.username);
                //                    document.getElementById('name1-user').innerHTML = uc_str;
                //                    document.getElementById("views-video").innerHTML = video.views_media + " views";
                //                    document.getElementById("date-video").innerHTML = "Published on " + video.created_at;
                //
                //                    document.getElementById("desc-video").innerHTML = video.description_media;
                //                    document.getElementById("home-videos").style.display = "none";
                //                    document.getElementById("video-player").style.display = "block";
                //                    var play = document.getElementById('video');
                //                    play.src = "./video?id=" + video.url_media +"&id2="+video.id_media;
                //
                //                    //                $http.post("./video", {
                //                    //                    url: video.url_media,
                //                    //                }, function (err, data) {
                //                    //                    if (err) throw err;
                //                    //                    console.log(data);
                //                    //                    var play = document.createElement('video');
                //                    //                    play.setAttribute('id', 'video');
                //                    //                    play.setAttribute("src", './video')
                //                    //                    play.controls = true;
                //                    //                    document.getElementById("video-player").appendChild(play);
                //                    //                });
                //
                //
                //
                //                }

                function firstToUpperCase(str) {
                    return str.substr(0, 1).toUpperCase() + str.substr(1);
                }
            }
        }
        if (data.r[1].response == "2") {
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
            }
            alert("No results for your search!");
        }
        searchinput1.value ="";
    });
}