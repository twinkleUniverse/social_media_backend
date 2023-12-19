User APIs
1)Register user:- To create or register a new user hit the following Api it will create the user with unique user id,username,emailid.
New User Creation Api =>localhost:8080/User/Register
Input formate => RequestBody=> {"username":"--", "emailId":"--",
      "password":"--",
      "bio":"--";
      "profile":"Url_image"}

2)Update profile picture Api=>localhost:8080/User/update_profile?username="--"&profile="--"
3)Update name Api=>localhost:8080/User/update_name?username="--"
4)Update Bio Api=>localhost:8080/User/update_bio?username="--"&Bio="--"
5)Update password =>localhost:8080/User/update_password?username="--"&password="--"
6)Get the user with username => localhost:8080/User/get_User?username="--"

Post Apis
1) create post=> localhost:8080/Post/post_content    
RequesBody=>{
     "username":"--",
   "content":"--"
    "caption":"--",
    "privacySetting": "--";
}
