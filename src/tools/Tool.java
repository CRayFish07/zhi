package tools;

import java.sql.*;

public class Tool {
	private static String url = "jdbc:mysql://localhost:3306/zhima?userUnicode=true&characterEncoding=UTF-8";
	private static String user = "root";
	private static String password = "ffffff";

	private Tool() {
	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getCon() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void free(ResultSet re, Statement stat, Connection con) {
		try {
			if (re != null)
				re.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}