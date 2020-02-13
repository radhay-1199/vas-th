package com.example.demo.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.modelandview.Packs;

@Component
public class PacksDao {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertdetails(Packs p) {
		try {
			logger.info("inserting into table com_packs");
			String query="insert into com_packs values("+p.getPackId()+","+p.getName()+","+p.getPackDescription()+","+p.getType()+","+p.getPrice()+","+p.getProductId()+","+p.getClientId()+","+p.getProductPwd()+","+p.getDuration()+","+p.getDownloadLimit()+","+p.getLandingPageId()+","+p.getBillerId()+","+p.getUserId()+","+p.getMessage()+","+p.getStatus()+","+p.getCreated()+","+p.getModified()+","+p.getCgImg()+","+p.getFcImg()+","+p.getFcFlag()+","+p.getOldId()+","+p.getPublisher()+","+p.getConsentTime()+","+p.getZone()+","+p.getCampaignName()+","+p.getServiceId()+","+p.getServiceType()+","+p.getServiceNode()+","+p.getPlanId()+","+p.getMatchType()+","+p.getCountryId()+","+p.getOperatorId()+","+p.getUnsubCode()+");";
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
			return 1;
		}
		catch(Exception e) {
			logger.info("exception while inserting in table com_packs: ."+e);
			e.printStackTrace();
			return 0;
		}
	}
}
