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
import cn.tomleung.entity.Cart;
import cn.tomleung.entity.User;

/**
 * Servlet implementation class CartAddServlet
 */
@WebServlet(description = "在购物车中增加一个商品的数量", urlPatterns = { "/CartAddServlet" })
public class CartAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		int gid = Integer.parseInt(request.getParameter("gid"));
		int uid = ((User)session.getAttribute("user")).getUid();
		Cart cart = new Cart();
		cart.setUid(uid);
		cart.setGid(gid);
		CartDAO cartDAO = DAOFactory.getCartDAOInstance();
		try {
			cart=cartDAO.queryByUGID(uid, gid);
			cart.setQty(cart.getQty()+1);
			cartDAO.update(cart);
			response.sendRedirect("CartShowServlet");
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("fail", "增加失败");
			request.getRequestDispatcher("CartShowServlet");
			return;
		}
	}

}
