package cn.tomleung.dao;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int[] goodIDs = null;
//		ArrayList<Good> goodList = new ArrayList<Good>();
//		Map<String, Integer> sellSum = new LinkedHashMap<String, Integer>();
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
//		try {
			goodIDs = goodDAO.queryAllID();
			for(int i:goodIDs)
				System.out.println(i);
//			int j = 0;
//			for (int i : goodIDs) {
//				goodList.add(goodDAO.queryByID(i));
//				int singleSum = goodDAO.querySellSumByID(i);
//				sellSum.put(goodList.get(j++).getGname(), singleSum);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		JSONArray gname = new JSONArray(sellSum.keySet());
//		JSONArray sell = new JSONArray(sellSum.values());
//		Map<String,JSONArray> a = new LinkedHashMap<String,JSONArray>();
//		a.put("categories", gname);
//		a.put("data", sell);
//		JSONObject json = new JSONObject(a);
////		System.out.println(gname);
//		System.out.println(json.toString());
	}
}
