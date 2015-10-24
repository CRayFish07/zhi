package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.MySql;

public class FocusServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FocusServlet() {
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

		//关注人时
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String focus = request.getParameter("focus");
		String type = request.getParameter("type");
		int size = 0;
		if (type.equals("1")) {//关注
			size = MySql.writeDB("insert into focus values(?,?)", new Object[] {
					name, focus });
			if (size == 1) {
				MySql.writeDB("update user set focus=focus+1 where user_id=?",
						new Object[] { name });
				MySql.writeDB(
						"update user set befocus = befocus+1 where user_id=?",
						new Object[] { focus });
			}
		} else {
			size = MySql.writeDB(
					"delete from focus where user_id=? and befocused=?",
					new Object[] { name, focus });
			if (size == 1) {
				MySql.writeDB("update user set focus=focus-1 where user_id=?",
						new Object[] { name });
				MySql.writeDB(
						"update user set befocus = befocus-1 where user_id=?",
						new Object[] { focus });
			}
		}
		if (size == 1) {

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
