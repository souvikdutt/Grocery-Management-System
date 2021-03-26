function verifyPassword() {  
	  var pw = document.getElementById("password").value; 
	  var cpw = document.getElementById("confirm_password").value; 
	    
	 //minimum password length validation  
	  if(pw.length < 4) {  
	     document.getElementById("message").innerHTML = "**Password length must be atleast 4 characters";  
	     return false;  
	  }  
	  
	//maximum length of password validation  
	  if(pw.length > 10) {  
	     document.getElementById("message").innerHTML = "**Password length must not exceed 10 characters";  
	     return false;  
	  } 
	
	//check empty password field  
	  if(pw != cpw) {  
	     document.getElementById("message").innerHTML = "**Confirm Password isn't matched!";  
	     return false;  
	  } 
}  