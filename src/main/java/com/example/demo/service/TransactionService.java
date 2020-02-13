package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TransactionDao;
import com.example.demo.modelandview.Transaction;
import com.example.demo.modelandview.Values;

@Service
public class TransactionService {
	
	@Autowired
	TransactionDao transactiondao;
	
	@Autowired
	Values values;
	
	public int saveTransactionInfo(Integer userHitId) {
		Transaction transaction=new Transaction(0,userHitId.toString(),values.getPackId(),values.getPublisher(),values.getBillerId(),userHitId,values.getPublisher());
		return transactiondao.insertdetails(transaction);
	}
	public int updateCgTransaction(String msisdn,String status,String statusCode,int transactionId,String sessionId ) {
		Transaction transaction=new Transaction(msisdn,transactionId,statusCode,status,sessionId);
		return transactiondao.updateCg(transaction);
	}
	public int updateBillingTransaction(String msisdn,float requestedPrice,String sessionId,String billingStatus) {
		Transaction transaction=new Transaction(msisdn,requestedPrice,sessionId,billingStatus);
		return transactiondao.updateBillingTransaction(transaction);
	}
	public int updateBillingTransactionCharge(String sessionId) {
		return transactiondao.updateChargeAmmountAndBillingStatus(sessionId);
	}
	public int updateCgStatus(String sessionId) {
		return transactiondao.updateCgStatusDetails(sessionId);
	}
	public int updateBillingStatusAndResponse(String sessionId) {
		return transactiondao.updateBillingStatusDetails(sessionId);
	}
	public int insertRenwalTransaction(String sessionId,String msisdn) {
		return transactiondao.insertionRenewalTransaction(0,msisdn,values.getPackId(),values.getProductId(),sessionId,10,"success","PUSH","PENDD",values.getPublisher(),values.getPublisher(),sessionId);
	}

}
