package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySql {
	private static String url = "jdbc:mysql://localhost:3306/zhima?userUnicode=true&characterEncoding=UTF-8";
	private static String user = "root";
	private static String password = "ffffff";

	private MySql() {
	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getCon() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void free(ResultSet re, Statement stat, Connection con) {
		try {
			if (re != null)
				re.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static int readD(String sql, Object[] params) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		int rows = 0;

		try {

			con = Tool.getCon();
			ps = (PreparedStatement) con.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			re = ps.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) re.getMetaData();
			int count = rsmd.getColumnCount();
			for (int i = 0; i < count; i++) {
				System.out.println(rsmd.getColumnName(i + 1));
				System.out.println(rsmd.getColumnTypeName(i + 1));
			}
			while (re.next()) {

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Tool.free(re, ps, con);
		}

		return rows;

	}

	public int writeDB(String sql) {
		return writeDB(sql, null);
	}

	public static int writeDB(String sql, Object[] params) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		// String sql = "";
		int row = 0;

		try {
			con = MySql.getCon();
			ps = (PreparedStatement) con.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySql.free(re, ps, con);
		}
		return row;
	}

	public static List<Map<String, Object>> readDB(String sql, Object[] params) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// map<>里，string 是键，就是列名，object是值，是集合，一个map得到一行记录

		try {
			con = Tool.getCon();
			ps = con.prepareStatement(sql);

			// sql语句参数化
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();// 一共获得几列
			String[] columName = new String[count];
			for (int i = 0; i < count; i++) {
				// System.out.println(rsmd.getColumnName(i + 1));
				// System.out.println(rsmd.getColumnTypeName(i + 1));
				columName[i] = rsmd.getColumnName(i + 1);// 获得列名
			}
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < count; i++) {
					map.put(columName[i], rs.getObject(i + 1));

				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Tool.free(rs, ps, con);
		}
		return list;
	}

}
