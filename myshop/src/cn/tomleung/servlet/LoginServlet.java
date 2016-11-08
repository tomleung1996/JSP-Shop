package cn.tomleung.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tomleung.dao.DAOFactory;
import cn.tomleung.dao.UserDAO;
//import cn.tomleung.entity.Fail;
import cn.tomleung.entity.User;
import cn.tomleung.tool.MD5;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "处理登录", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDAO userDAO = DAOFactory.getUserDAOInstance();
		HttpSession session = request.getSession(true);

		try {
			User user = userDAO.queryByName(username);
			if (user == null || (!MD5.Bit32(password).equalsIgnoreCase(user.getPassword()))) {
				request.setAttribute("fail", "用户名或密码有误，请重新核对");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			} else {
				session.setAttribute("user", user);
				response.sendRedirect("ShowAllServlet");
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
