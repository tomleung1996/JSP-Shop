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
 * Servlet implementation class CartDeleteServlet
 */
@WebServlet(description = "从购物车移除商品", urlPatterns = { "/CartDeleteServlet" })
public class CartDeleteServlet extends HttpServlet {
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
		cart.setGid(gid);
		cart.setUid(uid);
		CartDAO cartDAO = DAOFactory.getCartDAOInstance();
		try {
			Cart tmpCart=cartDAO.queryByUGID(uid, gid);
			if(tmpCart.getQty()==1){
				cartDAO.delete(cart);
				response.sendRedirect("CartShowServlet");
				return;
			}else{
				cart.setQty(tmpCart.getQty()-1);
				cartDAO.update(cart);
				response.sendRedirect("CartShowServlet");
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("fail", "移除失败");
			request.getRequestDispatcher("CartShowServlet");
			return;
		}
	}

}
