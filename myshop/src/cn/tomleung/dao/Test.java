package cn.tomleung.dao;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UserDAO userDAO = DAOFactory.getUserDAOInstance();
		System.out.println(userDAO.queryBuySumByID(1));
	}
}
