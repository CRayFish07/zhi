package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.EncodeTool;

import dao.ArticleDao;

import entity.Article;

public class AddArticleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddArticleServlet() {
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
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String auther = request.getParameter("auther");
		String module = request.getParameter("module");
		long time = (long) System.currentTimeMillis();
		Article article = new Article();
		article.setAuther(auther);
		article.setContent(content);
		article.setCreateTime(time);
		article.setModule(Integer.parseInt(module));
		article.setTitle(title);
		article.setUpdateTime(time);
		ArticleDao dao = new ArticleDao();
		int len = dao.addArticle(article);
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
