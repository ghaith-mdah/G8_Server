package MySQLconnection;
//im salman
//Also Ibraheem +ghaith
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;

import gui.ServerStartController;
import javafx.collections.ObservableList;
import logic.Order;
import logic.User;

public class SQLconnection {
	private static Connection con;
	private static Statement statement;
	private static ResultSet rs;
/**
 * @author gethe
 * functionality : this method connect server to database set driver and connection 
 * input : String loc -location of the database in MySQL ,String password-MySQL password ,String user=MySQL username */
	public void connectToDB(String loc, String password, String user) throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeed");
		} catch (Exception ex) {
			System.out.println("Driver definition failed");
			/* handle the error */}

		try {
			con = DriverManager.getConnection(loc, user, password);

			System.out.println("SQL connection succeed");

		} catch (SQLException ex) {
			ServerStartController.flag = false;
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		}
	}
/**
 * @author gethe
 * functionality : this method returns the role for a given user +saving user data in fields , if user not found or a wrong password is given it returns a string according to the case
 * input : user u which contains username and password from login screen */
	public static String GetUserWithPassword(User u) {
		String query = "SELECT * FROM user WHERE Username=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, u.getUsername());
			rs = ps.executeQuery();
			rs.next();
			String Pass = rs.getString("Password");
			boolean loged = rs.getBoolean("IsLoggedIn");
			if (u.getPassword().equals(Pass)) {
				if (!loged) {
					String role = rs.getString("Role");
					u.setRole(role);
					u.setEmail(rs.getString("Email"));
					u.setFname(rs.getString("FirstName"));
					u.setLnaem(rs.getString("LastName"));
					u.setPhone(rs.getString("Phone Number"));
					System.out.print("password= " + Pass + " role= " + role);
					ps = con.prepareStatement("update user set IsLoggedIn = ? where Username = ?");
					ps.setBoolean(1, true);
					ps.setString(2, u.getUsername());
					ps.executeUpdate();
					return role;
				} else {
					return "You're Already logged in";
				}
			} else {
				return "Wrong Password";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			return "Username Not Found";
		}

	}
/**
 * @author gethe
 * functionality : this method sets the logged-in field as 0 in database for a given user
 * input: User u */
	public static void GetLogOut(User u) {
		String query = "update user set IsLoggedIn = ? where Username = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setBoolean(1, false);
			ps.setString(2, u.getUsername());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 * @author gethe
 * functionality: this method gets data for a user using his username as primary key - for reseting password
 * input user u - contains just username */
	public static String GetUser(User u) {
		String query = "SELECT * FROM user WHERE Username=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, u.getUsername());
			rs = ps.executeQuery();
			rs.next();
			u.setRole(rs.getString("Role"));
			u.setEmail(rs.getString("Email"));
			u.setFname(rs.getString("FirstName"));
			u.setLnaem(rs.getString("LastName"));
			u.setPhone(rs.getString("Phone Number"));
			return u.getEmail();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Username Not Found";
		}
	}
	/**
	 * @author gethe
	 * functionality:this method resets user password in database
	 * input: user u -contains username and new password
	 **/
	public static void ResetPassword(User u) {
		String query = "update user set Password = ? where Username = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, u.getPassword());
			ps.setString(2, u.getUsername());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
