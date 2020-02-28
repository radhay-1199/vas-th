package com.example.demo.modelandview;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import com.opencsv.CSVWriter;

public class csvWriter {
	public static void main(String args[]) {
		File file = new File("example1.csv"); 
    try { 
        FileWriter outputfile = new FileWriter(file);  
        CSVWriter writer = new CSVWriter(outputfile); 
        String[] header = { "Device Type","Device ID Type","Multiple Sim Status","S/N of Device","IMEI","Device Launch Date","Device Status" }; 
		writer.writeNext(header);
		long IMEI=123456789987654L;
		int leftLimit = 65; // letter 'a'
	    int rightLimit = 90; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
		Integer n=Integer.valueOf(args[0]);
		for(int i=0;i<n;i++){
			String s1="Handheld";
			String s2="IMEI";
			String s3="Y";
			String s4 = random.ints(leftLimit, rightLimit + 1)
				      .limit(targetStringLength)
				      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				      .toString();
			IMEI+=1;
			String s5=Long.toString(IMEI);
			String s6=Integer.toString(20012001);
			String s7="New";
			
        	String[] data1 = { s1, s2, s3 ,s4, s5,s6,s7}; 
        	writer.writeNext(data1); 
		}
        writer.close();
    } 
    catch (IOException e) { 
        e.printStackTrace(); 
	}
 }
}
