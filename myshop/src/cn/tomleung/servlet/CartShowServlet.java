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
		ArrayList<Cart> carts = new ArrayList<Cart>();
		CartDAO cartDAO = DAOFactory.getCartDAOInstance();
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
		try {
			ArrayList<Cart> cs = cartDAO.queryByID(uid);
			for(Cart cart:cs){
				Cart singleCart = new Cart();
				Good tmpGood = goodDAO.queryByID(cart.getGid());
				singleCart.setUid(cart.getUid());
				singleCart.setGid(tmpGood.getGid());
				singleCart.setGname(tmpGood.getGname());
				singleCart.setGprice(tmpGood.getGprice());
				singleCart.setQty(cart.getQty());
				singleCart.setSubsum(singleCart.getGprice()*singleCart.getQty());
				carts.add(singleCart);
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
