package com.example.demo.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.SubscribedUsersDetailsDao;
import com.example.demo.modelandview.Values;

@Service
public class ControlService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	Values value;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserHitsService userHitsService;

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private BillingResponseService billingResponseService;
	
	@Autowired
	private SubscribedUsersDetailsDao subscribedUsersDetailsDao;

	public ModelAndView confirmSubService(String hostName, String hostIp) throws URISyntaxException {
		logger.info("saving user hit details in user hit table IP:" + hostIp + " User Agent" + hostName);
		int userHitId = userHitsService.saveUserHitInfo(hostName, hostIp);
		logger.info("user hit id details is: " + userHitId + " IP: " + hostIp + " User Agent" + hostName);
		int transactionId = transactionService.saveTransactionInfo(userHitId);
		URI tokenURI = new URI(value.getTokenURL());
		logger.info("getting token id from API IP:" + hostIp + " User Agent" + hostName);
		ResponseEntity<String> tokenResponse = restTemplate.getForEntity(tokenURI, String.class);
		String token = tokenResponse.getBody();
		logger.info("received token id is " + token + " IP:" + hostIp + " User Agent" + hostName);
		URI confirmURI = new URI(value.getConfirmURL() + "?token=" + token + "&trx_id=" + transactionId + "&service=" + value.getService()
				+ "&telco=" + value.getTelco() + "&shortcode=" + value.getShortcode() + "&price=" + value.getPrice() + "&success_url=" + value.getSuccessURL()
				+ "&cancel_url=" + value.getCancelURL());
		logger.info("redirecting to Confirm URI: " + confirmURI);
		/*
		 * ResponseEntity<String> response= restTemplate.getForEntity(confirmURI,
		 * String.class);
		 * 
		 * logger.info("Recieved Response from confrimURI :"+response);
		 */
		return new ModelAndView("redirect:" + confirmURI);

	}

	public String MTService(String msisdn, String trx_id, String session_id, String shortcode, String telco,String message, String price, String service, String subject, String content_type)throws URISyntaxException, UnsupportedEncodingException {
		logger.info("creating mt url where msisdn="+msisdn);
		message=message+Base64.getEncoder().encodeToString(msisdn.getBytes("utf-8"));
		URI mtURI = new URI(value.getMt_url()+"?msisdn="+msisdn+"&trx_id="+"&session_id="+"&shortcode="+shortcode+"&telco="
		+telco+"&message="+message+"&price="+price+"&service="+service+"&subject="+subject+"&content_type="+content_type);
		logger.info("sending mt URI: "+mtURI);
		ResponseEntity<String> mtResponse = restTemplate.getForEntity(mtURI, String.class);
		String mt = mtResponse.getBody();
		logger.info("response from MT url: "+mt);
		logger.info("inserting into com_billing_response");
		billingResponseService.billingResponseInfo(msisdn);
		logger.info("updating into com_transaction billing details");
		transactionService.updateBillingTransaction(msisdn, value.getPackPrice(), session_id,"PENDD");
		return mt;
	}
	
	public String renewalService(String msisdn, String shortcode, String telco,String message, String price, String service, String content_type)throws URISyntaxException, UnsupportedEncodingException {
		message=message+Base64.getEncoder().encodeToString(msisdn.getBytes("utf-8"));
		logger.info("creating mt url for renewal where msisdn="+msisdn);
		URI mtURI = new URI(value.getMt_url()+"?msisdn="+msisdn+"&trx_id="+"&session_id="+"&shortcode="+shortcode+"&telco="
		+telco+"&message="+message+"&price="+price+"&service="+service+"&subject=&content_type="+content_type);
		logger.info("pushing mt URI for renewal: "+mtURI);
		
		ResponseEntity<String> mtResponse = restTemplate.getForEntity(mtURI, String.class);
		String mt = mtResponse.getBody();
		String[] arrayOfResponseBody = mt.split(":");
		logger.info("response from MT url for remewal: "+mt);
		logger.info("Inserting Renewal Transaction details into com_transaction table");
		transactionService.insertRenwalTransaction(arrayOfResponseBody[1],msisdn);
		logger.info("inserting into com_billing_response for renewal");
		billingResponseService.billingResponseInfo(msisdn);
		return mt;
	}
}
