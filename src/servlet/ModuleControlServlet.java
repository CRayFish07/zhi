package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import tools.MySql;

public class ModuleControlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ModuleControlServlet() {
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

		// 模块下的用户列表，同道中人
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String module = request.getParameter("module");
		List<Map<String, Object>> modules = MySql.readDB(
				"select u.user_id,u.user_name,u.head_img,u.user_desc "
						+ "from collection as c ,user as u "
						+ "where module_id=? and c.user_id=u.user_id",
				new Object[] { module });
		JSONArray array = JSONArray.fromObject(modules);
		out.print(array.toString());
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
