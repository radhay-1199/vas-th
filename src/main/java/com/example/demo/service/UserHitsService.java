package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserHitsDao;
import com.example.demo.modelandview.UserHits;

@Service
public class UserHitsService {
	@Autowired
	UserHitsDao userhitdao;
	
	public int saveUserHitInfo(String hostName,String hostIp) {
		UserHits userhit = new UserHits(hostIp,"Jovial Digital",34,"CURRENT_TIMESTAMP()",hostName,"th","Jovial Digital");
		return userhitdao.insertdetails(userhit);
	}
	
}
