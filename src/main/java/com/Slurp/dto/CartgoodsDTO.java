package com.Slurp.dto;

public class CartgoodsDTO {

	private int cg_code;
	private String mid;
	private String gcode;
	private int gstock;
	private int gsize;
	private String gcolor;
	private int gprice;
	
	private String gimg;
	private String gname;
	
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	
	public int getCg_code() {
		return cg_code;
	}
	public void setCg_code(int cg_code) {
		this.cg_code = cg_code;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getGcode() {
		return gcode;
	}
	public void setGcode(String gcode) {
		this.gcode = gcode;
	}
	public int getGstock() {
		return gstock;
	}
	public void setGstock(int gstock) {
		this.gstock = gstock;
	}
	public int getGsize() {
		return gsize;
	}
	public void setGsize(int gsize) {
		this.gsize = gsize;
	}
	public String getGcolor() {
		return gcolor;
	}
	public void setGcolor(String gcolor) {
		this.gcolor = gcolor;
	}
	public int getGprice() {
		return gprice;
	}
	public void setGprice(int gprice) {
		this.gprice = gprice;
	}
	
	
	
	public String getGimg() {
		return gimg;
	}
	public void setGimg(String gimg) {
		this.gimg = gimg;
	}
	@Override
	public String toString() {
		return "CartgoodsDTO [cg_code=" + cg_code + ", mid=" + mid + ", gcode=" + gcode + ", gstock=" + gstock
				+ ", gsize=" + gsize + ", gcolor=" + gcolor + ", gprice=" + gprice + "]";
	}
	
	
}
