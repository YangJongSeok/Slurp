package com.Slurp.dto;

public class GoodsCountCheckDTO {

	private String ccode1;
	private String gcode1;
	private int gstock1;
	private int gsize1;
	private String gcolor1;
	
	public String getCcode1() {
		return ccode1;
	}
	public void setCcode1(String ccode1) {
		this.ccode1 = ccode1;
	}
	public String getGcode1() {
		return gcode1;
	}
	public void setGcode1(String gcode1) {
		this.gcode1 = gcode1;
	}
	public int getGstock1() {
		return gstock1;
	}
	public void setGstock1(int gstock1) {
		this.gstock1 = gstock1;
	}
	public int getGsize1() {
		return gsize1;
	}
	public void setGsize1(int gsize1) {
		this.gsize1 = gsize1;
	}
	public String getGcolor1() {
		return gcolor1;
	}
	public void setGcolor1(String gcolor1) {
		this.gcolor1 = gcolor1;
	}
	@Override
	public String toString() {
		return "GoodsCountCheckDTO [ccode1=" + ccode1 + ", gcode1=" + gcode1 + ", gstock1=" + gstock1 + ", gsize1="
				+ gsize1 + ", gcolor1=" + gcolor1 + "]";
	}
	
	
	
	
	
	
}
