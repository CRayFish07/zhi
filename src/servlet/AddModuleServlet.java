package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.EncodeTool;

import dao.ModuleDao;

import entity.Module;

public class AddModuleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddModuleServlet() {
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
		String name = request.getParameter("moduleName");
		String content = request.getParameter("content");
		String admin = request.getParameter("admin");
		long time = (long) System.currentTimeMillis();
		Module module = new Module();
		module.setAdmin(admin);
		module.setContent(content);
		module.setName(name);
		module.setTime(time);
		ModuleDao dao = new ModuleDao();
		int len = dao.addModule(module);
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
