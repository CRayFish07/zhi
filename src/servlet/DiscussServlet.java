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

public class DiscussServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DiscussServlet() {
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
		String article = request.getParameter("article");
		String type = request.getParameter("type");
		List<Map<String, Object>> discusses = null;
		discusses = MySql
				.readDB("select u.user_id,u.user_name,u.head_img,"
						+ "d.discussed_id,d.article_id,d.content,d.time,d.discussed_item_id,d.discussed_type"
						+ " from discussed as d,user as u"
						+ " where article_id=? and d.user_id=u.user_id and d.discussed_type=?"
						+ " order by time asc", new Object[] { article, type });
		/*
		 * if (itemID == null || itemID == "") { // 得到文章下的评论 discusses = MySql
		 * .readDB("select u.user_id,u.user_name,u.head_img," +
		 * "d.discussed_id,d.article_id,d.content,d.time,d.discussed_item_id" +
		 * " from discussed as d,user as u" +
		 * " where article_id=? and discussed_item_id=0 and d.user_id=u.user_id"
		 * + " order by time asc", new Object[] { article }); } else { //
		 * 得到评论下的评论 discusses = MySql
		 * .readDB("select u.user_id,u.user_name,u.head_img," +
		 * "d.discussed_id,d.article_id,d.content,d.time,d.discussed_item_id" +
		 * " from discussed as d,user as u" +
		 * " where article_id=? and discussed_item_id=? and d.user_id=u.user_id"
		 * + " order by time asc", new Object[] { article, itemID }); }
		 */
		out.print(JSONArray.fromObject(discusses));
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
