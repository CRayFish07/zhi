package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import tools.MySql;

public class FocusListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FocusListServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//读关注时,返回关注与被关注的用户的信息
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
		if (type.equals("1")) {// 得到关注的列表
			users = MySql.readDB(
					"select u.user_id,u.sex,u.user_name,u.create_time,"
							+ "u.role,u.score,u.head_img,u.user_desc,"
							+ "u.focus,u.befocus"
							+ " from focus as f ,user as u "
							+ "where f.user_id=? and f.befocused = u.user_id",
					new Object[] { name });
		} else if (type.equals("2")) {// 得到被关注的用户的列表
			users = MySql.readDB(
					"select u.user_id,u.sex,u.user_name,u.create_time,"
							+ "u.role,u.score,u.head_img,u.user_desc,"
							+ "u.focus,u.befocus"
							+ " from focus as f ,user as u "
							+ "where f.befocused=? and f.user_id = u.user_id",
					new Object[] { name });
		}
		if (users.size() == 0) {
			out.print("null");
		} else {
			JSONArray object = JSONArray.fromObject(users);
			out.print(object.toString());
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
