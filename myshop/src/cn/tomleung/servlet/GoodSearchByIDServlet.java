package cn.tomleung.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tomleung.dao.DAOFactory;
import cn.tomleung.dao.GoodDAO;
import cn.tomleung.entity.Good;

/**
 * Servlet implementation class GoodSearchByIDServlet
 */
@WebServlet("/GoodSearchByIDServlet")
public class GoodSearchByIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int gid = Integer.parseInt(request.getParameter("gid"));
		Good good = null;
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
		HttpSession session = request.getSession(true);

		try {
			good=goodDAO.queryByID(gid);
			session.setAttribute("good", good);
			response.sendRedirect("goodupdate.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
