package dao;

import java.util.List;
import java.util.Map;

import tools.MySql;
import entity.Article;

public class ArticleDao {

	public int addArticle(Article article) {
		int len = MySql.writeDB(
				"insert into article(article_title,"
						+ "article_content,author,"
						+ "create_time,module_id,img,"
						+ "update_time) values(?,?,?,?,?,?,?)",
				new Object[] { article.getTitle(), article.getContent(),
						article.getAuther(), article.getCreateTime(),
						article.getModule(), article.getImg(),
						article.getUpdateTime() });
		if (len == 1) {
			len = MySql
					.writeDB(
							"update module set article_num=article_num+1 where module_id=?",
							new Object[] { article.getModule() });
		}
		return len;
	}

	public int zan(int article) {
		List<Map<String, Object>> articles = MySql.readDB(
				"select zan from article where article_id=?",
				new Object[] { article });
		int zan = (Integer) articles.get(0).get("zan");
		int len = MySql.writeDB("update article set zan=? where article_id=?",
				new Object[] { zan + 1, article });
		if (len == 1) {
			return zan + 1;
		}
		return len;
	}

	public int scan(int article) {
		List<Map<String, Object>> articles = MySql.readDB(
				"select scan from article where article_id=?",
				new Object[] { article });
		int scan = (Integer) articles.get(0).get("scan");
		int len = MySql.writeDB("update article set scan=? where article_id=?",
				new Object[] { scan + 1, article });
		if (len == 1) {
			return scan + 1;
		}
		return len;
	}

	public int discuss(int article) {
		List<Map<String, Object>> articles = MySql.readDB(
				"select discuss from article where article_id=?",
				new Object[] { article });
		int discuss = (Integer) articles.get(0).get("discuss");
		int len = MySql.writeDB(
				"update article set discuss=? where article_id=?",
				new Object[] { discuss + 1, article });
		if (len == 1) {
			return discuss + 1;
		}
		return len;
	}

	public int favor(int article) {
		List<Map<String, Object>> articles = MySql.readDB(
				"select faver from article where article_id=?",
				new Object[] { article });
		int favor = (Integer) articles.get(0).get("faver");
		int len = MySql.writeDB(
				"update article set faver=? where article_id=?", new Object[] {
						favor + 1, article });
		if (len == 1) {
			return favor + 1;
		}
		return len;
	}

	public static String geModuletName(String moduleID) {
		List<Map<String, Object>> values = MySql.readDB(
				"select module_name from module where module_id=?",
				new Object[] { moduleID });
		return values.get(0).get("module_name").toString();
	}

	public static String getArticleTitle(String moduleID) {
		List<Map<String, Object>> values = MySql.readDB(
				"select article_title from article where article_id=?",
				new Object[] { moduleID });
		return values.get(0).get("article_title").toString();
	}

	public static String getModuleID(String moduleID) {
		List<Map<String, Object>> values = MySql.readDB(
				"select module_id from article where article_id=?",
				new Object[] { moduleID });
		return values.get(0).get("module_id").toString();
	}
	/**
	 * 山东捷瑞数字科技股份有限公司
	 */
}
