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

/**
 * Servlet implementation class SignInFormCheck
 */
@WebServlet("/SignInFormCheck")
public class SignInFormCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInFormCheck() {
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
		String Email = request.getParameter("emailId");
		//String otpNum = request.getParameter("otpNumber");
			
		if(AccountCheck(name, password, response))
		{
			try 
			{		
				c = DriverManager.getConnection("jdbc:mysql://localhost:3306/OCS", "root", "");
				Statement stmt=c.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Users");
				boolean process = true;
				
				while(rs.next())
				{
					if(rs.getString(1).equals(name))
					{
						pw.append("<p style = \"background-color:red; margin:auto; margin-left:40%;display:inline-block;\">User Name Already Present</p>");
						this.getServletContext().getRequestDispatcher("/SIGNIN/SignInForm.html").include(request, response);
						process = false;
						pw.close();
						break;
					}
					
				}
				if(process)
				{
						stmt.executeUpdate("insert into Users values('"+name+"','"+password+"','"+Email+"',0)"); 
						c.close();
						this.getServletContext().getRequestDispatcher("/APPLAYFORM/ApplayForm.html").forward(request, response);
				}
				c.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		}
		
	}
	
	public boolean AccountCheck(String name, String password, HttpServletResponse res) throws IOException {

		if(name.length() > 6)
		{
			if(name.matches("[a-zA-Z](.*)"))
			{
				if(password.length() > 6 )
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


