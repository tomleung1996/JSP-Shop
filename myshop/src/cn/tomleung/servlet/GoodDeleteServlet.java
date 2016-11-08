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
 * Servlet implementation class GoodDeleteServlet
 */
@WebServlet(description = "提供商品删除功能", urlPatterns = { "/GoodDeleteServlet" })
public class GoodDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int gid = Integer.parseInt(request.getParameter("gid"));
		Good good = new Good();
		good.setGid(gid);
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
		try {
			goodDAO.delete(good);
			response.sendRedirect("ShowAllServlet?flag=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("fail", "删除失败");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
	}

}
