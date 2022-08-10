package com.Slurp.dto;

public class WishlistDTO2 {

	private String gcode;
	private String gimg;
	private String gname;
	private int gprice;
	
	public String getGcode() {
		return gcode;
	}
	public void setGcode(String gcode) {
		this.gcode = gcode;
	}
	public String getGimg() {
		return gimg;
	}
	public void setGimg(String gimg) {
		this.gimg = gimg;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getGprice() {
		return gprice;
	}
	public void setGprice(int gprice) {
		this.gprice = gprice;
	}
	
	
	@Override
	public String toString() {
		return "WishlistDTO2 [gcode=" + gcode + ", gimg=" + gimg + ", gname=" + gname + ", gprice=" + gprice + "]";
	}
	
	
	
}
