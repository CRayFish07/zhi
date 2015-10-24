package dao;

import java.util.List;
import java.util.Map;

import tools.MySql;
import entity.Module;

public class ModuleDao {

	public int addModule(Module module) {
		int len = MySql.writeDB(
				"insert into module(module_name,"
						+ "module_admin,module_content,time) values(?,?,?,?)",
				new Object[] { module.getName(), module.getAdmin(),
						module.getContent(), module.getTime() });
		return len;
	}

	public int collection(String name, String module) {
		int len = MySql
				.writeDB(
						"insert into collection(user_id,module_id,isFollowed) values(?,?,?)",
						new Object[] { name, module, 1 });
		if (len == 1) {
			len = MySql
					.writeDB(
							"update module set attentions=attentions+1 where module_id=?",
							new Object[] { module });
		}
		return len;
	}

	public int uncollection(String name, String module) {
		int len = MySql.writeDB(
				"delete from collection where user_id=? and module_id=?",
				new Object[] { name, module });
		if (len == 1) {
			len = MySql
					.writeDB(
							"update module set attentions=attentions-1 where module_id=?",
							new Object[] { module });
		}
		return len;
	}

	public List<Map<String, Object>> tuijian(String name) {
		String sql = "select DISTINCT(m.module_id),m.module_name,"
				+ "m.module_admin,m.module_content,m.attentions,"
				+ "m.article_num,m.time,m.img"
				+ " from module AS m,collection as c "
				+ "WHERE m.module_id NOT in"
				+ "(SELECT module_id from collection where user_id=?)"
				+ " ORDER BY m.attentions DESC LIMIT 0,5";
		List<Map<String, Object>> list = MySql.readDB(sql,
				new Object[] { name });
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("isFollowed", 0);
		}
		return list;
	}
}
