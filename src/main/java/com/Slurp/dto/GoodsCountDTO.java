package com.Slurp.dto;

public class GoodsCountDTO {

	private String ccode;
	private String gcode;
	private int gstock;
	private int gsize;
	private String gcolor;
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
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
	@Override
	public String toString() {
		return "GoodsCountDTO [ccode=" + ccode + ", gcode=" + gcode + ", gstock=" + gstock + ", gsize=" + gsize
				+ ", gcolor=" + gcolor + "]";
	}
	
	
	
}
