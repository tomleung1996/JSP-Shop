package cn.tomleung.dao;

import java.util.ArrayList;

import cn.tomleung.entity.Good;

public interface GoodDAO {
	public void insert(Good good) throws Exception;
	public void update(Good good) throws Exception;
	public void delete(Good good) throws Exception;
	public ArrayList<Good> queryByName(String gname) throws Exception;
	public Good queryByID(int gid) throws Exception;
	public ArrayList<Good> queryAll() throws Exception;
}
