package com.Slurp.dto;


public class MembersDTO {
	private String mid;
	private String mpw;
	private String mname;
	private String mgender;
	private String mbirth;
	private String mphone;
	private String madd;
	private String memail;
	private String mcheck;
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMgender() {
		return mgender;
	}
	public void setMgender(String mgender) {
		this.mgender = mgender;
	}
	public String getMbirth() {
		return mbirth;
	}
	public void setMbirth(String mbirth) {
		this.mbirth = mbirth;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getMadd() {
		return madd;
	}
	public void setMadd(String madd) {
		this.madd = madd;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getMcheck() {
		return mcheck;
	}
	public void setMcheck(String mcheck) {
		this.mcheck = mcheck;
	}
	@Override
	public String toString() {
		return "MembersDTO [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", mgender=" + mgender + ", mbirth="
				+ mbirth + ", mphone=" + mphone + ", madd=" + madd + ", memail=" + memail + "]";
	}
	
	
}
