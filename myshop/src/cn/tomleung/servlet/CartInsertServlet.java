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
 * Servlet implementation class CartInsertServlet
 */
@WebServlet(description = "添加商品到购物车", urlPatterns = { "/CartInsertServlet" })
public class CartInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		int gid = Integer.parseInt(request.getParameter("gid"));
		int uid = ((User)session.getAttribute("user")).getUid();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		Cart cart = new Cart();
		cart.setUid(uid);
		cart.setGid(gid);
		cart.setQty(1);
		CartDAO cartDAO = DAOFactory.getCartDAOInstance();
		try {
			Cart tmpCart=cartDAO.queryByUGID(uid, gid);
			if(tmpCart==null){
				cartDAO.insert(cart);
				request.setAttribute("success", "加入购物车成功！");
				request.getRequestDispatcher("ShowAllServlet?currentPage="+currentPage).forward(request, response);
				return;
			}else{
				cart.setQty(tmpCart.getQty()+1);
				cartDAO.update(cart);
				request.setAttribute("success", "加入购物车成功！");
				request.getRequestDispatcher("ShowAllServlet?currentPage="+currentPage).forward(request, response);
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("fail", "加入购物车失败！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
	}

}
