package com.example.demo.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.modelandview.WifiUser;


@Component
public class WifiUserDao {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertdetails(WifiUser w) {
		try {
			logger.info("inserting into table wifi_user");
			String query="insert into <tablename>("+w.getMsisdn()+","+w.getId()+","+w.getDelieveryReport()+","+w.getDelieveryReportTime()+","+w.getShortcode()+","+w.getDate()+","+w.getMtId()+","+w.getProcess()+");";
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
			return 1;
		}
		catch(Exception e) {
			System.out.println("exception while inserting in wifi table: "+e);
			e.printStackTrace();
			return 0;
		}
	}
}
