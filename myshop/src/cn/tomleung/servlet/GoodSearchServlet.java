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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String gname = request.getParameter("gname");
		gname=gname.trim();
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
		HttpSession session = request.getSession(true);
		
		try {
			ArrayList<Good> all = goodDAO.queryByName(gname);
			if(all.size()==0){
				request.setAttribute("fail", "未搜索到任何内容");
				session.setAttribute("all", all);
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("all", all);
			response.sendRedirect("index.jsp");
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
