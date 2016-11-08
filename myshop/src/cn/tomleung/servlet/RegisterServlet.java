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
@WebServlet(description = "����ע��", urlPatterns = { "/RegisterServlet" })
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

		// ����û����Ƿ��ظ�
		try {
			if (userDAO.queryByName(user.getUsername()) != null) {
				request.setAttribute("fail", "�û����ѱ�ռ��");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �ж�����λ���Ƿ��㹻�����򷵻�register.jsp
		if (!verify.PasswordLengthVerify(user)) {
			request.setAttribute("fail", "���볤������8λ");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		// �ж����������Ƿ���ȣ����򷵻�register.jsp
		if (!verify.PasswordEqualVerify(user)) {
			request.setAttribute("fail", "�����������벻һ��");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		// �ж������Ƿ�Ϊ���֣����򷵻�register.jsp
		if (!verify.AgeNumericVerify(user)) {
			request.setAttribute("fail", "��������ȷ������");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		// �ж�email�Ƿ���ȷ�����򷵻�register.jsp
		if (!verify.EmailVerify(user)) {
			request.setAttribute("fail", "�����ʽ����ȷ");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		// ͨ��ȫ������д�����ݿ�

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
