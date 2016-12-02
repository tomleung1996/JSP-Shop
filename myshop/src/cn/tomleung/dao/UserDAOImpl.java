package cn.tomleung.dao;

import java.sql.*;
import java.util.*;
import cn.tomleung.dbcon.DBConnection;
import cn.tomleung.entity.User;

public class UserDAOImpl implements UserDAO {
	private String sql;
	private PreparedStatement pstmt;
	private DBConnection dbc;
	private ResultSet rs;

	@Override
	public void insert(User user) throws Exception {
		// TODO Auto-generated method stub
		try {
			dbc = new DBConnection();
			sql = "INSERT INTO users(username,password,gender,age,email) VALUES(?,md5(?),?,?,?)";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getGender());
			pstmt.setString(4, user.getAge());
			pstmt.setString(5, user.getEmail());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("插入失败");
		} finally {
			dbc.close();
		}
	}

	@Override
	public void updateInfo(User user) throws Exception {
		// TODO Auto-generated method stub
		try {
			dbc = new DBConnection();
			sql = "UPDATE users SET gender=?,age=?,email=? WHERE uid=?";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, user.getGender());
			pstmt.setString(2, user.getAge());
			pstmt.setString(3, user.getEmail());
			pstmt.setInt(4, user.getUid());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("更新失败");
		} finally {
			dbc.close();
		}
	}

	@Override
	public void delete(User user) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("删除方法未定义");
	}

	@Override
	public User queryByName(String username) throws Exception {
		// TODO Auto-generated method stub
		User user = null;
		try {
			dbc = new DBConnection();
			sql = "SELECT * FROM users WHERE BINARY username=?";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
				user.setPrivilege(rs.getString("privilege"));
				user.setAge(rs.getString("age"));
				user.setEmail(rs.getString("email"));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("查询单用户失败");
		} finally {
			dbc.close();
		}
		return user;
	}

	@Override
	public List<User> queryAll() throws Exception {
		// TODO Auto-generated method stub
		List<User> all = new ArrayList<User>();
		sql = "SELECT * FROM users";
		User user = null;
		try {
			dbc = new DBConnection();
			pstmt = dbc.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
				user.setPrivilege(rs.getString("privilege"));
				user.setAge(rs.getString("age"));
				user.setEmail(rs.getString("email"));
				all.add(user);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("查询所有用户失败");
		} finally {
			dbc.close();
		}
		return all;
	}

	@Override
	public void updatePwd(User user) throws Exception {
		// TODO Auto-generated method stub
		try {
			dbc = new DBConnection();
			sql = "UPDATE users SET password=md5(?) WHERE uid=?";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setInt(2, user.getUid());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("更新失败");
		} finally {
			dbc.close();
		}
	}

	@Override
	public User queryByID(int uid) throws Exception {
		// TODO Auto-generated method stub
		User user = null;
		try {
			dbc = new DBConnection();
			sql = "SELECT * FROM users WHERE uid=?";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
				user.setPrivilege(rs.getString("privilege"));
				user.setAge(rs.getString("age"));
				user.setEmail(rs.getString("email"));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("查询单用户失败");
		} finally {
			dbc.close();
		}
		return user;
	}

}
