package com.Slurp.dto;

import org.springframework.web.multipart.MultipartFile;

public class EventDTO {
	
	private int e_num;			// 글번호 PK [ 테이블 컬럼명 변경 필요 ]
	private String etitle;		// 글제목
	private String econtent;	// 글내용
	private String aid;			// 작성자 FK( ADMIN )
	private String eimg;		// 이벤트 이미지
	
	private MultipartFile efile;
	
	public int getE_num() {
		return e_num;
	}
	public void setE_num(int e_num) {
		this.e_num = e_num;
	}
	public String getEtitle() {
		return etitle;
	}
	public void setEtitle(String etitle) {
		this.etitle = etitle;
	}
	public String getEcontent() {
		return econtent;
	}
	public void setEcontent(String econtent) {
		this.econtent = econtent;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getEimg() {
		return eimg;
	}
	public void setEimg(String eimg) {
		this.eimg = eimg;
	}
	
	
	public MultipartFile getEfile() {
		return efile;
	}
	public void setEfile(MultipartFile efile) {
		this.efile = efile;
	}
	
	@Override
	public String toString() {
		return "EventDTO [e_num=" + e_num + ", etitle=" + etitle + ", econtent=" + econtent + ", aid=" + aid + ", eimg="
				+ eimg + ", efile=" + efile + "]";
	}
	
	
	
}
