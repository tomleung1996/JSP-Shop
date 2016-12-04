package cn.tomleung.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.tomleung.dao.DAOFactory;
import cn.tomleung.dao.UserDAO;
import cn.tomleung.entity.User;

/**
 * Servlet implementation class ShowBuySumServlet
 */
@WebServlet("/ShowBuySumServlet")
public class ShowBuySumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int[] userIDs = null;
		ArrayList<User> userList = new ArrayList<User>();
		Map<String, Integer> buySum = new LinkedHashMap<String, Integer>();
		UserDAO userDAO = DAOFactory.getUserDAOInstance();
		try {
			userIDs = userDAO.queryAllID();
			int j = 0;
			for (int i : userIDs) {
				userList.add(userDAO.queryByID(i));
				int singleSum = userDAO.queryBuySumByID(i);
				buySum.put(userList.get(j++).getUsername(), singleSum);
			}
			buySum=sortByValue(buySum);
			JSONArray uname = new JSONArray(buySum.keySet());
			JSONArray buy = new JSONArray(buySum.values());
			Map<String, JSONArray> a = new LinkedHashMap<String, JSONArray>();
			a.put("categories", uname);
			a.put("data", buy);
			JSONObject json = new JSONObject(a);
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}
