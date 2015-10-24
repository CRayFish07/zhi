package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;
import tools.DateUtil;
import tools.WebUtil;
import dao.UserDao;

public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ImageServlet() {
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
		String uhead = request.getParameter("image");
		@SuppressWarnings("unused")
		String path = "";
		String headName = DateUtil.getCurDate("yyMMddss");
		if (uhead != null) {
			String temp = uhead.substring(0, 4);
			if (temp.equals("http")) {
				path = uhead;
			} else {
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] b = decoder.decodeBuffer(uhead);
				for (int i = 0; i < b.length; i++) {
					if (b[i] < 0) {
						int tmp154_152 = i;
						byte[] tmp154_150 = b;
						tmp154_150[tmp154_152] = ((byte) (tmp154_150[tmp154_152] + 256));
					}
				}
				String imgFilePath = super.getServletContext().getRealPath(
						"/user/images/" + headName + ".png");
				OutputStream os = new FileOutputStream(imgFilePath);
				os.write(b);
				os.flush();
				os.close();
				path = WebUtil.getServerUrl(request) + "user/images/"
						+ headName + ".png";
				out.print(path);
			}
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
