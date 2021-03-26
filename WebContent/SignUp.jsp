<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Login Page</title>
<link rel="stylesheet" href="css/admin_login.css">
<script src="js/confirmPassword.js"></script>
</head>
<body>
	<div class="main">
		<form class="box" action="SignUpServe" method="post" >
			<h1>Create Account</h1>
			<input type="text" name="name" placeholder="Name" required> 
			<input type="text" name="phno" placeholder="Phone number" required> 
			<input type="text" name="gmail" placeholder="gmail" required> 
			<input type="password" name="pass" id="password" placeholder="password" required>
			<span id = "message" style="color:red"> </span> 
			<input type="password" name="" id="confirm_password" placeholder="Confirm  password" required> 
			
			<input type="submit" value="Register" onclick="return verifyPassword()">
		</form>
	</div>
</body>
</html>