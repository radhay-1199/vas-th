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

import com.example.demo.modelandview.SubscribedUsers;

@Component
public class SubscribedUsersDao {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public int insertdetails(SubscribedUsers s) {
		try {
			logger.info("inserting into table com_subscribed_users");
			String query="insert into com_subscribed_users(msisdn,biller_id,publisher,created,mdnTokenId) "
					+ "values(?,?,?,"+s.getCreated()+",?)";
			logger.info("query: "+query);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(query, new String[] {"id"});
			            ps.setString(1, s.getMsisdn());
			            ps.setString(2, s.getBillerId());
			            ps.setString(3, s.getPublisher());
			            ps.setString(4,"");
			            return ps;
			        }
			    },
			    keyHolder);
			/*
			 * jdbcTemplate.execute("insert into com_subscribed_users values("+s.
			 * getSubscribedUserId()+","+s.getMsisdn()+","+s.getAppUserId()+","+s.getCircle(
			 * )+","+s.getBillerId()+","+s.getPublisher()+","+s.getMdnTokenId()+","+s.
			 * getCreated()+");");
			 */
			return Integer.parseInt(""+keyHolder.getKey());
		}
		catch(Exception e) {
			System.out.println("exception while inserting in table com_subscribed_users: "+e);
			e.printStackTrace();
			return 0;
		}
	}
}
