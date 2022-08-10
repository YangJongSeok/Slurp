package com.Slurp.dto;

public class QnaReplyDTO {
	private int qr_num;
	private int qr_qnum;
	private String aid;
	private String qr_content;
	private String qr_date;
	
	public int getQr_num() {
		return qr_num;
	}
	public void setQr_num(int qr_num) {
		this.qr_num = qr_num;
	}
	public int getQr_qnum() {
		return qr_qnum;
	}
	public void setQr_qnum(int qr_qnum) {
		this.qr_qnum = qr_qnum;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getQr_content() {
		return qr_content;
	}
	public void setQr_content(String qr_content) {
		this.qr_content = qr_content;
	}
	public String getQr_date() {
		return qr_date;
	}
	public void setQr_date(String qr_date) {
		this.qr_date = qr_date;
	}
	
	@Override
	public String toString() {
		return "QnaReplyDTO [qr_num=" + qr_num + ", qr_qnum=" + qr_qnum + ", aid=" + aid + ", qr_content=" + qr_content
				+ ", qr_date=" + qr_date + "]";
	}

	
}
