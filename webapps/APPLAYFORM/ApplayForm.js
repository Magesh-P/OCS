var strCheckVal = ["userName", "national", "dist", "state", "add"];
var numCheckVal = ["phoneNum", "markSheetNo", "math", "phy", "che", "pinCode"];
var anyErrorPre = 0;

function process()
{
	for(var i = 0; i < strCheckVal.length; i++)
	{
		if( strCheck(document.getElementById(strCheckVal[i]).value) )
		{
			if(i == strCheckVal.length - 1)
			{
				 numCheckProcess(); 
			}			
		}
		else
		{
			anyErrorPre = 1;
			document.getElementById(strCheckVal[i] + "Err").textContent = "please check";
			if(i == strCheckVal.length - 1)
			{
				 numCheckProcess(); 
			}
		}
	}
	
	var yearCheck = ["dateOfBirth", "yearPassing", "add", "email"];
	
	for(var i = 0; i < yearCheck.length; i++)
	{
		
		if(document.getElementById(yearCheck[i]).value == "")
		{
			anyErrorPre = 1;
			document.getElementById(yearCheck[i] + "Err").textContent = "please insert Value";
		}
	}
	
	if(anyErrorPre == 1)
	{
		return false;
	}
	return true;
	
}

function numCheckProcess()
{
	for(var i = 0; i < numCheckVal.length; i++)
	{
		if(numCheck(document.getElementById(numCheckVal[i]).value))
		{
			console.log("magesh :-)");
		}
		else
		{
			anyErrorPre = 1;
			document.getElementById(numCheckVal[i] + "Err").textContent = "please check";
		}
	}
}



function strCheck(inputStr) 
{
	var patern = /^[a-zA-Z\s]+$/;
	
	if(inputStr.match(patern))
	{
		return true;
	}
	return false;
	
}

function numCheck(str) {
	
	var numPatern = /^[0-9\s]+$/;
	
	if(str.match(numPatern))
	{
		
		return true;
	}
	return false;
	
}