package cn.tomleung.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
	private int oid;
	private int uid;
	private Date otime;
	private int gid;
	private Good good;
	private int qty;
	private double subsum;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getOtime() {
		DateFormat  df  = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
		return df.format(otime);
	}
	public void setOtime(Date otime) {
		this.otime = otime;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getSubsum() {
		return subsum;
	}
	public void setSubsum(double subsum) {
		this.subsum = subsum;
	}
}
