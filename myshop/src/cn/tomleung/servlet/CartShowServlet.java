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
import cn.tomleung.dao.GoodDAO;
import cn.tomleung.entity.Cart;
import cn.tomleung.entity.Good;
import cn.tomleung.entity.User;
import cn.tomleung.entity.VisualCart;

/**
 * Servlet implementation class CartShowServlet
 */
@WebServlet(description = "œ‘ æπ∫ŒÔ≥µ", urlPatterns = { "/CartShowServlet" })
public class CartShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		int uid = ((User)session.getAttribute("user")).getUid();
		ArrayList<VisualCart> carts = new ArrayList<VisualCart>();
		CartDAO cartDAO = DAOFactory.getCartDAOInstance();
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
		try {
			ArrayList<Cart> cs = cartDAO.queryByID(uid);
			for(Cart cart:cs){
				VisualCart visualCart = new VisualCart();
				Good tmpGood = goodDAO.queryByID(cart.getGid());
				visualCart.setGname(tmpGood.getGname());
				visualCart.setGprice(tmpGood.getGprice());
				visualCart.setQty(cart.getQty());
				visualCart.setSubsum(visualCart.getGprice()*visualCart.getQty());
				carts.add(visualCart);
			}
			session.setAttribute("carts", carts);
			response.sendRedirect("cart.jsp");
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
