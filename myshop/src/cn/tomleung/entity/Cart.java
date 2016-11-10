package cn.tomleung.entity;

public class Cart {
	private int uid;
	private int gid;
	private int qty;
	private String gname;
	private double gprice;
	private double subsum;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public double getGprice() {
		return gprice;
	}

	public void setGprice(double gprice) {
		this.gprice = gprice;
	}

	public double getSubsum() {
		return subsum;
	}

	public void setSubsum(double subsum) {
		this.subsum = subsum;
	}
}
