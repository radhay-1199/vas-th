package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.modelandview.Wifi;


@Component
public class WifiDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertdetails(Wifi w) {
		try {
			jdbcTemplate.execute("insert into <tablename>("+w.getMsisdn()+","+w.getId()+","+w.getDelieveryReport()+","+w.getDelieveryReportTime()+","+w.getShortcode()+","+w.getDate()+","+w.getMtId()+","+w.getProcess()+");");
			return 1;
		}
		catch(Exception e) {
			System.out.println("exception in select token id....."+e);
			return 0;
		}
	}
}
