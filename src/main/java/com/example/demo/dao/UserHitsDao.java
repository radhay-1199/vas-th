package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.example.demo.modelandview.UserHits;

@Component
public class UserHitsDao {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertdetails(UserHits userhit) {
		try {
			logger.info("inserting into table com_user_hits");
			String query="insert into com_user_hits(ip_address,interface,pack_id,date,user_agent,biller_id,publisher)"
					+ " values(?,?,?,"+userhit.getDate()+",?,?,?)";
			logger.info("query: "+query);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(query, new String[] {"id"});
			            ps.setString(1, userhit.getIpAddress());
			            ps.setString(2, userhit.getInterfacee());
			            ps.setInt(3, userhit.getPackId());
			            ps.setString(4, userhit.getUserAgent());			            
			            ps.setString(5, userhit.getBillerId());			            
			            ps.setString(6, userhit.getPublisher());			            
			            return ps;
			        }
			    },
			    keyHolder);
			
			
			/*
			 * jdbcTemplate.
			 * execute("insert into com_user_hits(ip_address,interface,pack_id,date,user_agent,biller_id,publisher)"
			 * +
			 * " values('"+userhit.getIpAddress()+"','"+userhit.getInterfacee()+"',"+userhit
			 * .getPackId()+","+userhit.getDate()+",'"+userhit.getUserAgent()+"','"+userhit.
			 * getBillerId()+"','"+userhit.getPublisher()+"')");
			 */
			return Integer.parseInt(""+keyHolder.getKey());
		}
		catch(Exception e) {
			System.out.println("exception while inserting in table com_user_hits: "+e);
			e.printStackTrace();
			return 0;
		}
	}
	
}
