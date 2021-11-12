package MyPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ApplayFormCheck
 */
@WebServlet("/ApplayFormCheck")
public class ApplayFormCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplayFormCheck() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter pw = response.getWriter();
		
		String name = request.getParameter("userName");
		String national = request.getParameter("national");
		String district = request.getParameter("dist");
		String state = request.getParameter("state");
		String address = request.getParameter("add");
		
		String phoneNum = request.getParameter("phoneNum");
		String markSheet = request.getParameter("markSheetNo");
		String mathMark = request.getParameter("math");
		String phyMark = request.getParameter("phy");
		String cheMark = request.getParameter("che");
		String pinCode = request.getParameter("pinCode");
		
		String dateOfBirth = request.getParameter("dateOfBirth");
		String yearOfPassing = request.getParameter("yearPassing");
		String gender = request.getParameter("gender");
		String emailId = request.getParameter("email");
		String community = request.getParameter("community");

		String[] strCheckVal = {name, national, district, state};
		String[] numCheckVal = {phoneNum, markSheet, mathMark, phyMark, cheMark, pinCode};		
		String[] yearCheck = {dateOfBirth, yearOfPassing, emailId, address};
		
		boolean anyErrorPre = false;
		
		for(int i = 0; i < strCheckVal.length; i++)
		{
			if ( !strCheck(strCheckVal[i]) )
			{
				anyErrorPre = true;
			}			
		}
		
		for(int i = 0; i < numCheckVal.length; i++)
		{
			if ( !numCheck(numCheckVal[i]) )
			{
				anyErrorPre = true;
			}			
		}
		
		for(int i = 0; i < yearCheck.length; i++)
		{
			if ( yearCheck[i].equals("") )
			{
				anyErrorPre = true;
			}			
		}
		
		if(!anyErrorPre)
		{
			float cutOff = Float.parseFloat(phyMark)/4 + Float.parseFloat(cheMark)/4 + Float.parseFloat(mathMark)/2;
			java.sql.Connection c = null;
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = DriverManager.getConnection("jdbc:mysql://localhost:3306/OCS", "root", "");
				
				PreparedStatement p = c.prepareStatement("SELECT USER_ID FROM ApplayUser ORDER BY USER_ID DESC LIMIT 1;");
				ResultSet countUser = p.executeQuery();
				int count = 0;
				while(countUser.next())
				{
					count = countUser.getInt(1);
				}
				count += 1;
				
				PreparedStatement p1 = c.prepareStatement("insert into ApplayUser values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				p1.setString(1, name);
				p1.setString(2, national);
				p1.setString(3, district);
				p1.setString(4, state);
				p1.setString(5, address);
				p1.setString(6, phoneNum);
				p1.setString(7, markSheet);
				p1.setString(8, mathMark);
				p1.setString(9, phyMark);
				p1.setString(10, cheMark);
				p1.setString(11, pinCode);
				p1.setString(12, dateOfBirth);
				p1.setString(13, yearOfPassing);
				p1.setString(14, gender);
				p1.setString(15, emailId);
				p1.setString(16, community);
				p1.setString(17, count+"");
				p1.setString(18, cutOff+"");
				p1.executeUpdate();
				
				PreparedStatement p2 = c.prepareStatement("update Users SET status = 1 where  email_pk = ?");
				p2.setString(1, emailId);
				int res1 = p2.executeUpdate();
				if(res1 == 1)
				{
					System.out.println("Data Added Successfully ..");
				}
				//this.getServletContext().getRequestDispatcher("/ThankssForm.html").forward(request, response);
				response.sendRedirect("http://www.google.co.in");
				
				c.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
			
		}
		else
		{
			pw.append("<p style = \"background-color:red; margin:auto; margin-left:40%;display:inline-block;\">Some Error Occure</p>");
			this.getServletContext().getRequestDispatcher("APPLAYFORM/ApplayForm.html").include(request, response);
			pw.close();
		}
	}
	
	public boolean strCheck(String inputStr)
	{
		
		if(inputStr.matches("[a-zA-Z]+"))
		{
			return true;
		}
		return false;
	}

	
	public boolean numCheck(String inputNum)
	{
		if(inputNum.matches("[0-9]+"))
		{
			return true;
		}
		return false;
	}

}
