package cn.tomleung.dao;

import java.util.ArrayList;

import cn.tomleung.entity.Cart;

public interface CartDAO {
	public void insert(Cart cart) throws Exception;
	public void update(Cart cart) throws Exception;
	public void delete(Cart cart) throws Exception;
	public ArrayList<Cart> queryByID(int uid) throws Exception;
	public Cart queryByUGID(int uid,int gid) throws Exception;
	public ArrayList<Cart> queryAll() throws Exception;
}
