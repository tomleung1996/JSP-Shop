package cn.tomleung.dao;

import java.util.List;

import cn.tomleung.entity.User;

public interface UserDAO {
	public void insert(User user) throws Exception;
	public void updatePwd(User user) throws Exception;
	public void updateInfo(User user) throws Exception;
	public void delete(User user) throws Exception;
	public User queryByName(String username) throws Exception;
	public User queryByID(int uid) throws Exception;
	public List<User> queryAll() throws Exception;
}
