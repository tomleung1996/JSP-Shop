package cn.tomleung.servlet;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class ShowAllServlet
 */
@WebServlet(description = "展示全部商品（并跳转至首页）", urlPatterns = { "/ShowAllServlet" })
public class ShowAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
		HttpSession session = request.getSession(true);
		String flag = request.getParameter("flag");

		String currentPageStr = request.getParameter("currentPage");
		int itemPerPage = 8;
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr.trim());
		}

		try {
			int totalGoods = goodDAO.queryAllCount();
			if (totalGoods < itemPerPage) {
				itemPerPage = totalGoods;
			}
			int totalPages = totalGoods / itemPerPage + ((totalGoods % itemPerPage) > 0 ? 1 : 0);
			ArrayList<Good> all = goodDAO.queryLimit((currentPage - 1) * itemPerPage, itemPerPage);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("currentPage", currentPage);
			session.setAttribute("all", all);
			if (flag!=null) {
				request.getRequestDispatcher("goodmanage.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
