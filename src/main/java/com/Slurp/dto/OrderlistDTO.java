package com.Slurp.dto;

public class OrderlistDTO {

	private int o_code;			// 주문번호	[PK]
	private String gcode;		//상품코드		[FK]
	private String gname;		//상품이름		[FK]
	private String gcolor;		//상품컬러		[FK]
	private int gsize;			//상품사이즈	[FK]
	private int o_quantity;		//구매수량
	private String mid;			//아이디		[FK]
	private String mname;		//이름		[FK]
	private String mgender;		//성별
	private int o_age;			//구매자 나이
	private String o_phone;		//구매자 번호
	private String o_add;		//배달주소
	private String o_request;	//배달요청사항
	private int o_gprice;		//총상품가격
	private String o_date;		//주문일자
	private String o_state;		//주문상태
	private String ccode;		//업체코드 	[FK]
	
	private String gimg;
	
	public int getO_code() {
		return o_code;
	}
	public void setO_code(int o_code) {
		this.o_code = o_code;
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
	public String getGcolor() {
		return gcolor;
	}
	public void setGcolor(String gcolor) {
		this.gcolor = gcolor;
	}
	public int getGsize() {
		return gsize;
	}
	public void setGsize(int gsize) {
		this.gsize = gsize;
	}
	public int getO_quantity() {
		return o_quantity;
	}
	public void setO_quantity(int o_quantity) {
		this.o_quantity = o_quantity;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
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
	public int getO_age() {
		return o_age;
	}
	public void setO_age(int o_age) {
		this.o_age = o_age;
	}
	public String getO_phone() {
		return o_phone;
	}
	public void setO_phone(String o_phone) {
		this.o_phone = o_phone;
	}
	public String getO_add() {
		return o_add;
	}
	public void setO_add(String o_add) {
		this.o_add = o_add;
	}
	public String getO_request() {
		return o_request;
	}
	public void setO_request(String o_request) {
		this.o_request = o_request;
	}
	public int getO_gprice() {
		return o_gprice;
	}
	public void setO_gprice(int o_gprice) {
		this.o_gprice = o_gprice;
	}
	public String getO_date() {
		return o_date;
	}
	public void setO_date(String o_date) {
		this.o_date = o_date;
	}
	public String getO_state() {
		return o_state;
	}
	public void setO_state(String o_state) {
		this.o_state = o_state;
	}
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getGimg() {
		return gimg;
	}
	public void setGimg(String gimg) {
		this.gimg = gimg;
	}
	@Override
	public String toString() {
		return "OrderlistDTO [o_code=" + o_code + ", gcode=" + gcode + ", gname=" + gname + ", gcolor=" + gcolor
				+ ", gsize=" + gsize + ", o_quantity=" + o_quantity + ", mid=" + mid + ", mname=" + mname + ", mgender="
				+ mgender + ", o_age=" + o_age + ", o_phone=" + o_phone + ", o_add=" + o_add + ", o_request="
				+ o_request + ", o_gprice=" + o_gprice + ", o_date=" + o_date + ", o_state=" + o_state + ", ccode="
				+ ccode + "]";
	}

	
	
	
	
}
