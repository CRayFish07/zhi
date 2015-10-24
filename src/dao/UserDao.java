package dao;

import java.util.List;
import java.util.Map;

import tools.MySql;
import entity.User;

public class UserDao {

	public int addUser(User user) {
		int len = MySql.writeDB(
				"insert into user(user_id,password,user_name,sex,"
						+ "create_time,cur_time) values(?,?,?,?,?,?)",
				new Object[] { user.getID(), user.getPassword(),
						user.getName(), user.getSex(), user.getCreateTime(),
						user.getCurTime() });
		return len;
	}

	public int updateUserImg(String img, String userID) {
		int len = MySql.writeDB("update user set head_img=? where user_id=?",
				new Object[] { img, userID });
		return len;
	}

	public int updateUserInfo(User user) {
		int len = MySql
				.writeDB(
						"update user set sex=?,user_desc=?,user_name=? where user_id=?",
						new Object[] { user.getSex(), user.getDesc(),
								user.getName(), user.getID() });
		return len;
	}

	public List<Map<String, Object>> getUserInfo(String name, String password) {
		List<Map<String, Object>> users = MySql.readDB(
				"select * from user where user_id = ? and password=?",
				new Object[] { name, password });
		// 判断是否有此人
		if (users.size() == 0 || users == null) {
			return users;
		} else {
			// 将个人信息放到服务器，防止在android多次上传用户信息
			List<Map<String, Object>> collects = MySql.readDB(
					"select * from collection where user_id=?",
					new Object[] { name });
			List<Map<String, Object>> favors = MySql.readDB(
					"select * from favor where user_id=?",
					new Object[] { name });
			users.get(0).put("collection", collects.size());
			users.get(0).put("favor", favors.size());
		}
		return users;
	}

	public int updateUserName(String name, String userName) {
		int len = MySql.writeDB("update user set user_name=? where user_id=?",
				new Object[] { userName, name });
		return len;
	}
}
