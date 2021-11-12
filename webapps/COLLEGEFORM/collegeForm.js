var stringIds = ["collegeName", "address", "phoneNumber", "email"];
var numIds = ["mechSeatAvai", "eeeSeatAvai", "cecSeatAvai", "comSeatAvai", "civilSeatAvai"];
var fees = ["mechFees", "eeeFees", "cecFees", "comFees", "civilFees", "CounsilingCode", "collegeCutOff"];

function collegeDetailsCheck() 
{
	var anyErrorPre = false;
	for(var i = 0; i < stringIds.length; i++)
	{
		if( nullCheck( document.getElementById(stringIds[i]).value) )
		{
			anyErrorPre = true;
			document.getElementById(stringIds[i] + "Err").textContent = "please enter the value without numbers";
		}
	}

	for(var i = 0; i < numIds.length; i++)
	{
		if(numValCheck( document.getElementById(numIds[i]).value , 4) )
		{
			anyErrorPre = true;
			document.getElementById(numIds[i] + "Err").textContent = "Please check the seats count";
		}
	}

	for(var i = 0; i < fees.length; i++)
	{
			if(numValCheck( document.getElementById(fees[i]).value  , 7) )
			{
				anyErrorPre = true;
				document.getElementById(fees[i] + "Err").textContent = "Please check the value"; 
			}
	}
	 if(anyErrorPre)
	 {
	 	return false;
	 }
	 return true;
}

function nullCheck(inputStr)
{
	if(inputStr != "")
	{
		return false;
	}
	return true;
}

function numValCheck(inputStr, inputStrLen)
{
	var patern = /^[0-9]+$/;
	if(inputStr.match(patern) && (inputStr.length <= inputStrLen))
	{
		return false;
	}
	return true;
}	