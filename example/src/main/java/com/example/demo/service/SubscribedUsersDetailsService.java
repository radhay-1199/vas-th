package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.SubscribedUsersDetailsDao;
import com.example.demo.modelandview.SubscribedUsersDetails;
import com.example.demo.modelandview.Values;

@Service
public class SubscribedUsersDetailsService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SubscribedUsersDetailsDao subscribedusersdetailsdao;
	
	@Autowired
	private BillingResponseService billingResponseService;
	
	@Autowired
	Values values;
	
	public int subscribedUsersDetailsInfo(String msisdn,String sessionId,String product_id ) {
		SubscribedUsersDetails subscribedusersdetails=new SubscribedUsersDetails(values.getBillerId(),values.getPublisher(),msisdn,values.getPackId(),"CURRENT_TIMESTAMP()","CURRENT_TIMESTAMP()",1,"CURRENT_TIMESTAMP() + INTERVAL 24 HOUR",sessionId,product_id);
		return subscribedusersdetailsdao.insertdetails(subscribedusersdetails);
	}
	
	public int unsubscribedUsersDetailsInfo(String msisdn,String sessionId,String product_id ) {
		SubscribedUsersDetails subscribedusersdetails=new SubscribedUsersDetails(values.getBillerId(),values.getPublisher(),msisdn,values.getPackId(),"CURRENT_TIMESTAMP()","CURRENT_TIMESTAMP()",0,"CURRENT_TIMESTAMP() + INTERVAL 24 HOUR",sessionId,product_id);
		return subscribedusersdetailsdao.updateDetails(subscribedusersdetails);
		//return 0;
	}
	public int updateRenewalInfo(String msisdn,String sessionId,String product_id ) {
		logger.info("inserting into com_billing_response for renewal");
		billingResponseService.billingResponseInfo(msisdn);
		SubscribedUsersDetails subscribedusersdetails=new SubscribedUsersDetails(values.getBillerId(),values.getPublisher(),msisdn,values.getPackId(),"CURRENT_TIMESTAMP()","CURRENT_TIMESTAMP()",1,"CURRENT_TIMESTAMP() + INTERVAL 24 HOUR",sessionId,product_id);
		return subscribedusersdetailsdao.updateDetailsDaily(subscribedusersdetails);
	}
}
