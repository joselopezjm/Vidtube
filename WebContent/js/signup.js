var submit = document.getElementById('button-submit');
var username = document.getElementById('signup-username');
var nombre = document.getElementById('signup-name');
var lastname = document.getElementById('signup-lastname');
var password = document.getElementById('signup-password');
var email = document.getElementById('signup-email');
var birth = document.getElementById('signup-birthday');
var pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
var $http = new XHR();
var x = null;
submit.onclick = function onPost() {
        $http.get("./signup", {
            username: username.value
        }, function (err, data) {
            if (err) throw err;
            x = data;
            check();
        });

        function check() {

            if (username.value == x.p[0].username) {
                document.getElementById("signup-username-icon").style.display = "inline-block";
            } else {
                if (pattern.test(email.value)) {
                    if (password.value != '' && nombre.value != '' && lastname.value != '' && birth.value != '') {
                        
                        $http.post("./signup", {
                            username: username.value,
                            password: password.value,
                            nombre: nombre.value.toLowerCase(),
                            lastname: lastname.value.toLowerCase(),
                            email: email.value,
                            birth: birth.value
                        }, function (err, data) {
                            if (err) throw err;
                            console.log(data);
                        });
                      restart();
                    } else {
                       console.log(password);
                        if (password.value == '') {
                            document.getElementById("signup-password-icon").style.display = "inline-block";
                        }
                        if (nombre.value == '') {
                            document.getElementById("signup-name-icon").style.display = "inline-block";
                        }
                        if (lastname.value == '') {
                            document.getElementById("signup-lastname-icon").style.display = "inline-block";
                        }
                        if (birth.value == '') {
                            document.getElementById("signup-birthday-icon").style.display = "inline-block";
                        }
                    }
                } else {
                    document.getElementById("signup-email-icon").style.display = "inline-block";

                }
            }
          
           
            function restart() {
                 document.getElementById('form-signup').style.display = 'none';
                document.getElementById("signup-username-icon").style.display = "none";

                document.getElementById("signup-email-icon").style.display = "none";

                document.getElementById("signup-birthday-icon").style.display = "none";

                document.getElementById("signup-lastname-icon").style.display = "none";

                document.getElementById("signup-name-icon").style.display = "none";

                document.getElementById("signup-password-icon").style.display = "none";
                username.value="";
                password.value="";
                email.value="";
                nombre.value="";
                lastname.value="";
                birth.value="";
            }
        }
}