package cn.tomleung.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tomleung.dao.DAOFactory;
import cn.tomleung.dao.GoodDAO;
import cn.tomleung.dao.OrderDAO;
import cn.tomleung.entity.Order;
import cn.tomleung.entity.OrderHead;
import cn.tomleung.entity.User;

/**
 * Servlet implementation class OrderShowServlet
 */
@WebServlet("/OrderShowServlet")
public class OrderShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		int uid = ((User)session.getAttribute("user")).getUid();
		OrderDAO orderDAO = DAOFactory.getOrderDAOInstance();
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
		try {
			ArrayList<OrderHead> orders = orderDAO.queryByUID(uid);
			for(OrderHead o:orders){
				double sum=0;
				for(Order o2:o.getOrder()){
					o2.setGood(goodDAO.queryByID(o2.getGid()));
					o2.setSubsum(o2.getGood().getGprice()*o2.getQty());
					sum+=o2.getSubsum();
				}
				o.setSum(sum);
			}
			Collections.sort(orders);
			session.setAttribute("orders", orders);
			response.sendRedirect("order.jsp");
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
