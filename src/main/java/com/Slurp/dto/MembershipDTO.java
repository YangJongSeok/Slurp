package com.Slurp.dto;

public class MembershipDTO {

	private String mid;
	private int m_mileage;
	private int m_hispay;
	private String m_rating;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
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
		return "MembershipDTO [mid=" + mid + ", m_mileage=" + m_mileage + ", m_hispay=" + m_hispay
				+ ", m_rating=" + m_rating + "]";
	}
	

	
	
	
}
