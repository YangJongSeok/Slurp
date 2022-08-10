package com.Slurp.dto;

public class AddressDTO {

	private String postcode;
	private String address;
	private String detailAddress;
	private String extraAddress;
	
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getExtraAddress() {
		return extraAddress;
	}
	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}
	
	@Override
	public String toString() {
		return "AddressDTO [postcode=" + postcode + ", address=" + address + ", detailAddress=" + detailAddress
				+ ", extraAddress=" + extraAddress + "]";
	}
	
	
	
}
