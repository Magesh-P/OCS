package MyPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintStream;
/**
 * Servlet implementation class LoinFormCheck
 */
@WebServlet("/LoinFormCheck")
public class LoinFormCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoinFormCheck() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e1) 
		{
			e1.printStackTrace();
		}
			
		Connection c = null;
                
		response.setContentType("text/html"); 
		PrintWriter pw=response.getWriter();
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		if(AccountCheck(name, password, response))
		{
			try 
			{	
			        System.out.println("cor");
				c = DriverManager.getConnection("jdbc:mysql://localhost:3306/OCS", "root", "");
				Statement stmt=c.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Users");
				boolean process = true;
				while(rs.next())
				{
					if(rs.getString(1).equals(name))
					{
						if(rs.getString(2).equals(password))
						{
							if(rs.getInt(4) == 0)
							{
								//change User Apply form
								process = false;
								this.getServletContext().getRequestDispatcher("/APPLAYFORM/ApplayForm.html").forward(request, response);
								c.close();
								break;
							}
							else
							{
								process = false;
								response.sendRedirect("http://www.google.co.in");  
								c.close();
								break;
							}
							
						}
						else
						{
							pw.print("<p style = \"background-color:red; margin:auto; margin-left:40%;display:inline-block;\">Wrong Password !</p>");
							process = false;
							this.getServletContext().getRequestDispatcher("/LOGIN/LogInForm.html").include(request, response);
							c.close();
							break;
						}
					}
					
				}
				if(process)
				{
					pw.println("<p style = \"background-color:red; margin:auto; margin-left:40%;display:inline-block;\">Check UserName</p>");
					this.getServletContext().getRequestDispatcher("/LogInForm.html").include(request, response);
					c.close();
				}
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		}
		
	}
	/*
	 * This method check user Name And Password 
	 * Name - length, pattern check
	 * Password - length check
	 * 
	 * Arguments - username, password, http response  
	 */
	public boolean AccountCheck(String name, String password, HttpServletResponse res) throws IOException {

		if(name.length() > 6)
		{
			if(name.matches("[a-zA-Z](.*)"))
			{
				if(password.length() > 6)
				{
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
		
	}
}
