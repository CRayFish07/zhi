package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;
import tools.DateUtil;
import tools.MySql;
import tools.WebUtil;
import dao.UserDao;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
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
		String type = "0";
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		type = request.getParameter("type");
		if (type.equals("0")) {// 0 代表登录
			UserDao dao = new UserDao();
			List<Map<String, Object>> users = dao.getUserInfo(name, password);
			if (users.size() == 0) {
				out.print("error");
			} else {
				JSONObject jsonArray = JSONObject.fromObject(users.get(0));
				out.print(jsonArray.toString());
			}
		} else if (type.equals("1")) {// 1 代表修改密码
			int i = MySql.writeDB("update user set password=? where user_id=?",
					new Object[] { password, name });
			if (i == 1) {
				out.print("{success}");
			} else {
				out.print("{error}");
			}
		} else {// 上传用户头像
			// BASE64Decoder decoder = new BASE64Decoder();
			String uhead = request.getParameter("uhead");
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
							"/user/userhead_img/" + headName + ".png");
					OutputStream os = new FileOutputStream(imgFilePath);
					os.write(b);
					os.flush();
					os.close();

					path = WebUtil.getServerUrl(request) + "user/userhead_img/"
							+ headName + ".png";
					UserDao dao = new UserDao();
					int size = dao.updateUserImg(path, name);
					if (size == 0) {
						out.print("error");
					} else {
						out.print(path);
					}
				}
			}
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
