package com.example.demo.service;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.SubscribedUsersDetailsDao;
import com.example.demo.modelandview.SubscribedUsersDetails;
import com.example.demo.modelandview.Values;

@Service
public class RenewalService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SubscribedUsersDetailsDao subscribedUsersDetailsDao;
	
	@Autowired
	ControlService controlService;
	
	@Autowired
	Values value;
	
	public int renewalForSubscribedUsers() throws URISyntaxException, UnsupportedEncodingException{
		logger.info("getting list of subscribed users for renewal");
		List<SubscribedUsersDetails> list=subscribedUsersDetailsDao.getAllSubscribers();
		logger.info("saving subscriber details inside the list"+list);
		for(SubscribedUsersDetails temp : list) {
			logger.info("pushing renewal mt to subscribed users");
			controlService.renewalService(temp.getMsisdn(), value.getShortcode(), value.getTelco(), value.getMessage(), value.getMtPrice(), value.getService(), value.getContent_type());
			logger.info("mt pushed for renewal of service for subscribed users");
		}
		return 1;
	}

}
