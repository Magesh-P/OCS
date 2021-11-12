package MyPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CollegeSelectionProcess")
public class CollegeSelectionProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CollegeSelectionProcess() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userId = request.getParameter("USER_ID");
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/OCS", "root", "");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from ApplayUser where USER_ID = " + userId);
			PrintWriter pw = response.getWriter();
			rs.next();
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
			String[] userDetails = {rs.getString(1), rs.getString(12), rs.getString(18), rs.getString(7), rs.getString(5)};
			pw.append(Arrays.toString(userDetails));
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		
	}

}
