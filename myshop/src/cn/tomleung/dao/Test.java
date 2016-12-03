package cn.tomleung.dao;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int[] goodIDs = null;
		OrderDAO orderDAO = DAOFactory.getOrderDAOInstance();
		orderDAO.queryByUID(2);
	}
}
