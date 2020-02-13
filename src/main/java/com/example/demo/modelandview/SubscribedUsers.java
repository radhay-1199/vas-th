package com.example.demo.modelandview;

public class SubscribedUsers {
	private int subscribedUserId;
	private String msisdn;
	private int appUserId;
	private String circle;
	private String billerId;
	private String publisher;
	private String mdnTokenId;
	private String created;
	
	public SubscribedUsers() {}
	
	public SubscribedUsers(String msisdn,String billerId,String publisher,String created) {
		this.msisdn=msisdn;
		this.billerId=billerId;
		this.publisher=publisher;
		this.created=created;
	}
	public int getSubscribedUserId() {
		return subscribedUserId;
	}
	public void setSubscribedUserId(int subscribedUserId) {
		this.subscribedUserId = subscribedUserId;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public int getAppUserId() {
		return appUserId;
	}
	public void setAppUserId(int appUserId) {
		this.appUserId = appUserId;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getBillerId() {
		return billerId;
	}
	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getMdnTokenId() {
		return mdnTokenId;
	}
	public void setMdnTokenId(String mdnTokenId) {
		this.mdnTokenId = mdnTokenId;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	@Override
	public String toString() {
		return "SubscribedUsers [subscribedUserId=" + subscribedUserId + ", msisdn=" + msisdn + ", appUserId="
				+ appUserId + ", circle=" + circle + ", billerId=" + billerId + ", publisher=" + publisher
				+ ", mdnTokenId=" + mdnTokenId + ", created=" + created + "]";
	}
	

}
