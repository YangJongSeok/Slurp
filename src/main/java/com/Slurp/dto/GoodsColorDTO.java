package com.Slurp.dto;

public class GoodsColorDTO {

	private String gcode;
	private String gcolor;
	
	public String getGcode() {
		return gcode;
	}
	public void setGcode(String gcode) {
		this.gcode = gcode;
	}
	public String getGcolor() {
		return gcolor;
	}
	public void setGcolor(String gcolor) {
		this.gcolor = gcolor;
	}
	
	@Override
	public String toString() {
		return "GoodsColorDTO [gcode=" + gcode + ", gcolor=" + gcolor + "]";
	}
	
	
	
	
	
}
