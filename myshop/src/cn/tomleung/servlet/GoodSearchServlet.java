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
 * Servlet implementation class GoodSearchServlet
 */
@WebServlet("/GoodSearchServlet")
public class GoodSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		String gname = request.getParameter("gname");
		
		if(gname!=null){
			gname = gname.trim();
			session.setAttribute("gname", gname);
		}else{
			gname=(String)session.getAttribute("gname");
		}
		
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
		

		String currentPageStr = request.getParameter("currentPage");
		int itemPerPage = 8;
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr.trim());
		}

		try {
			int totalGoods = goodDAO.queryByName(gname).size();
			if (totalGoods == 0) {
				request.setAttribute("fail", "没有找到任何结果");
				session.setAttribute("all", null);
				request.getRequestDispatcher("search.jsp").forward(request, response);
				return;
			}
			if (totalGoods < itemPerPage) {
				itemPerPage = totalGoods;
			}
			int totalPages = totalGoods / itemPerPage + ((totalGoods % itemPerPage) > 0 ? 1 : 0);
			ArrayList<Good> all = goodDAO.queryByNameLimit(gname, (currentPage - 1) * itemPerPage, itemPerPage);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("currentPage", currentPage);
			session.setAttribute("all", all);
			request.getRequestDispatcher("search.jsp").forward(request, response);
			return;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try {
		// ArrayList<Good> all = goodDAO.queryByName(gname);
		// if(all.size()==0){
		// request.setAttribute("fail", "未搜索到任何内容");
		// session.setAttribute("all", all);
		// request.getRequestDispatcher("index.jsp").forward(request, response);
		// return;
		// }
		// session.setAttribute("all", all);
		// response.sendRedirect("ShowAllServlet?search=1");
		// return;
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
