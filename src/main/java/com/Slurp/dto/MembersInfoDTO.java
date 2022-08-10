package com.Slurp.dto;


public class MembersInfoDTO {
	private String mid;		// 아이디 PK
	private String mpw;		// 비밀번호
	private String mname;	// 이름
	private String mgender;	// 성별
	private String mbirth;	// 생년월일
	private String mphone;	// 핸드폰번호 UK
	private String madd;	// 주소
	private String memail;	// 이메일
	private int m_mileage;	// 마일리지
	private int m_hispay;	// 총구매금액
	private String m_rating;	// 회원등급
	
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
	public int getM_mileage() {
		return m_mileage;
	}
	public void setM_mileage(int m_mileage) {
		this.m_mileage = m_mileage;
	}
	public int getM_hispay() {
		return m_hispay;
	}
	public void setM_hispay(int m_hispay) {
		this.m_hispay = m_hispay;
	}
	public String getM_rating() {
		return m_rating;
	}
	public void setM_rating(String m_rating) {
		this.m_rating = m_rating;
	}
	
	@Override
	public String toString() {
		return "MembersDTO [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", mgender=" + mgender + ", mbirth="
				+ mbirth + ", mphone=" + mphone + ", madd=" + madd + ", memail=" + memail + ",  m_mileage=" + m_mileage
				+ ", m_hispay=" + m_hispay + ", m_rating=" + m_rating + "]";
	}
	
}
