package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TransactionDao;
import com.example.demo.dao.UserHitsDao;
import com.example.demo.modelandview.UserHits;
import com.example.demo.modelandview.Values;

@Service
public class UserHitsService {
	@Autowired
	UserHitsDao userhitdao;
	
	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	Values value;
	
	public int saveUserHitInfo(String hostName,String hostIp) {
		UserHits userhit = new UserHits(hostIp,value.getInterfacee(),value.getPackId(),"CURRENT_TIMESTAMP()",hostName,value.getBillerId(),value.getPublisher());
		return userhitdao.insertdetails(userhit);
	}

	public int updateMsisdn(String msisdn,Integer trxId) {
		Integer hitId=transactionDao.getUserHitId(trxId);
		return userhitdao.updateMSISDN(hitId,msisdn);
	}
	
}
