package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import tools.MySql;

public class UserInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UserInfoServlet() {
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

		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		// 根据请求得到用户的信息
		String userName = request.getParameter("userName");
		String requestName = request.getParameter("requestName");
		// 请求被请求的用户信息
		List<Map<String, Object>> users = MySql.readDB(
				"select user_id,user_name,sex,create_time,role,"
						+ "score,head_img,user_desc,focus,befocus"
						+ " from user where user_id=?",
				new Object[] { requestName });
		List<Map<String, Object>> focus = MySql.readDB(
				"select * from focus where user_id=? and befocused=?",
				new Object[] { userName, requestName });
		if (focus.size() == 1) {
			users.get(0).put("isFocus", 1);
		} else if (focus.size() == 0) {
			users.get(0).put("isFocus", 0);
		}
		out.print(JSONObject.fromObject(users.get(0)).toString());
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
