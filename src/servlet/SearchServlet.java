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

import tools.EncodeTool;
import tools.MySql;

public class SearchServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchServlet() {
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
		// ËÑË÷°å¿é
		String search = request.getParameter("search");
		String type = request.getParameter("type");
		List<Map<String, Object>> searches = null;
		if (type.equals("1")) {// ¼ìË÷°å¿é
			searches = MySql.readDB(
					"select * from module where module_name like '%" + search
							+ "%'", new Object[] {});
		} else if (type.equals("2")) {
			searches = MySql.readDB(
					"select * from article where article_title like '%"
							+ search + "%'", new Object[] {});
		}
		JSONArray array = JSONArray.fromObject(searches);
		out.print(array.toString());
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
