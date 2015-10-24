package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.EncodeTool;

import net.sf.json.JSONObject;
import dao.UserDao;
import entity.User;

public class AddUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AddUserServlet() {
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
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		long createTime = System.currentTimeMillis();
		User user = new User();
		user.setID(userID);
		user.setSex(sex);
		user.setPassword(password);
		user.setName("ÐÂÓÃ»§");
		user.setCreateTime(createTime);
		user.setCurTime(createTime);
		UserDao dao = new UserDao();
		int len = dao.addUser(user);
		if (len == 1) {
			List<Map<String, Object>> users = dao.getUserInfo(userID, password);
			JSONObject object = JSONObject.fromObject(users.get(0));
			out.print(object.toString());
		} else {
			out.print("error");
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
