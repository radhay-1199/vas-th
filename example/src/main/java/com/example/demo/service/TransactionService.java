package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TransactionDao;
import com.example.demo.modelandview.Transaction;
import com.example.demo.modelandview.Values;

@Service
public class TransactionService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	TransactionDao transactiondao;
	
	@Autowired
	BillingResponseService billingResponseService;
	
	@Autowired
	Values values;
	
	public int saveTransactionInfo(Integer userHitId, String clickId) {
		Transaction transaction=new Transaction(0,userHitId.toString(),values.getPackId(),values.getInterfacee(),values.getBillerId(),userHitId,values.getPublisher(),clickId,values.getProductId());
		return transactiondao.insertdetails(transaction);
	}
	public int updateCgTransaction(String msisdn,String status,String statusCode,int transactionId,String sessionId ) {
		Transaction transaction=new Transaction(msisdn,transactionId,statusCode,status,sessionId);
		return transactiondao.updateCg(transaction);
	}
	public int updateBillingTransaction(String msisdn,float requestedPrice,String sessionId,String billingStatus, String newSessionId) {
		Transaction transaction=new Transaction(msisdn,requestedPrice,sessionId,billingStatus);
		return transactiondao.updateBillingTransaction(transaction,newSessionId);
	}
	public int updateBillingTransactionCharge(String sessionId,String msisdn) {
		    transactiondao.updateChargeAmmountAndBillingStatus(sessionId);
			logger.info("inserting into com_billing_response");
			billingResponseService.billingResponseInfo(msisdn);
		 //bp_adnetwork->callback_url
		 //transaction se adntwork_id
		 //check of sending callback
		 //hit callback_url 
		 return 1;
	}
	public int updateCgStatus(String sessionId) {
		return transactiondao.updateCgStatusDetails(sessionId);
	}
	public int updateBillingStatusAndResponse(String sessionId) {
		return transactiondao.updateBillingStatusDetails(sessionId);
	}
	public int insertRenwalTransaction(String sessionId,String msisdn) {
		return transactiondao.insertionRenewalTransaction(0,msisdn,values.getPackId(),values.getProductId(),sessionId,10,"success","PUSH","PENDD",values.getInterfacee(),values.getPublisher(),sessionId,values.getBillerId());
	}

}
