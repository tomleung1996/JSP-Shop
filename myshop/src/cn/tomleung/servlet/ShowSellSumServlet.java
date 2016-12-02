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
import cn.tomleung.dao.GoodDAO;
import cn.tomleung.entity.Good;

/**
 * Servlet implementation class ShowSellSumServlet
 */
@WebServlet("/ShowSellSumServlet")
public class ShowSellSumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int[] goodIDs = null;
		ArrayList<Good> goodList = new ArrayList<Good>();
		Map<String, Integer> sellSum = new LinkedHashMap<String, Integer>();
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
		try {
			goodIDs = goodDAO.queryAllID();
			int j = 0;
			for (int i : goodIDs) {
				goodList.add(goodDAO.queryByID(i));
				int singleSum = goodDAO.querySellSumByID(i);
				sellSum.put(goodList.get(j++).getGname(), singleSum);
			}
			sellSum=sortByValue(sellSum);
			JSONArray gname = new JSONArray(sellSum.keySet());
			JSONArray sell = new JSONArray(sellSum.values());
			Map<String, JSONArray> a = new LinkedHashMap<String, JSONArray>();
			a.put("categories", gname);
			a.put("data", sell);
			JSONObject json = new JSONObject(a);
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}
