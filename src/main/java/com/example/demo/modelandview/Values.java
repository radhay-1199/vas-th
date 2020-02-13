package com.example.demo.modelandview;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Values {
	@Value("${publisher}")
	private String publisher;
	@Value("${biller_id}")
	private String biller_id;
	@Value("${pack_id}")
	private int pack_id;
	@Value("${packPrice}")
	private float packPrice;
	@Value("${requestPlan}")
	private String requestPlan;
	@Value("${validityDays}")
	private String validityDays;
	@Value("${operationId}")
	private String operationId;
	@Value("${bearerId}")
	private String bearerId;
	@Value("${churn}")
	private int churn;
	@Value("${action}")
	private String action;
	@Value("${shortcode}")
	private String shortcode;
	@Value("${price}")
	private String price;
	@Value("${message}")
	private String message;
	@Value("${subject}") 
	 private String subject;
	@Value("${content_type}")
	private String content_type;
	@Value("${service}")
	private String service;
	@Value("${getTokenURL}")
	private String tokenURL;
	@Value("${confirmSubURL}")
	private String confirmURL;
	@Value("${telco}")
	private String telco;
	@Value("${successURL}")
	private String successURL;
	@Value("${cancelURL}")
	private String cancelURL;
	@Value("${mt_url}")
	private String mt_url;
	@Value("${productId}")
	private String productId;
	@Value("${mtPrice}")
	private String mtPrice;
	
	public String getMtPrice() {
		return mtPrice;
	}

	public String getProductId() {
		return productId;
	}
	
	public String getPrice() {
		return price;
	}

	public String getMessage() {
		return message;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent_type() {
		return content_type;
	}

	public String getTokenURL() {
		return tokenURL;
	}

	public String getConfirmURL() {
		return confirmURL;
	}
	
	public String getService() {
		return service;
	}

	public String getTelco() {
		return telco;
	}

	public String getSuccessURL() {
		return successURL;
	}

	public String getCancelURL() {
		return cancelURL;
	}

	public String getMt_url() {
		return mt_url;
	}
	
	public String getRequestPlan() {
		return requestPlan;
	}
	
	public String getPublisher() {
		return publisher;
	}

	public String getValidityDays() {
		return validityDays;
	}

	public String getOperationId() {
		return operationId;
	}

	public String getBearerId() {
		return bearerId;
	}

	public int getChurn() {
		return churn;
	}

	public String getAction() {
		return action;
	}

	public String getShortcode() {
		return shortcode;
	}

	public String getBillerId() {
		return biller_id;
	}
	public int getPackId() {
		return pack_id;
	}
	public float getPackPrice() {
		return packPrice;
	}
	
}
