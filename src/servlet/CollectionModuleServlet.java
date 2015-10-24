package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ModuleDao;

public class CollectionModuleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CollectionModuleServlet() {
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
		String module = request.getParameter("module");
		int len = 0;
		ModuleDao dao = new ModuleDao();
		if (type.equals("1")) {// ÊÕ²Ø°å¿é
			len = dao.collection(name, module);
		} else if (type.equals("2")) {
			len = dao.uncollection(name, module);
		}
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
