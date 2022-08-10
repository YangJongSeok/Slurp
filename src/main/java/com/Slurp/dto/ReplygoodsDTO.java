package com.Slurp.dto;

public class ReplygoodsDTO {

	private int r_num;
	private String gcode;
	private int r_grade;
	private String r_reply;
	private String r_img1;
	private String r_img2;
	private String r_img3;
	private String mid;
	
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public String getGcode() {
		return gcode;
	}
	public void setGcode(String gcode) {
		this.gcode = gcode;
	}
	public int getR_grade() {
		return r_grade;
	}
	public void setR_grade(int r_grade) {
		this.r_grade = r_grade;
	}
	public String getR_reply() {
		return r_reply;
	}
	public void setR_reply(String r_reply) {
		this.r_reply = r_reply;
	}
	public String getR_img1() {
		return r_img1;
	}
	public void setR_img1(String r_img1) {
		this.r_img1 = r_img1;
	}
	public String getR_img2() {
		return r_img2;
	}
	public void setR_img2(String r_img2) {
		this.r_img2 = r_img2;
	}
	public String getR_img3() {
		return r_img3;
	}
	public void setR_img3(String r_img3) {
		this.r_img3 = r_img3;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	@Override
	public String toString() {
		return "ReplygoodsDTO [r_num=" + r_num + ", gcode=" + gcode + ", r_grade=" + r_grade + ", r_reply=" + r_reply
				+ ", r_img1=" + r_img1 + ", r_img2=" + r_img2 + ", r_img3=" + r_img3 + ", mid=" + mid + "]";
	}
}
