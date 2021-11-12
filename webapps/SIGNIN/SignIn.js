/**
 * Sign IN from process
 */

function createAcc() {

 	var name = document.getElementById("unIbox");
 	var password = document.getElementById("pass");
 	var mail = document.getElementById("mailBox");
 	var phone = document.getElementById("phoneNum");
 	var OTP = document.getElementById("otpNum");
 	
 	
 	/*
 	 * First check name then check password.
 	 * After finish the password check then check username already exists
 	 * 
 	 */
 		
 	if(nameCheck(name.value))
 	{
 		if(passWordCheck(password.value) )
 			{1
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
 	
 	if(name.length > 6)
 	{
 		
 		if(name.match(pattern))
 		{
 			
 			return true;
 		}
 		else
 		{
 			document.getElementById('nameErrorMsg').textContent = "UserName only alpha numeric";
 			return false;
 		}
 	}
 	document.getElementById('nameErrorMsg').textContent = "user name have atleast 7 chaeacter";
 	return false;
 }

 /*
  * This method check the password
  * The password have minimum 9 character
  */
 function passWordCheck(password)
 {
 	if(password.length > 6)
 	{
 		return true;
 	}
 	else
 	{
 	 	document.getElementById('passErrorMsg').textContent = "user password have atleast 7 chaeacter";
 		return false;
 	}
 }