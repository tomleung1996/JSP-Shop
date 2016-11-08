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
//import cn.tomleung.entity.User;

/**
 * Servlet implementation class ShowAllServlet
 */
@WebServlet(description = "展示全部商品（并跳转至首页）", urlPatterns = { "/ShowAllServlet" })
public class ShowAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
		HttpSession session = request.getSession(true);
		String flag = request.getParameter("flag");
		try {
			ArrayList<Good> all = goodDAO.queryAll();
			session.setAttribute("all", all);
			if(flag!=null){
				response.sendRedirect("goodmanage.jsp");
				return;
			}
			response.sendRedirect("index.jsp");
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
