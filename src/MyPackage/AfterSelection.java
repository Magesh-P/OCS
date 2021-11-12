package MyPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

/**
 * Servlet implementation class AfterSelection
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AfterSelection" })
public class AfterSelection extends  HttpServlet {
	private static final long serialVersionUID = 1L;
    public AfterSelection() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String headData = request.getParameter("heading").trim();
		int couCode = Integer.parseInt(request.getParameter("counsleCode").trim());
		int avaiSeat = Integer.parseInt(request.getParameter("avaiSeat").trim());
		avaiSeat --;
		System.out.println(headData + "  " + couCode + " " + avaiSeat);
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/OCS", "root", "");
			PreparedStatement p = c.prepareStatement("update CollegeDetails ApplayUser set "+headData+"=? where CounsilingCode = ?");
			p.setString(1, avaiSeat+"");
			p.setString(2, couCode+"");
			int res = p.executeUpdate();
			System.out.println(res);
			
			//PrintWriter pw = response.getWriter();
			
			TableCreation t = new TableCreation();
			t.doPost(request, response);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
