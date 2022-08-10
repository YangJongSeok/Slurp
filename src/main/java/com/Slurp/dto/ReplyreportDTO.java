package com.Slurp.dto;

public class ReplyreportDTO {

	private int rr_num;
	private int r_num;
	private String rr_content;
	private String mid;
	public int getRr_num() {
		return rr_num;
	}
	public void setRr_num(int rr_num) {
		this.rr_num = rr_num;
	}
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public String getRr_content() {
		return rr_content;
	}
	public void setRr_content(String rr_content) {
		this.rr_content = rr_content;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	@Override
	public String toString() {
		return "Replyreport [rr_num=" + rr_num + ", r_num=" + r_num + ", rr_content=" + rr_content + ", mid=" + mid
				+ "]";
	}
	
	
	
}
