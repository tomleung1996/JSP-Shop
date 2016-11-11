package cn.tomleung.dao;


import cn.tomleung.entity.Good;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
		Good good = new Good();
//		good.setGid(41);
		good.setGname("≤‚ ‘");
//		good.setGprice(999);
//		good.setGpic("none pic");
//		good.setGdes("oasdjoijjoiwjego");
//		good.setGorigin("GZ");
		System.out.println(goodDAO.queryByName("≤‚").get(0).getGdes());
	}

}
