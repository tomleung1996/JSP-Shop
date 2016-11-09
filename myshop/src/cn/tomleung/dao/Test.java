package cn.tomleung.dao;

//import cn.tomleung.entity.Cart;
//import cn.tomleung.entity.User;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		Cart cart = null;
		CartDAO cartDAO =DAOFactory.getCartDAOInstance();
		cartDAO.truncate(1);
	}

}
