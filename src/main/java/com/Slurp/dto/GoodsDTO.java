package com.Slurp.dto;

import org.springframework.web.multipart.MultipartFile;

public class GoodsDTO {

	private String gcode;		// 상품코드  PK | UUID.RANDOM
	private String gname;		// 상품이름  UK
	private int gprice;			// 상품가격
	private int gcate;			// 상품분류
	private String ggender;		// 상품성별
	private String ccode;		// 업체코드  FK ( COMPANY )
	private String gcontent;	// 상품설명
	private String gimg;		// 이미지메인
	
	 private String gdtimg1; // 상품설명이미지1 
	 private String gdtimg2; // 상품설명이미지2
	 private String gdtimg3; // 상품설명이미지3 
	 private String gdtimg4; // 상품설명이미지4
	 	
	private String gdate;		// 등록일
	private String cname;
	
	private MultipartFile gfile;
	private MultipartFile gfile1;
	private MultipartFile gfile2;
	private MultipartFile gfile3;
	private MultipartFile gfile4;

	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getGcode() {
		return gcode;
	}
	public void setGcode(String gcode) {
		this.gcode = gcode;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getGprice() {
		return gprice;
	}
	public void setGprice(int gprice) {
		this.gprice = gprice;
	}
	public int getGcate() {
		return gcate;
	}
	public void setGcate(int gcate) {
		this.gcate = gcate;
	}
	public String getGgender() {
		return ggender;
	}
	public void setGgender(String ggender) {
		this.ggender = ggender;
	}
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getGcontent() {
		return gcontent;
	}
	public void setGcontent(String gcontent) {
		this.gcontent = gcontent;
	}
	public String getGimg() {
		return gimg;
	}
	public void setGimg(String gimg) {
		this.gimg = gimg;
	}
	public String getGdtimg1() {
		return gdtimg1;
	}
	public void setGdtimg1(String gdtimg1) {
		this.gdtimg1 = gdtimg1;
	}
	public String getGdtimg2() {
		return gdtimg2;
	}
	public void setGdtimg2(String gdtimg2) {
		this.gdtimg2 = gdtimg2;
	}
	public String getGdate() {
		return gdate;
	}
	public void setGdtimg3(String gdtimg3) {
		this.gdtimg3 = gdtimg3;
	}
	public String getGdtimg4() {
		return gdtimg4;
	}
	public void setGdtimg4(String gdtimg4) {
		this.gdtimg4 = gdtimg4;
	}
	public void setGdate(String gdate) {
		this.gdate = gdate;
	}
	public String getGdtimg3() {
		return gdtimg3;
	}
	public MultipartFile getGfile() {
		return gfile;
	}

	public void setGfile(MultipartFile gfile) {
		this.gfile = gfile;
	}


	public MultipartFile getGfile1() {
		return gfile1;
	}

	public void setGfile1(MultipartFile gfile1) {
		this.gfile1 = gfile1;
	}

	public MultipartFile getGfile2() {
		return gfile2;
	}

	public void setGfile2(MultipartFile gfile2) {
		this.gfile2 = gfile2;
	}

	public MultipartFile getGfile3() {
		return gfile3;
	}

	public void setGfile3(MultipartFile gfile3) {
		this.gfile3 = gfile3;
	}

	public MultipartFile getGfile4() {
		return gfile4;
	}

	public void setGfile4(MultipartFile gfile4) {
		this.gfile4 = gfile4;
	}
	
	@Override
	public String toString() {
		return "GoodsDTO [gcode=" + gcode + ", gname=" + gname + ", gprice=" + gprice + ", gcate=" + gcate
				+ ", ggender=" + ggender + ", ccode=" + ccode + ", gcontent=" + gcontent + ", gimg=" + gimg
				+ ", gdtimg1=" + gdtimg1 + ", gdtimg2=" + gdtimg2 + ", gdtimg3=" + gdtimg3 + ", gdtimg4=" + gdtimg4
				+ ", gdate=" + gdate + ", cname=" + cname + ", gfile=" + gfile + ", gfile1=" + gfile1 + ", gfile2="
				+ gfile2 + ", gfile3=" + gfile3 + ", gfile4=" + gfile4 + "]";
	}

	
	
	
}
