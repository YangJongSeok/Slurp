package com.Slurp.dto;




public class PageDTO {
	private int page; // 페이지번호
	private int maxpage; // 글 개수에 따른 최대 페이지 개수
	private int startpage; // 시작페이지 번호
	private int endpage; // 끝 페이지 번호
	private int startrow; // 
	private int endrow; //
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxpage() {
		return maxpage;
	}
	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}
	public int getStartpage() {
		return startpage;
	}
	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}
	public int getEndpage() {
		return endpage;
	}
	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}
	public int getStartrow() {
		return startrow;
	}
	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}
	public int getEndrow() {
		return endrow;
	}
	public void setEndrow(int endrow) {
		this.endrow = endrow;
	}
	
	
	
}
