package dao;

import java.util.List;
import java.util.Map;

import tools.MySql;

public class PersonCrotrolDao {

	public List<Map<String, Object>> personFavor(String userID) {
		String sql = "select a.article_id,a.article_title,a.article_content,"
				+ "a.author,a.create_time,a.faver,a.zan,a.scan,a.discuss"
				+ ",a.module_id,a.img,f.time as update_time,m.module_name"
				+ ",u.head_img,u.user_name "
				+ "from article as a,module as m,user as u,favor as f"
				+ " where u.user_id=f.user_id and f.module_id=m.module_id"
				+ " and f.article_id=a.article_id and f.user_id=? "
				+ "order by f.time desc";
		List<Map<String, Object>> favors = MySql.readDB(sql,
				new Object[] { userID });
		return favors;
	}

	public List<Map<String, Object>> personScan(String userID) {
		String sql = "select a.article_id,a.article_title,a.article_content,"
				+ "a.author,a.create_time,a.faver,a.zan,a.scan,a.discuss"
				+ ",a.module_id,a.img,f.read_time as update_time,m.module_name"
				+ ",u.head_img,u.user_name "
				+ "from article as a,module as m,user as u,scan as f"
				+ " where u.user_id=f.user_id and f.module_id=m.module_id"
				+ " and f.article_id=a.article_id and f.user_id=? "
				+ "order by f.read_time desc";
		List<Map<String, Object>> favors = MySql.readDB(sql,
				new Object[] { userID });
		return favors;
	}

	public List<Map<String, Object>> personDiscuss(String userID) {
		List<Map<String, Object>> favors = MySql.readDB(
				"select * from discussed where user_id=?",
				new Object[] { userID });
		return favors;
	}

	public List<Map<String, Object>> personCollection(String userID) {
		List<Map<String, Object>> coll = MySql
				.readDB("select m.module_id,m.module_name,m.module_content,m.attentions,"
						+ "m.article_num,m.time,m.img,c.isFollowed,m.module_admin from collection as c,"
						+ "module as m where c.user_id=? and c.module_id=m.module_id",
						new Object[] { userID });
		return coll;
	}
}
