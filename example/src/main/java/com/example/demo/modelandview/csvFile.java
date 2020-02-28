package com.example.demo.modelandview;

public class csvFile {
	private int recordType;
    private long servedIMEI;
    private long servedIMSI;
    private long servedMSISDN;
    private String systemType;
    /**
     * @param recordType
     * @param servedIMEI
     * @param servedIMSI
     * @param servedMSISDN
     * @param systemType
     */
    public csvFile(int recordType, long servedIMEI, long servedIMSI, long servedMSISDN,
            String systemType) {
        super();
        this.recordType = recordType;
        this.servedIMEI = servedIMEI;
        this.servedIMSI = servedIMSI;
        this.servedMSISDN = servedMSISDN;
        this.systemType = systemType;
    }

	public int getRecordType() {
		return recordType;
	}
	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}
	public long getServedIMEI() {
		return servedIMEI;
	}
	public void setServedIMEI(long servedIMEI) {
		this.servedIMEI = servedIMEI;
	}
	public long getServedIMSI() {
		return servedIMSI;
	}
	public void setServedIMSI(long servedIMSI) {
		this.servedIMSI = servedIMSI;
	}
	public long getServedMSISDN() {
		return servedMSISDN;
	}
	public void setServedMSISDN(long servedMSISDN) {
		this.servedMSISDN = servedMSISDN;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
    @Override
    public String toString() {
        return "csvFile [recordType=" + recordType + ", servedIMEI=" + servedIMEI
                + ", servedIMSI=" + servedIMSI + ", servedMSISDN=" + servedMSISDN + ", systemType="
                + systemType + "]";
    }
}
