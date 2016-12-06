package cn.tomleung.entity;

import java.io.Serializable;

public class Good implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5109535777709019791L;
	private int gid;
	private String gname;
	private double gprice;
	private String gpic;
	private String gdes;
	private String gorigin;

	public String getGdes() {
		return gdes;
	}

	public void setGdes(String gdes) {
		this.gdes = gdes;
	}

	public String getGorigin() {
		return gorigin;
	}

	public void setGorigin(String gorigin) {
		this.gorigin = gorigin;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
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

	public String getGpic() {
		return gpic;
	}

	public void setGpic(String gpic) {
		this.gpic = gpic;
	}


}
