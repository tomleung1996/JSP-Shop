package cn.tomleung.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tomleung.dao.CartDAO;
import cn.tomleung.dao.DAOFactory;
import cn.tomleung.dao.OrderDAO;
import cn.tomleung.entity.Cart;
import cn.tomleung.entity.User;

/**
 * Servlet implementation class OrderCreateServlet
 */
@WebServlet("/OrderCreateServlet")
public class OrderCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		int uid = ((User)session.getAttribute("user")).getUid();
		CartDAO cartDAO = DAOFactory.getCartDAOInstance();
		@SuppressWarnings("unchecked")
		ArrayList<Cart> carts = (ArrayList<Cart>)session.getAttribute("carts");
		OrderDAO orderDAO = DAOFactory.getOrderDAOInstance();
		try {
			orderDAO.insert(carts);
			cartDAO.truncate(uid);
			session.setAttribute("carts", null);
			response.sendRedirect("OrderShowServlet");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
