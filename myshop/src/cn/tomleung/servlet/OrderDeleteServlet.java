package cn.tomleung.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tomleung.dao.DAOFactory;
import cn.tomleung.dao.OrderDAO;

/**
 * Servlet implementation class OrderDeleteServlet
 */
@WebServlet("/OrderDeleteServlet")
public class OrderDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int oid = Integer.parseInt(request.getParameter("oid"));
		OrderDAO orderDAO = DAOFactory.getOrderDAOInstance();
		try {
			orderDAO.delete(oid);
			response.sendRedirect("OrderShowServlet");
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
