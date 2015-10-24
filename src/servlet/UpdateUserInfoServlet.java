package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;

public class UpdateUserInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UpdateUserInfoServlet() {
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

		// 更新个人信息的操作
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String sex = request.getParameter("sex");
		String desc = request.getParameter("desc");
		String name = request.getParameter("name");
		String userName = request.getParameter("userName");
		User user = new User();
		user.setDesc(desc);
		user.setSex(sex);
		user.setID(name);
		user.setName(userName);
		UserDao dao = new UserDao();
		int len = 0;
		len = dao.updateUserInfo(user);
		if (len == 1) {
			out.print("success");
		} else {
			out.print("error");
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
