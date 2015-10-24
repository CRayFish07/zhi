package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.EncodeTool;

import dao.ArticleControlDao;
import dao.ArticleDao;
import entity.Discuss;
import entity.Favor;

public class ArticleControlServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArticleControlServlet() {
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
		// 点赞，收藏，浏览
		String name = request.getParameter("name");// 用户名
		String article = request.getParameter("article");// 帖子ID
		String type = request.getParameter("type");// 四种方式的类型
		// String module = request.getParameter("module");// 模块的ID
		String itemID = request.getParameter("itemID");// 评论评论时设置为被评论的id，其余可以为空
		String content = request.getParameter("content");// 评论内容
		long time = (long) System.currentTimeMillis();
		ArticleDao dao = new ArticleDao();
		ArticleControlDao controlDao = new ArticleControlDao();
		if (itemID == null) {
			itemID = "0";
		}
		int item = Integer.parseInt(itemID);
		int len = 0;
		int size = 0;
		if (type.equals("1")) {// 点赞,传Article就行，用户名
			len = dao.zan(Integer.parseInt(article));
			size = 2;
		} else if (type.equals("2")) {
			// 评论：传用户名，帖子ID，评论内容，被评论的id(如果不传值，设置为评论的文章)
			len = dao.discuss(Integer.parseInt(article));
			size = controlDao.discussed(new Discuss(Integer.parseInt(article),
					name, content, time, item));
		} else if (type.equals("3")) {// 浏览：传用户名，帖子ID
			len = dao.scan(Integer.parseInt(article));
			size = controlDao.scan(new Favor(name, Integer.parseInt(article),
					ArticleDao.getArticleTitle(article), Integer
							.parseInt(ArticleDao.getModuleID(article)),
					EncodeTool.toChinese(ArticleDao.geModuletName(ArticleDao
							.getModuleID(article))), time));
		} else if (type.equals("4")) {// 收藏：传用户名，帖子ID
			len = dao.favor(Integer.parseInt(article));
			size = controlDao.favor(new Favor(name, Integer.parseInt(article),
					ArticleDao.getArticleTitle(article), Integer
							.parseInt(ArticleDao.getModuleID(article)),
					EncodeTool.toChinese(ArticleDao.geModuletName(ArticleDao
							.getModuleID(article))), time));
		}
		if (len == 0 || size == 0) {
			out.print("error");
		} else {
			out.print(len);
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
