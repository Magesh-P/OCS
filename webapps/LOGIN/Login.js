/**
 * log in form process
 */

function createAcc() {

    console.log("hai i am work ...");
 	var name = document.getElementById("userName");
 	var password = document.getElementById("password");
 	
 	/*
 	 * First check name then check password.
 	 * After finish the password check then check username already exists
 	 * 
 	 */
 		
 	if(nameCheck(name.value))
 	{
 		if(passWordCheck(password.value))
 		{
 			 return true;
 		}
 		
 		return false;
 		
 	}
 	else
 	{
 		return false;
 	}
 	
 }
/**

	This function check names 
	Names are present correct formate
	Name have length minimu 6 maximum 10 char
	Name have two numeric value
*/
 function nameCheck(name)
 {
 	var pattern = /^[A-Za-z][A-Za-z0-9]+$/;

 	if(name.length > 6 )
 	{
 		
 		if(name.match(pattern))
 		{
 			
 			return true;
 		}
 		else
 		{
 			document.getElementById('nameError').textContent = "UserName only alpha numeric";
 			return false;
 		}
 	}
 	document.getElementById('nameError').textContent = "userName have atleast 7 chaeacter";
 	return false;
 }

 /*
  * This method check the password
  * The password have minimum 9 chaeacter
  */
 function passWordCheck(password)
 {
 	if(password.length > 6 )
 	{
 		return true;
 	}
 	else
 	{
 	 	document.getElementById('passError').textContent = "password have atleast 7 chaeacter";
 		return false;
 	}
 }