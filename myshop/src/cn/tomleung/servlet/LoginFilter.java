package cn.tomleung.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tomleung.entity.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("*.jsp")
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse response = (HttpServletResponse) sresponse;
		HttpSession session = request.getSession(true);
		String target = request.getRequestURI();
		String regex1 = "login\\.jsp|register\\.jsp|LoginServlet|RegisterServlet"; // 过滤未登录用户
		String regex2 = "goodmanage\\.jsp|goodupdate\\.jsp|goodinsert\\.jsp"; // 过滤非超级管理员用户
		Pattern p1 = Pattern.compile(regex1);
		Matcher m1 = p1.matcher(target);
		Pattern p2 = Pattern.compile(regex2);
		Matcher m2 = p2.matcher(target);

		if (!(m1.find())) {
			if (session.getAttribute("user") == null || ((User) session.getAttribute("user")).getUsername() == null) {
				response.sendRedirect("login.jsp");
				return;
			} else if (m2.find() && !((User) session.getAttribute("user")).getPrivilege().equals("超级管理员")) {
				request.setAttribute("fail", "对不起，该功能只允许超级管理员使用");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			} else if (target.indexOf("search") > 0) {
				response.sendRedirect("ShowAllServlet?search=1");
			} else if (target.indexOf("index.jsp") > 0) {
				response.sendRedirect("ShowAllServlet");
			}else if (target.indexOf("goodmanage.jsp") > 0) {
				response.sendRedirect("ShowAllServlet?flag=1");
			} else {
				chain.doFilter(request, response);
				return;
			}
		} else {
			chain.doFilter(request, response);
			return;
		}
		// pass the request along the filter chain

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
