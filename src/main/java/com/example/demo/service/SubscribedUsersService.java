package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.SubscribedUsersDao;
import com.example.demo.modelandview.SubscribedUsers;
import com.example.demo.modelandview.Values;

@Service
public class SubscribedUsersService {
	@Autowired
	SubscribedUsersDao subscribedusersdao;
	
	@Autowired
	Values values;
	
	public int subscribedUserInfo(String msisdn) {
		SubscribedUsers subscribedusers=new SubscribedUsers(msisdn,values.getBillerId(),values.getPublisher(),"CURRENT_TIMESTAMP()");
		return subscribedusersdao.insertdetails(subscribedusers);
	}
}
