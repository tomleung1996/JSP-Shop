package cn.tomleung.dao;

import cn.tomleung.entity.User;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		UserDAO userDAO = DAOFactory.getUserDAOInstance();
		user=userDAO.queryByName("tomleung");
		user.setUsername("bbb");
		userDAO.insert(user);
	}

}
