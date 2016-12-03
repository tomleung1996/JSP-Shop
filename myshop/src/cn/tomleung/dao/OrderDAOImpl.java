package cn.tomleung.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.tomleung.dbcon.DBConnection;
import cn.tomleung.entity.Cart;
import cn.tomleung.entity.Order;
import cn.tomleung.entity.OrderHead;

public class OrderDAOImpl implements OrderDAO {
	private String sql;
	private PreparedStatement pstmt;
	private DBConnection dbc;
	private ResultSet rs;

	@Override
	public void insert(ArrayList<Cart> carts) throws SQLException {
		// TODO Auto-generated method stub
		try {
			dbc = new DBConnection();
			sql = "INSERT INTO orders(uid) VALUES(?)";
			pstmt = dbc.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, carts.get(0).getUid());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			rs.next();
			int oid = rs.getInt(1);
			for (Cart c : carts) {
				sql = "INSERT INTO order_details(oid,gid,qty) VALUES(?,?,?)";
				pstmt = dbc.getConnection().prepareStatement(sql);
				pstmt.setInt(1, oid);
				pstmt.setInt(2, c.getGid());
				pstmt.setInt(3, c.getQty());
				pstmt.executeUpdate();
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
	}

	@Override
	public void delete(int oid) throws SQLException {
		// TODO Auto-generated method stub
		try{
			dbc=new DBConnection();
			sql="UPDATE orders SET odel=1 WHERE oid=?";
			pstmt=dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, oid);
			pstmt.executeUpdate();
//			sql="DELETE FROM orders WHERE oid=?";
//			pstmt=dbc.getConnection().prepareStatement(sql);
//			pstmt.setInt(1, oid);
//			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e){
			throw e;
		}finally{
			dbc.close();
		}
	}

//	@Override
//	public ArrayList<Order> queryByUID(int uid) throws Exception {
//		// TODO Auto-generated method stub
//		ArrayList<Order> orders = new ArrayList<Order>();
//		Order order = null;
//		try {
//			dbc = new DBConnection();
//			sql = "SELECT * FROM orders_view WHERE uid=?";
//			pstmt = dbc.getConnection().prepareStatement(sql);
//			pstmt.setInt(1, uid);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				order = new Order();
//				order.setOid(rs.getInt("oid"));
//				order.setUid(rs.getInt("uid"));
//				order.setOtime(rs.getTimestamp("otime"));
//				order.setGid(rs.getInt("gid"));
//				order.setQty(rs.getInt("qty"));
//				orders.add(order);
//			}
//			rs.close();
//			pstmt.close();
//		} catch (Exception e) {
//			throw new Exception("≤È—Ø ß∞‹");
//		} finally {
//			dbc.close();
//		}
//		return orders;
//	}
	
	@Override
	public ArrayList<OrderHead> queryByUID(int uid) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<OrderHead> orders = new ArrayList<OrderHead>();
		ArrayList<Order> inner=null;
		Order order = null;
		try {
			dbc = new DBConnection();
			sql = "SELECT * FROM orders_view WHERE uid=? AND odel=0";
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();
			int oid=0;
			int tmpoid=0;
			int count=-1;
			while (rs.next()) {
				oid=rs.getInt("oid");
				if(tmpoid!=oid){
					orders.add(new OrderHead());
					count++;
					orders.get(count).setOid(oid);
					orders.get(count).setOtime(rs.getTimestamp("otime"));
					inner= new ArrayList<Order>();
					orders.get(count).setOrder(inner);
					tmpoid=oid;
				}
				order = new Order();
				order.setGid(rs.getInt("gid"));
				order.setQty(rs.getInt("qty"));
				orders.get(count).getOrder().add(order);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
//			throw new Exception("≤È—Ø ß∞‹");
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return orders;
	}

	@Override
	public int queryOrderNumberByUID(int uid) throws Exception {
		// TODO Auto-generated method stub
		int count=0;
		try{
			dbc=new DBConnection();
			sql="SELECT COUNT(DISTINCT oid) FROM orders_view WHERE uid=?";
			pstmt=dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, uid);
			rs=pstmt.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
			pstmt.close();
		}catch(Exception e){
			throw new Exception("≤È—Ø∂©µ• ˝¡ø ß∞‹");
		}finally{
			dbc.close();
		}
		return count;
	}

}
