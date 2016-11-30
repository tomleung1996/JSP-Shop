package cn.tomleung.dao;


import java.util.ArrayList;

import cn.tomleung.entity.Order;
import cn.tomleung.entity.OrderHead;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		CartDAO cartDAO = DAOFactory.getCartDAOInstance();
//		ArrayList<Cart> carts = cartDAO.queryByID(1);
		
		OrderDAO orderDAO = DAOFactory.getOrderDAOInstance();
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
//		orderDAO.insert(carts);
//		orderDAO.delete(8);
		ArrayList<OrderHead> orders = orderDAO.queryByUID(1);
		for(OrderHead o:orders){
			for(Order o2:o.getOrder()){
				o2.setGood(goodDAO.queryByID(o2.getGid()));
			}
		}
		for(OrderHead o:orders){
			System.out.println(o.getOid()+" "+o.getOtime());
			for(Order o2:o.getOrder())
				System.out.println("\t"+o2.getGid()+" "+o2.getGood().getGname()+" "+o2.getGood().getGorigin()+" "+o2.getQty());
		}
			
//		System.out.println(orderDAO.queryOrderNumberByUID(3));
	}

}
