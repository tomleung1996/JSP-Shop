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
@WebServlet(description = "提供密码修改服务", urlPatterns = { "/ModifyPwdServlet" })
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
		String password = request.getParameter("password"); //获取原密码
		String password2 = request.getParameter("password2"); //获取新密码
		String password3 = request.getParameter("password3"); //获取新密码的确认密码
		UserDAO userDAO = DAOFactory.getUserDAOInstance();
		User user = new User();
		InputVerify verify = new InputVerify();
		
		user.setUsername(username);
		user.setPassword(password3);
		
		try {
			if(!MD5.Bit32(password).equalsIgnoreCase(userDAO.queryByName(username).getPassword())){
				request.setAttribute("fail", "原密码不正确，请重新检查");
				request.getRequestDispatcher("modifypwd.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!verify.PasswordLengthVerify(user)){
			request.setAttribute("fail", "新密码长度至少为8位");
			request.getRequestDispatcher("modifypwd.jsp").forward(request, response);
			return;
		}
		
		if(!password2.equals(password3)){
			request.setAttribute("fail", "新密码与确认密码不一致");
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
