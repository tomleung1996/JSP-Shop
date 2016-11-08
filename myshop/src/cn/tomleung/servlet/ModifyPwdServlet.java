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
import cn.tomleung.tool.MD5;

/**
 * Servlet implementation class ModifyPwdServlet
 */
@WebServlet(description = "�ṩ�����޸ķ���", urlPatterns = { "/ModifyPwdServlet" })
public class ModifyPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		String username = ((User) session.getAttribute("user")).getUsername();
		String password = request.getParameter("password"); //��ȡԭ����
		String password2 = request.getParameter("password2"); //��ȡ������
		String password3 = request.getParameter("password3"); //��ȡ�������ȷ������
		UserDAO userDAO = DAOFactory.getUserDAOInstance();
		User user = new User();
		InputVerify verify = new InputVerify();
		
		user.setUsername(username);
		user.setPassword(password3);
		
		try {
			if(!MD5.Bit32(password).equalsIgnoreCase(userDAO.queryByName(username).getPassword())){
				request.setAttribute("fail", "ԭ���벻��ȷ�������¼��");
				request.getRequestDispatcher("modifypwd.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!verify.PasswordLengthVerify(user)){
			request.setAttribute("fail", "�����볤������Ϊ8λ");
			request.getRequestDispatcher("modifypwd.jsp").forward(request, response);
			return;
		}
		
		if(!password2.equals(password3)){
			request.setAttribute("fail", "��������ȷ�����벻һ��");
			request.getRequestDispatcher("modifypwd.jsp").forward(request, response);
			return;
		}
		
		try {
			userDAO.updatePwd(user);
			user = userDAO.queryByName(username);
			session.setAttribute("user", user);
			response.sendRedirect("profile.jsp");
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
