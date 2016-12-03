package cn.tomleung.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.tomleung.dbcon.DBConnection;
import cn.tomleung.entity.Good;

public class GoodDAOImpl implements GoodDAO {
	private String sql;
	private PreparedStatement pstmt;
	private DBConnection dbc;
	private ResultSet rs;

	@Override
	public void insert(Good good) throws SQLException {
		// TODO Auto-generated method stub
		try {
			dbc = new DBConnection();
			sql = "INSERT INTO goods(gname,gprice,gpic,gdes,gorigin) values(?,?,?,?,?)";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, good.getGname());
			pstmt.setDouble(2, good.getGprice());
			pstmt.setString(3, good.getGpic());
			pstmt.setString(4, good.getGdes());
			pstmt.setString(5, good.getGorigin());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			dbc.close();
		}
	}

	@Override
	public void update(Good good) throws Exception {
		// TODO Auto-generated method stub
		try {
			dbc = new DBConnection();

			sql = "UPDATE goods SET gname=?,gprice=?,gpic=?,gdes=?,gorigin=? WHERE gid=?";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, good.getGname());
			pstmt.setDouble(2, good.getGprice());
			pstmt.setString(3, good.getGpic());
			pstmt.setString(4, good.getGdes());
			pstmt.setString(5, good.getGorigin());
			pstmt.setInt(6, good.getGid());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("更新失败");
		} finally {
			dbc.close();
		}
	}

	@Override
	public void delete(Good good) throws Exception {
		// TODO Auto-generated method stub
		try {
			dbc = new DBConnection();
			sql = "DELETE FROM goods WHERE gid=?";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, good.getGid());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("删除失败");
		} finally {
			dbc.close();
		}
	}

	@Override
	public ArrayList<Good> queryByName(String gname) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Good> search = new ArrayList<Good>();
		sql = "SELECT * FROM goods WHERE gname LIKE ?";
		Good good = null;
		try {
			dbc = new DBConnection();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, "%" + gname + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				good = new Good();
				good.setGid(rs.getInt("gid"));
				good.setGname(rs.getString("gname"));
				good.setGprice(rs.getDouble("gprice"));
				good.setGpic(rs.getString("gpic"));
				good.setGdes(rs.getString("gdes"));
				good.setGorigin(rs.getString("gorigin"));
				search.add(good);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("搜索商品失败");
		} finally {
			dbc.close();
		}
		return search;
	}

	@Override
	public ArrayList<Good> queryAll() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Good> all = new ArrayList<Good>();
		sql = "SELECT * FROM goods";
		Good good = null;
		try {
			dbc = new DBConnection();
			pstmt = dbc.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				good = new Good();
				good.setGid(rs.getInt("gid"));
				good.setGname(rs.getString("gname"));
				good.setGprice(rs.getDouble("gprice"));
				good.setGpic(rs.getString("gpic"));
				good.setGdes(rs.getString("gdes"));
				good.setGorigin(rs.getString("gorigin"));
				all.add(good);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("查询所有商品失败");
		} finally {
			dbc.close();
		}
		return all;
	}

	@Override
	public Good queryByID(int gid) throws Exception {
		// TODO Auto-generated method stub
		Good good = null;
		try {
			dbc = new DBConnection();
			sql = "SELECT * FROM goods WHERE gid=?";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, gid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				good = new Good();
				good.setGid(rs.getInt("gid"));
				good.setGname(rs.getString("gname"));
				good.setGprice(rs.getDouble("gprice"));
				good.setGpic(rs.getString("gpic"));
				good.setGdes(rs.getString("gdes"));
				good.setGorigin(rs.getString("gorigin"));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("查询单个商品失败");
		} finally {
			dbc.close();
		}
		return good;
	}

	@Override
	public ArrayList<Good> queryLimit(int from, int limit) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Good> all = new ArrayList<Good>();
		sql = "SELECT * FROM goods LIMIT ?,?";
		Good good = null;
		try {
			dbc = new DBConnection();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, from);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				good = new Good();
				good.setGid(rs.getInt("gid"));
				good.setGname(rs.getString("gname"));
				good.setGprice(rs.getDouble("gprice"));
				good.setGpic(rs.getString("gpic"));
				good.setGdes(rs.getString("gdes"));
				good.setGorigin(rs.getString("gorigin"));
				all.add(good);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("查询限定商品失败");
		} finally {
			dbc.close();
		}
		return all;
	}

	@Override
	public int queryAllCount() throws SQLException {
		// TODO Auto-generated method stub
		try {
			dbc = new DBConnection();
			sql = "SELECT COUNT(*) FROM goods";
			pstmt = dbc.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			dbc.close();
		}
		return 0;
	}

	@Override
	public ArrayList<Good> queryByNameLimit(String gname, int from, int limit) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Good> search = new ArrayList<Good>();
		sql = "SELECT * FROM goods WHERE gname LIKE ? LIMIT ?,?";
		Good good = null;
		try {
			dbc = new DBConnection();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, "%" + gname + "%");
			pstmt.setInt(2, from);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				good = new Good();
				good.setGid(rs.getInt("gid"));
				good.setGname(rs.getString("gname"));
				good.setGprice(rs.getDouble("gprice"));
				good.setGpic(rs.getString("gpic"));
				good.setGdes(rs.getString("gdes"));
				good.setGorigin(rs.getString("gorigin"));
				search.add(good);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new Exception("搜索分页商品失败");
		} finally {
			dbc.close();
		}
		return search;
	}

	@Override
	public int[] queryAllID() throws Exception {
		// TODO Auto-generated method stub
		int[] allID = null;
		try {
			dbc = new DBConnection();
			sql = "SELECT DISTINCT gid FROM goods";
			pstmt = dbc.getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE);
			rs = pstmt.executeQuery();
			rs.last();
			allID = new int[rs.getRow()];
			rs.beforeFirst();
			while (rs.next())
				allID[rs.getRow() - 1] = rs.getInt(1);
		} catch (Exception e) {
			throw new Exception("查询全部商品ID失败");
		} finally {
			dbc.close();
		}
		return allID;
	}

	@Override
	public int querySellSumByID(int gid) throws Exception {
		// TODO Auto-generated method stub
		int sellSum = 0;
		try {
			dbc = new DBConnection();
			sql = "SELECT SUM(qty) FROM order_details WHERE gid=?";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, gid);
			rs = pstmt.executeQuery();
			rs.next();
			sellSum = rs.getInt(1);
		} catch (Exception e) {
			throw new Exception("查询商品销量失败");
		} finally {
			dbc.close();
		}
		return sellSum;
	}

}
