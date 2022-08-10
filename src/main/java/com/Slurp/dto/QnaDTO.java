package com.Slurp.dto;

public class QnaDTO {
	private int qnum;
	private String qtitle;
	private String qcontent;
	private String mid;
	private String qcheck;
	private String qdate;
	private String scheck;
	
	public int getQnum() {
		return qnum;
	}

	public void setQnum(int qnum) {
		this.qnum = qnum;
	}

	public String getQtitle() {
		return qtitle;
	}

	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}

	public String getQcontent() {
		return qcontent;
	}

	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getQcheck() {
		return qcheck;
	}

	public void setQcheck(String qcheck) {
		this.qcheck = qcheck;
	}
	public String getQdate() {
		return qdate;
	}

	public void setQdate(String qdate) {
		this.qdate = qdate;
	}

	public String getScheck() {
		return scheck;
	}

	public void setScheck(String scheck) {
		this.scheck = scheck;
	}

	@Override
	public String toString() {
		return "QnaDTO [qnum=" + qnum + ", qtitle=" + qtitle + ", qcontent=" + qcontent + ", mid=" + mid + ", qcheck="
				+ qcheck + ", qdate=" + qdate + ", scheck=" + scheck + "]";
	}



	
}