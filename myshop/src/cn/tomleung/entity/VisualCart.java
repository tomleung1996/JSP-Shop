package cn.tomleung.entity;

//����һ�����ﳵ��ͼ�࣬���Ǻ����ݿ���ص�����ģ�ͣ�����Ϊ�˷�����ʾ
public class VisualCart {
	private int uid;
	private int gid;
	private String gname;
	private double gprice;
	private int qty;
	private double subsum;
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
}
