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
import cn.tomleung.entity.User;

/**
 * Servlet implementation class GoodDetailServlet
 */
@WebServlet(description = "չʾ��Ʒ����", urlPatterns = { "/GoodDetailServlet" })
public class GoodDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		if (session.getAttribute("user") == null || ((User) session.getAttribute("user")).getUsername() == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
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