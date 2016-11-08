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
			sql = "INSERT INTO users(username,password) VALUES(?,md5(?))";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();

			sql = "INSERT INTO users_info(username,gender,age,email) VALUES(?,?,?,?)";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getGender());
			pstmt.setString(3, user.getAge());
			pstmt.setString(4, user.getEmail());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("����ʧ��");
		} finally {
			dbc.close();
		}
	}

	@Override
	public void updateInfo(User user) throws Exception {
		// TODO Auto-generated method stub
		try {
			dbc = new DBConnection();
			sql = "UPDATE users_info SET gender=?,age=?,email=? WHERE username=?";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, user.getGender());
			pstmt.setString(2, user.getAge());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUsername());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("����ʧ��");
		} finally {
			dbc.close();
		}
	}

	@Override
	public void delete(User user) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("ɾ������δ����");
	}

	@Override
	public User queryByName(String username) throws Exception {
		// TODO Auto-generated method stub
		User user = null;
		try {
			dbc = new DBConnection();
			sql = "SELECT users.*,gender,privilege,age,email " + "FROM users,users_info "
					+ "WHERE users.username=users_info.username " + "AND users.username=?";
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
			throw new Exception("��ѯ���û�ʧ��");
		} finally {
			dbc.close();
		}
		return user;
	}

	@Override
	public List<User> queryAll() throws Exception {
		// TODO Auto-generated method stub
		List<User> all = new ArrayList<User>();
		sql = "SELECT users.*,gender,privilege,age,email " + "FROM users,users_info "
				+ "WHERE users.username=users_info.username";
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
			throw new Exception("��ѯ�����û�ʧ��");
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
			sql = "UPDATE users SET password=md5(?) WHERE username=?";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getUsername());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("����ʧ��");
		} finally {
			dbc.close();
		}
	}

}
