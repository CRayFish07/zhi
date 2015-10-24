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

import dao.ModuleDao;
import dao.PersonCrotrolDao;

public class PersonControlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public PersonControlServlet() {
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
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		PersonCrotrolDao dao = new PersonCrotrolDao();
		List<Map<String, Object>> maps = null;
		switch (Integer.parseInt(type)) {
		case 1:// 收藏信息
			maps = dao.personFavor(name);
			break;
		case 2:// 阅读记录
			maps = dao.personScan(name);
			break;
		case 3:// 评论记录
			maps = dao.personDiscuss(name);
			break;
		case 4:// 关注的板块
			maps = dao.personCollection(name);
			break;
		case 5:// 关注的板块下面的推荐
			maps = new ModuleDao().tuijian(name);
			break;
		}
		JSONArray array = JSONArray.fromObject(maps);
		out.print(array.toString());
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
