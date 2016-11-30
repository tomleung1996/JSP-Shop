package cn.tomleung.dao;

import java.util.ArrayList;

import cn.tomleung.entity.Cart;
import cn.tomleung.entity.OrderHead;

public interface OrderDAO {
	public void insert(ArrayList<Cart> carts) throws Exception;
	public void delete(int oid) throws Exception;
	public ArrayList<OrderHead> queryByUID(int uid) throws Exception;
	public int queryOrderNumberByUID(int uid) throws Exception;
}
