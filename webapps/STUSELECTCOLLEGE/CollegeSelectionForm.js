/**
 * 
 */

function sendRequest(par, url, callBack)
{
	const Http = new XMLHttpRequest();
	
	Http.onreadystatechange = function() 
	{
		
	    if (Http.readyState == 4 && Http.status == 200) 
	    {
		     if(callBack == 2)
		    {
		    	 alert(Http.responseText);
		    	 tableCreation(Http.responseText);
		   	}
		    if(callBack == 1)
		    {
		    	fillUserDetail(Http.responseText);
		    }
		    if(callBack == 3)
		    {
		    	tableCreation(Http.responseText);
		   	}
		    
	    }

	  }; 
	
	Http.open("POST", url,true);
	Http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	console.log(par);
	Http.send(par);
}

function userDetail() 
{
		
	var par = "USER_ID="+ document.getElementById("userId").value;
	var url = '/OCS/getUserDetails';
	var userDe = sendRequest(par, url, 1);	
		
}

function createTable()
{
	var url = '/OCS/Tablecreation';
	var par = "userId="+ document.getElementById("userId").value;
	if(par != null )
	{
		var createTa = sendRequest(par, url, 2);
	}
	else
	{
		alert("Enter the User Id..!");
	}
	
}


//It's a callback function

function tableCreation(userDeatils) {
	
	tableDivClick = true;
	var details = userDeatils.substr(2, userDeatils.length -6);		
	var val = details.split('], [');
	
	var res = [];
	for(var i = 0; i < val.length; i++)
	{
		var temp = [];
		temp = val[i].split(",");
		res.push(temp);
	}
	
	generate_table(res.length, res[0].length, res);
	
}
/*
 * This code copied by ---> https://developer.mozilla.org/en-US/docs/Web/API/Document_Object_Model/Traversing_an_HTML_table_with_JavaScript_and_DOM_Interfaces
 * This method generate table in particular column and row
 * 
 */
function generate_table(rowCount, colCount, val) 
{
		 var thead1 = ["collegeName", "Address", "phoneNumber", "email", "MechSeat", "EeeSeat", "EcSeat", "ComSeat", "CivilSeat", "mechFees", "EeeFees", "EcFees", "comFees", "civilFees", "CounsilingCode", "CutOff"];
			
		  
		  var table1Div = document.getElementById("tableDiv");
		  
		  
		  table1Div.textContent = "";

		  // creates a <table> element and a <tbody> element
		  var tbl = document.createElement("table");
		  
		  
		  tbl.setAttribute('class', 'collegeDetailTable');
		  
		  var tblBody = document.createElement("tbody");
		  
		  tblBody.setAttribute('class', 'collegeDetailTableBody');
		  
		  var tableHead = tbl.createTHead();
		  
		  var headrow = tableHead.insertRow(0);
		  
		  // creating all cells
		  for (var i = 0; i <= rowCount; i++) 
		  {
			  
		    // creates a table row
		    var row = document.createElement("tr");

		    for (var j = 0; j < colCount; j++) 
		    {
		    	
		      // Create a <td> element and a text node, make the text
		      // node the contents of the <td>, and put the <td> at
		      // the end of the table row
		    	if(i == 0)
		    	{
		    		var the = document.createElement("th");
		    		the.appendChild(document.createTextNode(thead1[j]));
		    		headrow.appendChild(the);
		    	}
		    	else
		    	{
		    		var cell = document.createElement("td");
		   	      	var cellText = document.createTextNode(val[i-1][j]);
		   	      	if( (j < 9) && (j > 3) )
		   	      	{
		   	      		cellClick(cell, thead1[j], val[i-1] , row);
		   	      	}	
		   	      	cell.appendChild(cellText);
		   	      	row.appendChild(cell);
		    	}
		    }
		    
		 // add the row to the end of the table body
		    tblBody.appendChild(row);
		     
		  }

		  // put the <tbody> in the <table>
		  tbl.appendChild(tblBody);
		  // appends <table> into <body>
		  table1Div.appendChild(tbl); 	
	
}

/*
	This method fill the user detail
*/

function fillUserDetail(information)
{
	/*
	 * Print Writer format
	 * {
	 * 		name : UserName --> tableColum(1)
	 * 		dateBirth : userDateOfBirth ---> (12)
	 * 		cut-Off : cutoff ---> (18)
	 * 		number : markSheetNumber --> (7)
	 * 		Address : Address --> (5)
	 * }
	 */
	
	var userDetails = information.substr(1, information.length -2).split(",");
	document.getElementById("name").value = userDetails[0];
	document.getElementById("dob").value = userDetails[1];
	document.getElementById("cutoff").value = userDetails[2];
	document.getElementById("markSheetNo").value = userDetails[3];
	document.getElementById("address").value = userDetails[4];
	

}

function cellClick(cell, heading, rowData, rowObj)
{
	
	cell.onclick = function()
	{
		
		rowObj.style.backgroundColor = "#1ba94c";
		cell.style.backgroundColor = "rgb(255, 176, 0)";
			
		if(confirm("You select " + rowData[0] + "College "+ heading + "department"))
		{
			var userIdObj = document.getElementById("userId");
			userIdObj.value = "";
			conformCollege(cell, heading, rowData);
		}
		else
		{
			alert("please select College");
		}
			
	};
	
}

function conformCollege(cell, head, data)
{
	var avaiSeat = cell.textContent;
	avaiSeat ++;
	avaiSeat --;
	if(avaiSeat > 0)
	{
		var params = "heading="+head+"&"+"counsleCode="+data[14]+"&"+"avaiSeat="+avaiSeat+"&"+"userId="+ document.getElementById("userId").value;
		var url = '/OCS/decreaseCount';
		var userDe = sendRequest(params, url, 3);
	}
	else
	{
		alert("Please Select Other college.");
	}
}
