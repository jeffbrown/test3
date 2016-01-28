# testapp

curl --verbose --request POST --data "j_username=admin&j_password=Al1c3Inj@1L&_spring_security_remember_me=checked" http://localhost:8080/auth/j_spring_security_check --cookie-jar cookies.txt

// GET TEST
curl --verbose --request GET --header "Content-Type: application/json" "http://localhost:8080/v0.1/post/show/1" --cookie cookies.txt

Application should loop THREE TIMES 
