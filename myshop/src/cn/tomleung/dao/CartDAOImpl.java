package cn.tomleung.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.tomleung.dbcon.DBConnection;
import cn.tomleung.entity.Cart;

public class CartDAOImpl implements CartDAO {
	private String sql;
	private PreparedStatement pstmt;
	private DBConnection dbc;
	private ResultSet rs;

	@Override
	public void insert(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		try {
			dbc = new DBConnection();
			sql = "INSERT INTO carts(uid,gid,qty) values(?,?,?)";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, cart.getUid());
			pstmt.setInt(2, cart.getGid());
			pstmt.setInt(3, cart.getQty());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception("插入失败");
		} finally {
			dbc.close();
		}
	}

	@Override
	public void update(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		try {
			dbc = new DBConnection();
			sql = "UPDATE carts SET qty=? WHERE uid=? AND gid=?";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, cart.getQty());
			pstmt.setInt(2, cart.getUid());
			pstmt.setInt(3, cart.getGid());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("更新失败");
		} finally {
			dbc.close();
		}
	}

	@Override
	public void delete(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		try {
			dbc = new DBConnection();
			sql = "DELETE FROM carts WHERE uid=? AND gid=?";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, cart.getUid());
			pstmt.setInt(2, cart.getGid());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("删除失败");
		} finally {
			dbc.close();
		}
	}

	@Override
	public ArrayList<Cart> queryByID(int uid) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Cart> carts = new ArrayList<Cart>();
		sql = "SELECT * FROM carts WHERE uid=?";
		Cart cart = null;
		try {
			dbc = new DBConnection();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cart = new Cart();
				cart.setUid(rs.getInt("uid"));
				cart.setGid(rs.getInt("gid"));
				cart.setQty(rs.getInt("qty"));
				carts.add(cart);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("获取购物车失败");
		} finally {
			dbc.close();
		}
		return carts;
	}

	@Override
	public ArrayList<Cart> queryAll() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("方法未定义");
	}

	@Override
	public Cart queryByUGID(int uid, int gid) throws Exception {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM carts WHERE uid=? AND gid=?";
		Cart cart = null;
		try {
			dbc = new DBConnection();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, gid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cart = new Cart();
				cart.setUid(rs.getInt("uid"));
				cart.setGid(rs.getInt("gid"));
				cart.setQty(rs.getInt("qty"));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("获取购物车失败");
		} finally {
			dbc.close();
		}
		return cart;
	}

}
