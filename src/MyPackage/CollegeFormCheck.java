package MyPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CollegeFormCheck
 */
@WebServlet("/CollegeFormCheck")
public class CollegeFormCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollegeFormCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		
		String collegeName = request.getParameter("collegeName");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		
		String mechSeatAvai = request.getParameter("mechSeatAvai");
		String eeeSeatAvai = request.getParameter("eeeSeatAvai");
		String cecSeatAvai = request.getParameter("cecSeatAvai");
		String comSeatAvai = request.getParameter("comSeatAvai");
		String civilSeatAvai = request.getParameter("civilSeatAvai");
		
		String mechFees = request.getParameter("mechFees");
		String eeeFees = request.getParameter("eeeFees"); 
		String cecFees = request.getParameter("cecFees");
		String comFees = request.getParameter("comFees");
		String civilFees = request.getParameter("civilFees");
		String CounsilingCode = request.getParameter("CounsilingCode");
		String collegeCutOff = request.getParameter("collegeCutOff");
		
		
		String[] stringIds = {collegeName, address, phoneNumber, email};
		String[] numIds = {mechSeatAvai, eeeSeatAvai, cecSeatAvai, comSeatAvai, civilSeatAvai};
		String[] fees = {mechFees, eeeFees, cecFees, comFees, civilFees, CounsilingCode, collegeCutOff};

		
		boolean anyErrorPre = false;
		
		for(int i = 0 ; i < stringIds.length; i++)
		{
			if( nullCheck(stringIds[i]) )
			{
				anyErrorPre = true;
			}
		}
		
		for(int i = 0; i < numIds.length; i++)
		{
			if( numValCheck( numIds[i], 4) )
			{
				anyErrorPre = true;
			}
		}
		
		for(int i = 0; i < fees.length; i++)
		{
				if(numValCheck( fees[i] , 7) )
				{
					anyErrorPre = true;
				}
		}
		
		if(anyErrorPre)
		{
			pw.append("<p style = \"background-color:red; margin:auto; margin-left:40%;display:inline-block;\">Some Error Occure</p>");
			this.getServletContext().getRequestDispatcher("/COLLEGEFORM/collegeForm.html").include(request, response);
			pw.close();
		}
		else
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/OCS", "root", "");
				Statement stmt=c.createStatement();
				stmt.executeUpdate("insert into CollegeDetails values('"+collegeName+"','"+address+"','"+phoneNumber+"','"+email+"','"+mechSeatAvai+"','"+eeeSeatAvai+"','"+cecSeatAvai+"','"+comSeatAvai+"','"+civilSeatAvai+"','"+mechFees+"','"+eeeFees+"','"+cecFees+"','"+comFees+"','"+civilFees+"','"+CounsilingCode+"','"+collegeCutOff+"')");
				response.sendRedirect("http://www.google.co.in");  
				//this.getServletContext().getRequestDispatcher("/LogInForm.html").forward(request, response);
				pw.close();
				c.close();
				System.out.println("success");
			} 
			catch (SQLException | ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
	
	}
	
	public boolean nullCheck(String inputStr)
	{
		if(inputStr != "")
		{
			return false;
		}
		return true;
	}
	
	public boolean numValCheck(String inputStr, int inputStrLen)
	{
		if((inputStr.matches("[0-9]+")) && (inputStr.length() <= inputStrLen))
		{
			return false;
		}
		return true;
	}	
}
