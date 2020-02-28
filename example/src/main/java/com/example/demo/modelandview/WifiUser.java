package com.example.demo.modelandview;

public class WifiUser {
	private String msisdn;
	private int id;
	private String delieveryReport;
	private String delieveryReportTime;
	private String shortcode;
	private String date;
	private String mtId;
	private int process;
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDelieveryReport() {
		return delieveryReport;
	}
	public void setDelieveryReport(String delieveryReport) {
		this.delieveryReport = delieveryReport;
	}
	public String getDelieveryReportTime() {
		return delieveryReportTime;
	}
	public void setDelieveryReportTime(String delieveryReportTime) {
		this.delieveryReportTime = delieveryReportTime;
	}
	public String getShortcode() {
		return shortcode;
	}
	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMtId() {
		return mtId;
	}
	public void setMtId(String mtId) {
		this.mtId = mtId;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}
	@Override
	public String toString() {
		return "Wifi [msisdn=" + msisdn + ", id=" + id + ", delieveryReport=" + delieveryReport
				+ ", delieveryReportTime=" + delieveryReportTime + ", shortcode=" + shortcode + ", date=" + date
				+ ", mtId=" + mtId + ", process=" + process + "]";
	}
	
}
