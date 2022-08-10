package com.Slurp.dto;

public class WishlistDTO {
	
	private int wl_code;
	private String mid;
	private String gcode;
	public int getWl_code() {
		return wl_code;
	}
	public void setWl_code(int wl_code) {
		this.wl_code = wl_code;
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
	@Override
	public String toString() {
		return "WishlistDTO [wl_code=" + wl_code + ", mid=" + mid + ", gcode=" + gcode + "]";
	}
	
	
	
}
