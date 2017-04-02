var submit3 = document.getElementById('button-submit3');
var name2 = document.getElementById('upload-name');
var category = document.getElementById('upload-category');
var description = document.getElementById('upload-description');
var video = document.getElementById('upload-file');



var xhr = new XMLHttpRequest();
submit3.onclick = function onPost() {
    if (name2.value != "" && category.value != "" && description.value != "" && video.value != "") {
        xhr.onreadystatechange = function () {
            if (xhr.status === 200 && xhr.readyState === 4) {
                console.log(xhr.responseText);
                var resp = xhr.responseText;
                if (parseInt(resp) == 1) {
                    alert("Video was uploaded successfully!")
                    restart();
                } else {
                    alert("Error on video upload!")
                }
            }
        }
        var formData = new FormData();
        formData.append("file", video.files[0]);
        formData.append("name", name2.value.toLowerCase());
        formData.append("category", category.value);
        formData.append("description", description.value);
        xhr.open("post", "./upload", true);
        xhr.send(formData);
    } else {
        document.getElementById('error-upload').style.display = 'block';
    }
}



function restart() {
    name2.value="";
    category.value="";
    description.value="";
    video.value="";
}