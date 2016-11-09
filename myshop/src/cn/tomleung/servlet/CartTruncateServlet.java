package cn.tomleung.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tomleung.dao.CartDAO;
import cn.tomleung.dao.DAOFactory;
import cn.tomleung.entity.User;

/**
 * Servlet implementation class CartTruncateServlet
 */
@WebServlet(description = "清空购物车", urlPatterns = { "/CartTruncateServlet" })
public class CartTruncateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		int uid = ((User)session.getAttribute("user")).getUid();
		CartDAO cartDAO = DAOFactory.getCartDAOInstance();
		try {
			cartDAO.truncate(uid);
			session.setAttribute("carts", null);
			response.sendRedirect("CartShowServlet");
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("fail", "清空购物车失败");
			request.getRequestDispatcher("CartShowServlet").forward(request, response);
			return;
		}
	}

}
