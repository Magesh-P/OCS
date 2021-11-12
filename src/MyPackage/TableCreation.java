package MyPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

/**
 * Servlet implementation class TableCreation
 */
@WebServlet("/TableCreation")
public class TableCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableCreation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId").trim();
		System.out.println(userId);
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/OCS", "root", "");
			java.sql.Statement stmt = c.createStatement();
			
			ResultSet rs3 = stmt.executeQuery("SELECT CutOff FROM ApplayUser where USER_ID = "+ userId);
			rs3.next();
			String cutOffMark = rs3.getString(1); 
			
			
			// count the table row
			ResultSet rs2 = stmt.executeQuery("SELECT COUNT(1) FROM CollegeDetails");
			rs2.next();
			int rowCout = rs2.getInt(1);
			
			//Get all user  details in database 
			ResultSet rs = stmt.executeQuery("select * from CollegeDetails");
			PrintWriter pw = response.getWriter();
			
			// Count the table column
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int columnCount = rsmd.getColumnCount(); 
			
			String[][] tableData = new String[rowCout][columnCount];
			
			int i =0; 
			
			while(rs.next())
			{
				if(Double.parseDouble(cutOffMark) >= Double.parseDouble(rs.getString(16)))
				{
					for(int j = 1; j <= columnCount; j++)
					{
						tableData[i][j-1] = rs.getString(j);
					}
				}
				i++;
			}
			
			pw.println(Arrays.deepToString(tableData));
			
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			
			e.printStackTrace();
		}
		
	}
	
}
