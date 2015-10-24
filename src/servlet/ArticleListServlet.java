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

import tools.Pager;

public class ArticleListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ArticleListServlet() {
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
		String num = request.getParameter("pageNum");
		String type = request.getParameter("type");// all代表显示所有，其他代表输入的是板块ID代表显示板块内的帖子
		// 数据库分页显示
		List<Map<String, Object>> lists = Pager.srollSql(Integer.parseInt(num),
				type);
		JSONArray array = JSONArray.fromObject(lists);
		out.print(array.toString());
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
