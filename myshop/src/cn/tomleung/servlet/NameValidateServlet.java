package cn.tomleung.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tomleung.dao.DAOFactory;
import cn.tomleung.dao.UserDAO;

/**
 * Servlet implementation class NameValidateServlet
 */
@WebServlet("/NameValidateServlet")
public class NameValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		boolean flag=true;
		UserDAO userDAO = DAOFactory.getUserDAOInstance();
		String username = request.getParameter("username");
		try {
			if (userDAO.queryByName(username) != null){
				flag=false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag){
			response.getWriter().write("true");
		}else{
			response.getWriter().write("false");
		}
	}

}
