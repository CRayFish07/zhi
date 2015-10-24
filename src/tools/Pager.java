package tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pager {

	public static List<Map<String, Object>> srollSql(int page, String type) {

		List<Map<String, Object>> lists = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con = Tool.getCon();
			// 鑾峰緱鎬昏鏁�
			int totalRows = 0;
			ps = con.prepareStatement("select count(*) from article");
			rs = ps.executeQuery();
			if (rs.next()) {
				totalRows = rs.getInt(1);
			}

			// 鎬婚〉鏁�
			int pageSize = 10;
			int totalPages;

			if (totalRows % pageSize == 0) {
				totalPages = totalRows / pageSize;
			} else {
				totalPages = totalRows / pageSize + 1;
			}

			if (page <= 0) {
				page = 1;
			}
			if (page > totalPages) {
				page = totalPages;
			}
			int first = 1;
			int last = totalPages;
			int before = page - 1;
			if (before < 1) {
				before = last;
			}
			int after = page + 1;
			if (after > totalPages) {
				after = first;
			}
			if (type.equals("all")) {
				sql = "select a.article_id,a.article_title,a.article_content,"
						+ "a.author,a.create_time,a.faver,a.zan,a.scan,a.discuss"
						+ ",a.module_id,a.img,a.update_time,m.module_name"
						+ ",u.head_img,u.user_name "
						+ "from article as a,module as m,user as u"
						+ " where a.module_id=m.module_id and a.author=u.user_id "
						+ "order by a.scan desc limit ?,?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, pageSize * (page - 1));
				ps.setInt(2, pageSize);
			} else {
				sql = "select a.article_id,a.article_title,a.article_content,"
						+ "a.author,a.create_time,a.faver,a.zan,a.scan,a.discuss"
						+ ",a.module_id,a.img,a.update_time,m.module_name"
						+ ",u.head_img,u.user_name "
						+ "from article as a,module as m,user as u "
						+ "where a.module_id=? and a.module_id=m.module_id and a.author=u.user_id"
						+ " order by a.update_time desc limit ?,?";
				ps = con.prepareStatement(sql);
				ps.setString(1, type);
				ps.setInt(2, pageSize * (page - 1));
				ps.setInt(3, pageSize);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();// 涓�叡鑾峰緱鍑犲垪
			String[] columName = new String[count];
			for (int i = 0; i < count; i++) {
				columName[i] = rsmd.getColumnName(i + 1);// 鑾峰緱鍒楀悕
			}
			lists = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < count; i++) {
					map.put(columName[i], rs.getObject(i + 1));
				}
				// 寰楀埌鍥剧墖璺緞
				String content = rs.getString("article_content");
				Pattern p = Pattern
				// .compile("/<img.*src\\s*=\\s*[\"|\']?\\s*([^>\"\'\\s]*)/i");
						.compile("src=\"([^\"]+)\"", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(content);
				List<String> images = new ArrayList<String>();
				while (m.find()) {
					images.add(m.group(1));
				}
				map.put("imageList", images);
				// int articleID = rs.getInt("article_id");
				lists.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Tool.free(rs, ps, con);
		}
		return lists;
	}
}
