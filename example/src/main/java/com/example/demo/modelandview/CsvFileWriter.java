package com.example.demo.modelandview;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvFileWriter {
	//Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
     
    //CSV file header
    private static final String FILE_HEADER = "recordType,servedIMEI,servedIMSI,servedMSISDN,systemType";
 
    public static void writeCsvFile(int number) {
    		long servedIMEI=987654321123456L;
    		long servedIMSI=9876543211234565L;
    		long servedMSISDN=9876543211L;
    		ArrayList<csvFile> list=new ArrayList<csvFile>();
        	for(int i=0;i<number;i++) {
        		int s1=8;
        		servedIMEI+=1;
        		servedIMSI+=1;
        		servedMSISDN+=1;
    			String s5="A";
        		list.add(new csvFile(s1,servedIMEI,servedIMSI,servedMSISDN,s5));
        	}
        FileWriter fileWriter = null;
                 
        try {
            fileWriter = new FileWriter("example.csv");
            
            fileWriter.append(FILE_HEADER.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);
            for(csvFile csvf : list) {
            	fileWriter.append(String.valueOf(csvf.getRecordType()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvf.getServedIMEI()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvf.getServedIMSI()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvf.getServedMSISDN()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(csvf.getSystemType());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            System.out.println("CSV file was created successfully !!!");
             
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
             
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }   
        }
    }
    public static void main(String args[]) {
    	Integer n=Integer.valueOf(args[0]);
    	writeCsvFile(n);
    }
}
