package in.ac.vce;
import java.sql.*;

public class MySQLBean 
{
	public Boolean authenticate(String username, String password) {
		try {
			//DB Logic
			String url = "jdbc:mysql://localhost:3306/test";
			String DBusername = "root";
			String DBpassword = "";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection(url, DBusername, DBpassword);
			PreparedStatement stmt=con.prepareStatement("select username from login where username like ? and password like ?");
			stmt.setString(1,username);
			stmt.setString(2,password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
