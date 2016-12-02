package cn.tomleung.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderHead implements Comparable<OrderHead>{
	private int oid;
	private Date otime;
	private double sum;
	private ArrayList<Order> order;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getOtime() {
		DateFormat  df  = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
		return df.format(otime);
	}
	public void setOtime(Date otime) {
		this.otime = otime;
	}
	public ArrayList<Order> getOrder() {
		return order;
	}
	public void setOrder(ArrayList<Order> order) {
		this.order = order;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	@Override
	public int compareTo(OrderHead other) {
		// TODO Auto-generated method stub
		if(this.oid>other.oid)
			return -1;
		else if(this.oid<other.oid)
			return 1;
		else
			return 0;
	}
	
	
}
