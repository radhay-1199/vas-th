package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BillingResponseDao;
import com.example.demo.modelandview.BillingResponse;

@Service
public class BillingResponseService {
	@Autowired
	BillingResponseDao billingresponsedao;
	
	public int billingResponseInfo(String msisdn) {
		BillingResponse billingResponse=new BillingResponse(msisdn,"CURRENT_TIMESTAMP()");
		return billingresponsedao.insertdetails(billingResponse);
	}
}
