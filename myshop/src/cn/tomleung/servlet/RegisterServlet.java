package cn.tomleung.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import cn.tomleung.dao.DAOFactory;
import cn.tomleung.dao.UserDAO;
import cn.tomleung.entity.User;
import cn.tomleung.tool.InputVerify;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "处理注册", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
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
		String password2 = request.getParameter("password2");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		UserDAO userDAO = DAOFactory.getUserDAOInstance();
		User user = new User();
		InputVerify verify = new InputVerify();
//		HttpSession session=request.getSession(true);

		user.setUsername(username);
		user.setPassword(password);
		user.setPassword2(password2);
		user.setGender(gender);
		user.setAge(age);
		user.setEmail(email);

		// 检查用户名是否重复
		try {
			if (userDAO.queryByName(user.getUsername()) != null) {
				request.setAttribute("fail", "用户名已被占用");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 判断密码位数是否足够，否则返回register.jsp
		if (!verify.PasswordLengthVerify(user)) {
			request.setAttribute("fail", "密码长度少于8位");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		// 判断两个密码是否相等，否则返回register.jsp
		if (!verify.PasswordEqualVerify(user)) {
			request.setAttribute("fail", "两次密码输入不一致");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		// 判断年龄是否为数字，否则返回register.jsp
		if (!verify.AgeNumericVerify(user)) {
			request.setAttribute("fail", "请输入正确的年龄");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		// 判断email是否正确，否则返回register.jsp
		if (!verify.EmailVerify(user)) {
			request.setAttribute("fail", "邮箱格式不正确");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		// 通过全部检查后写入数据库

		try {
			userDAO.insert(user);
			response.sendRedirect("login.jsp?success=1");
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
