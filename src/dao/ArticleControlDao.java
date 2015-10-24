package dao;

import entity.Discuss;
import entity.Favor;
import tools.MySql;

public class ArticleControlDao {

	// ∆¿¬€
	public int discussed(Discuss discuss) {
		if (discuss.getItemID() == 0) {
			discuss.setType(0);
		} else {
			discuss.setType(1);
		}
		int len = MySql
				.writeDB(
						"insert into discussed(article_id,"
								+ "user_id,content,time,discussed_type,discussed_item_id)"
								+ " values(?,?,?,?,?,?)", new Object[] {
								discuss.getArticleID(), discuss.getUserID(),
								discuss.getContent(), discuss.getTime(),
								discuss.getType(), discuss.getItemID() });
		if (len != 0) {
			len = MySql.writeDB(
					"update article set update_time=? where article_id=?",
					new Object[] { discuss.getTime(), discuss.getArticleID() });
		}
		return len;
	}

	//  ’≤ÿ
	public int favor(Favor favor) {
		int len = MySql.writeDB(
				"insert into favor(user_id" + ",article_id,article_title,"
						+ "module_id,module_name,time) values(?,?,?,?,?,?)",
				new Object[] { favor.getUserID(), favor.getArticleId(),
						favor.getArticleTitle(), favor.getModuleID(),
						favor.getModuleName(), favor.getTime() });
		return len;
	}

	// ‰Ø¿¿
	public int scan(Favor favor) {
		int len = MySql.writeDB(
				"insert into scan(user_id,article_id,article_title,module_id,module_name,"
						+ "read_time) values(?,?,?,?,?,?)",
				new Object[] { favor.getUserID(), favor.getArticleId(),
						favor.getArticleTitle(), favor.getModuleID(),
						favor.getModuleName(), favor.getTime() });
		return len;
	}
}
