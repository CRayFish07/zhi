package servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpush.MessagePush;

public class MessagePushServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessagePushServlet() {
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
		int ret = 0;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String msg = request.getParameter("msg");
		// String major = request.getParameter("majorName");
		// String stuClass = request.getParameter("className");
		String alias = request.getParameter("selStuNos");
		MessagePush push = new MessagePush(msg, title);
		long msgId = 0;
		if (alias != null && !alias.equals("")) {
			String[] aliasArr = alias.split(",");
			Set<String> aliasSet = new HashSet<String>();
			for (String item : aliasArr) {
				aliasSet.add(item);
			}
			msgId = push.sendPushAlias(aliasSet);
		} else {
			msgId = push.sendPushAll();
		}
		// request.getRequestDispatcher("/push.jsp").forward(request, response);
	}

	public void init() throws ServletException {
	}

}
