package cn.tomleung.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tomleung.dao.DAOFactory;
import cn.tomleung.dao.GoodDAO;
import cn.tomleung.entity.Good;

/**
 * Servlet implementation class GoodDetailServlet
 */
@WebServlet(description = "展示商品详情", urlPatterns = { "/GoodDetailServlet" })
public class GoodDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int gid = Integer.parseInt(request.getParameter("gid"));
		Good good = null;
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
		try {
			good=goodDAO.queryByID(gid);
			request.setAttribute("good", good);
			request.getRequestDispatcher("gooddetail.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
