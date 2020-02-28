package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.modelandview.Values;
import com.example.demo.service.ControlService;
import com.example.demo.service.RenewalService;
import com.example.demo.service.SubscribedUsersDetailsService;
import com.example.demo.service.SubscribedUsersService;
import com.example.demo.service.TransactionService;
import com.example.demo.service.UserHitsService;

@Controller
public class MainController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ControlService controlService;
	
	@Autowired
	TransactionService transactionservice;
	
	@Autowired
	SubscribedUsersDetailsService subscribedUsersDetailsService;
	
	@Autowired
	SubscribedUsersService subscribedUsersSerivice;
	
	@Autowired
	RenewalService renewalService;
	
	@Autowired
	UserHitsService userHitsService;
	
	@Autowired
	Values value;
	
	@Value("${message}")
	private String message;
	@Value("${successPageURL}")
	String successPageURL;
	@Value("${cancelPageURL}")
	String cancelPageURL;
	
	@GetMapping("/confirmsub")
	public ModelAndView invokeService(HttpServletRequest request, @RequestParam(required=false) String clickId,@RequestParam Integer transactionId) throws URISyntaxException {
		String hostName=request.getHeader("User-Agent");
		String hostIp=request.getRemoteAddr();
		logger.info("request received for subscribe from IP: "+hostIp+" and User Agent: "+hostName+" and clickId: "+clickId);
	//	logger.info("Redirecting");
		return controlService.confirmSubService(hostName,hostIp,clickId,transactionId);
	}
	
	@GetMapping("/success")
	public ModelAndView successControl(@RequestParam(required=false) String txn_id, @RequestParam(required=false) String status) {
		//transactionservice.updateCgTransaction("success", status, txn_id);
		logger.info("received request in success url where trx_id: "+txn_id+" status: "+status);
		logger.info("redirecting to success page"+successPageURL);
		
		return new ModelAndView("redirect:"+successPageURL);
	}
	
	@GetMapping("/cancel")
	public ModelAndView cancelControl(@RequestParam(required=false) String txn_id, @RequestParam(required=false) String status) {
		logger.info("received request in cancel url where trx_id: "+txn_id+" status: "+status);
		logger.info("sending response OK for "+txn_id+" status "+status);
		return new ModelAndView("redirect:"+cancelPageURL);
	}
	@GetMapping("/mo")
	public ResponseEntity<?> MOControl(@RequestParam String channel, @RequestParam String msisdn, @RequestParam String trx_date, @RequestParam String trx_id, @RequestParam String session_id, @RequestParam String shortcode, @RequestParam String telco, @RequestParam String sms, @RequestParam String aggregator, @RequestParam int campaign_id)throws URISyntaxException, UnsupportedEncodingException {
		System.out.println("Channel    :"+channel);
		System.out.println("MSISDN     :"+msisdn);
		System.out.println("Trx_date   :"+trx_date);
		System.out.println("Trx ID     :"+trx_id);
		System.out.println("Session ID :"+session_id);
		System.out.println("Shortcode  :"+shortcode);
		System.out.println("Telco      :"+telco);
		System.out.println("SMS        :"+sms);
		System.out.println("Aggregator :"+aggregator);
		System.out.println("Campaign ID:"+campaign_id);
		logger.info("received request in MO url where channel: "+channel+" msisdn: "+msisdn+" trx_date: "+trx_date+" trx_id: "+trx_id+" session_id: "+session_id+" shortcode: "+shortcode+" telco: "+telco+" sms: "+sms+" aggregator: "+aggregator+" campaign_id: "+campaign_id);
		try {	
				if("R 01".equals(sms) || "01".equals(sms) || "R1".equals(sms) ) {
				logger.info("updating cg details from success in com_transaction");
				transactionservice.updateCgTransaction(msisdn,"success",sms, campaign_id,session_id);
				}
				else {
					logger.info("updating cg details for cancel in com_transaction");
					transactionservice.updateCgTransaction(msisdn,"cancel",sms, campaign_id,session_id);
				}
				
				//userHitsService.updateMsisdn(msisdn,campaign_id);
				//we will update this while unsubscribe
				//if("C 01".equals(sms)) 
				
				/*String str= message+Base64.getEncoder().encodeToString(msisdn.getBytes("utf-8"));
				logger.info("inserting into com_subscribed_users_details");
				subscribedUsersDetailsService.subscribedUsersDetailsInfo(msisdn, session_id,"Glamour");
				logger.info("updating cg details from success in com_transaction");
				transactionservice.updateCgTransaction("success",sms, campaign_id);
				logger.info("updating moTransaction in com_transaction");
				transactionservice.updateMoTransaction(msisdn, value.getPackPrice(),session_id,campaign_id);
				//message=message+Base64.getEncoder().encodeToString(msisdn.getBytes("utf-8"));	
				logger.info("pushing MT url");
				controlService.MTService(msisdn,trx_id,session_id,shortcode,telco,str,value.getPrice(),value.getService(),value.getSubject(),value.getContent_type());
			
			
				logger.info("updating unsubscription column in com_subscribed_users_details");
				subscribedUsersDetailsService.unsubscribedUsersDetailsInfo(msisdn, session_id,"Glamour");
			
			
				logger.info("updating cg details for cancel in com_transaction");
				transactionservice.updateCgTransaction("cancel",sms, campaign_id);*/
				
			
		} catch (Exception e) {
			logger.error("Exception in MO: "+e);
			e.printStackTrace();		}
		logger.info("sending response OK from MO request");
		return new ResponseEntity("OK", HttpStatus.OK);
	}
	@GetMapping("/dr")
	public ResponseEntity<?> DRControl(@RequestParam String shortcode, @RequestParam String telco, @RequestParam String msisdn, @RequestParam String service, @RequestParam(required=false) String subject, @RequestParam String session_id, @RequestParam String trx_id, @RequestParam String status) {
		System.out.println("Shortcode :"+shortcode);
		System.out.println("Telco      :"+telco);
		System.out.println("MSISDN     :"+msisdn);
		System.out.println("Service    :"+service);
		System.out.println("Subject    :"+subject);
		System.out.println("Session ID :"+session_id);
		System.out.println("Trx ID     :"+trx_id);
		System.out.println("Status     :"+status);
		String[] subjectArray = subject.split(";");
		logger.info("recieved request in DR url where shortcode: "+shortcode+" telco: "+telco+" msisdn: "+msisdn+" service: "+service+" subject: "+subject+" session_id: "+session_id+" trx_id: "+trx_id+" status: "+status);
		try {
			if("REG".equals(subjectArray[1]) && status.equals("RECEIVED")){
				logger.info("inserting into com_subscribed_users_details");
				subscribedUsersDetailsService.subscribedUsersDetailsInfo(msisdn, session_id,value.getProductId());
				logger.info("inserting into com_subscribed_users");
				subscribedUsersSerivice.subscribedUserInfo(msisdn);
				//message=message+Base64.getEncoder().encodeToString(msisdn.getBytes("utf-8"));	
				logger.info("pushing MT url");
				controlService.MTService(msisdn,trx_id,session_id,shortcode,telco,value.getMessage(),value.getMtPrice(),value.getService(),value.getSubject(),value.getContent_type());
			}
			if("REG".equals(subjectArray[1]) && status.equals("REJECTED")) {
				transactionservice.updateCgStatus(session_id);
				
			}
			if("REG2".equals(subjectArray[1]) && status.equals("RECEIVED")) {
				logger.info("updating charge amount in com_transaction");
				transactionservice.updateBillingTransactionCharge(session_id,msisdn);
			}
			if("REG2".equals(subjectArray[1]) && status.equals("REJECTED")) {
				transactionservice.updateBillingStatusAndResponse(session_id);
			}
			if("UNREG".equals(subjectArray[1]) && status.equals("RECEIVED")) {
				logger.info("updating unsubscribed users details in com_unsubscribes_users_details");
				subscribedUsersDetailsService.unsubscribedUsersDetailsInfo(msisdn, session_id,value.getProductId());
			}
			if("PUSH".equals(subjectArray[1]) && status.equals("RECEIVED")) {
				//message=message+Base64.getEncoder().encodeToString(msisdn.getBytes("utf-8"));
				//transactionservice.updateBillingTransactionCharge(session_id,msisdn);
				
				logger.info("updating users details for renewal in com_subscribed_users_details");
				subscribedUsersDetailsService.updateRenewalInfo(msisdn,session_id,value.getProductId());
			}
			if("PUSH".equals(subjectArray[1]) && status.equals("REJECTED")) {
				transactionservice.updateBillingStatusAndResponse(session_id);
			}
		} catch (Exception e) {
			logger.error("Exception in DR: "+e);
			e.printStackTrace();
		}
		logger.info("sending response OK from DR request");
		return new ResponseEntity("OK", HttpStatus.OK);
	}
	@GetMapping("/push-mt-for-renewal")
	public ResponseEntity<?> mtForRenewal() throws UnsupportedEncodingException, URISyntaxException{
		logger.info("request received for renewal of subscribed users in /push-mt-for-renewal url");
		renewalService.renewalForSubscribedUsers();
		return new ResponseEntity("OK", HttpStatus.OK);
	}
}
