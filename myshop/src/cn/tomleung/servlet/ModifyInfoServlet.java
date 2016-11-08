package cn.tomleung.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tomleung.dao.DAOFactory;
import cn.tomleung.dao.UserDAO;
import cn.tomleung.entity.User;
import cn.tomleung.tool.InputVerify;

/**
 * Servlet implementation class ModifyUserServlet
 */
@WebServlet(description = "提供用户信息修改功能", urlPatterns = { "/ModifyInfoServlet" })
public class ModifyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		int uid = ((User) session.getAttribute("user")).getUid();
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		UserDAO userDAO = DAOFactory.getUserDAOInstance();
		User user = new User();
		InputVerify verify = new InputVerify();

		user.setUid(uid);
		user.setGender(gender);
		user.setAge(age);
		user.setEmail(email);

		if (!verify.EmailVerify(user)) {
			request.setAttribute("fail", "邮箱格式不正确，请重新检查");
			request.getRequestDispatcher("modifyinfo.jsp").forward(request, response);
			return;
		}
		if (!verify.AgeNumericVerify(user)) {
			request.setAttribute("fail", "请输入正确的年龄");
			request.getRequestDispatcher("modifyinfo.jsp").forward(request, response);
			return;
		}
		try {
			userDAO.updateInfo(user);
			user = userDAO.queryByID(uid);
			session.setAttribute("user", user);
			response.sendRedirect("profile.jsp");
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
