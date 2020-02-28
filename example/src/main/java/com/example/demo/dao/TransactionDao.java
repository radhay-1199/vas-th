package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.example.demo.modelandview.SubscribedUsersDetails;
import com.example.demo.modelandview.Transaction;

@Component
public class TransactionDao {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public int insertdetails(Transaction t) {
		try {
			logger.info("inserting into table com_transaction");
			String query="insert into com_transaction(subscribed_user_id,transaction_unique_id,pack_id,interface,biller_id,hit_id,publisher,adnetwork_id,product_id) "
					+ "values(?,?,?,?,?,?,?,?,?)";
			logger.info("query: "+query);



			/*jdbcTemplate.execute("insert into com_transaction values("+t.getTransactionId()+","
			 * +t.getSubscribedUserId()+","+t.getMsisdn()+","+t.getType()+","+t.getPackId()+","+t.getProductId()+","
			 * +t.getTransactionTime()+","+t.getTransactionUniqueId()+","+t.getRequestedPrice()+","+t.getPriceDeducted()+","
			 * +t.getCgResponseId()+","+t.getCgStatus()+","+t.getCgStatusCode()+","+t.getCgResponseTime()+","
			 * +t.getBillingStatus()+","+t.getBillingResponseTime()+","+t.getBillingResponse()+","+t.getBillingId()+","
			 * +t.getBrowserCode()+","+t.getInterfacee()+","+t.getAdnetworkId()+","+t.getCallbackStatus()+","
			 * +t.getBillerId()+","+t.getHitId()+","+t.getCircleName()+","+t.getPublisher()+","+t.getContentId()+","
			 * +t.getSameDayChurn()+","+t.getV3mobiMsisdn()+","+t.getAppUserId()+","+t.getUserType()+","+t.getProcess()+","+
			 * t.getConsent()+","+t.getPubId()+","+t.getSessionId()+");");
			return 1;*/
			
			
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
					new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement ps =
									connection.prepareStatement(query, new String[] {"id"});
							ps.setInt(1, t.getSubscribedUserId());
							ps.setString(2, t.getTransactionUniqueId());
							ps.setInt(3, t.getPackId());
							ps.setString(4, t.getInterfacee());
							ps.setString(5, t.getBillerId());
							ps.setInt(6, t.getHitId());			            
							ps.setString(7, t.getPublisher());
							ps.setString(8, t.getAdnetworkId());
							ps.setString(9, t.getProductId());
							return ps;
						}
					},
					keyHolder);
			
			return Integer.parseInt(""+keyHolder.getKey());
			}
		catch(Exception e) {
			System.out.println("exception while inserting in table com_transaction: "+e);
			return 0;
		}
	}
	
	public int updateCg(Transaction t) {
		try {
			logger.info("updating cg in table com_transaction");
			String query="update com_transaction set session_id='"+t.getSessionId()+"',msisdn='"+t.getMsisdn()+"',cg_status_code='"+t.getCgStatusCode()+"',cg_status='"+t.getCgStatus()+"' where transaction_id="+t.getTransactionId();
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
		return 1;
		}catch(Exception e) {
			System.out.println("exception while updating cg in table com_transaction table: "+e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateBillingTransaction(Transaction t, String newSessionId) {
		try {
			logger.info("updating after DR in table com_transaction");
			String query="update com_transaction set session_id='"+newSessionId+"', requested_price="+t.getRequestedPrice()+",billing_status='PENDD' where session_id="+t.getSessionId()+" and msisdn='"+t.getMsisdn()+"'";
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
			logger.info("updated billing transaction");
			return 1;
		}catch(Exception e) {
			logger.info("exception while updating billing transaction in table com_transaction: "+e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateChargeAmmountAndBillingStatus(String sessionId) {
		try {
			logger.info("updating billing status and price deducted in com_transaction table");
			String query="update com_transaction set price_deducted='10.00', billing_status='DONE'"+"where session_id='"+sessionId+"'";
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
			logger.info("updated transaction billing status and price deducted");
			return 1;
		}catch(Exception e) {
			logger.info("exception while updating billing status and price deducted in com_transaction Table : "+e);
			e.printStackTrace();
			return 0;
		}
	}
	public int updateCgStatusDetails(String sessionId) {
		try {
			logger.info("updating cg status in com_transaction Table");
			String query="update com_transaction set cg_status='FAIL' where session_id='"+sessionId+"'";
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
			logger.info("updated cg status in com_transaction table");
			return 1;
		} catch (Exception e) {
			logger.info("exception while updating cg status in com_transaction table : "+e);
			e.printStackTrace();
			return 0;
		}
	}
	public int updateBillingStatusDetails(String sessionId) {
		try {
			logger.info("updating billing status in com_transaction table");
			String query="update com_transaction set billing_status='FAIL', billing_response='REJECTED', billing_response_time=CURRENT_TIMESTAMP() where session_id='"+sessionId+"'";
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
			logger.info("updated billing status in com_transaction table");
			return 1;
		} catch (Exception e) {
			logger.info("exception while updating billing status and response in com_transaction table : "+e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public int insertionRenewalTransaction(int subscribedUserId, String msisdn, int packId, String productId, String transactionUniqueId,
			float requestedPrice, String cgStatus, String cgStatusCode, String billingStatus, String interfacee, String publisher,
			String sessionId, String billerId) {
		try {
			logger.info("Inserting transaction entry during renewal in com_transaction table");
			String query="insert into com_transaction(subscribed_user_id,msisdn,pack_id,product_id,transaction_unique_id,requested_price,cg_status,cg_status_code,billing_status,interface,publisher,session_id,biller_id) "
					+ "values("+subscribedUserId+",'"+msisdn+"',"+packId+",'"+productId+"','"+transactionUniqueId+"',"+requestedPrice+",'"
					+cgStatus+"','"+cgStatusCode+"','"+billingStatus+"','"+interfacee+"','"+publisher+"','"+sessionId+"','"+billerId+"')";
			logger.info("query: "+query);
			jdbcTemplate.execute(query);
			logger.info("inserted new transaction entry for renewal in com_transaction table");
			return 1;
		} catch (Exception e) {
			logger.info("exception while inserting new entry for renewal in com_transaction table : "+e);
			e.printStackTrace();
			return 0;
		}
	}

	public Integer getUserHitId(Integer trxId) {
		String query ="select hit_id from com_transaction where transaction_id="+trxId;
		logger.info("query"+query);
		 return jdbcTemplate.query(query,new ResultSetExtractor<Integer>(){

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				Integer userHitId=null;
				while(rs.next()) {
					userHitId= rs.getInt("hit_id");
				}
				return userHitId;
			}  
		    });  
	}

}
